package com.unit.service.impl;

import com.unit.dto.TransferRequestDto;
import com.unit.entity.Account;
import com.unit.entity.User;
import com.unit.exception.BadRequestException;
import com.unit.exception.NotFoundException;
import com.unit.repository.AccountRepository;
import com.unit.repository.UserRepository;
import com.unit.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    @Override
    public List<Account> getAccountsByUserId(Long userId) {
        return accountRepository.findAllByUserId(userId, true);
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow(NotFoundException :: new);
    }

    @Override
    public String transfer(TransferRequestDto requestDto, Long userId) {

        User user = userRepository.findById(userId).orElseThrow();
        if(requestDto.getFromAccount().equals(requestDto.getToAccount())){
            throw new BadRequestException("FromAccount and toAccount can not the same account!");
        }

        if(!accountRepository.existsAccountByAccountNumber(requestDto.getFromAccount())){
            throw new BadRequestException("FromAccount is not exist!");
        }

        if(!accountRepository.existsAccountByAccountNumber(requestDto.getToAccount())){
            throw new BadRequestException("ToAccount is not exist!");
        }

        Account fromAccount = findByAccountNumber(requestDto.getFromAccount());

        if (!fromAccount.isActive()){
            throw new BadRequestException("FromAccount is deActive!");
        }

        if (!fromAccount.getUser().equals(user)){
            throw new BadRequestException("FromAccount is not current user's account!");
        }

        Account toAccount = findByAccountNumber(requestDto.getToAccount());

        if (!toAccount.isActive()){
            throw new BadRequestException("ToAccount is deActive!");
        }

        if (fromAccount.getBalance().compareTo(requestDto.getAmount())<0){
            throw new BadRequestException("Balance is not enough for transfer!");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(requestDto.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(requestDto.getAmount()));
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        return "Transfer successfully completed";
    }
}

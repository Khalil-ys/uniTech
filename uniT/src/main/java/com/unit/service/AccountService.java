package com.unit.service;

import com.unit.dto.TransferRequestDto;
import com.unit.entity.Account;
import com.unit.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    List<Account> getAccountsByUserId(Long userId);

    Account findByAccountNumber(String accountNumber);

    String transfer(TransferRequestDto requestDto, Long userId);
}

package com.unit.controller;


import com.unit.dto.TransferRequestDto;
import com.unit.entity.Account;
import com.unit.security.JwtService;
import com.unit.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final JwtService jwtService;

    @GetMapping("/accounts")
    public List<Account> accounts(@RequestHeader("Authorization") String token){
        Long userId=Long.valueOf(jwtService.getIdFromToken(token));
        return accountService.getAccountsByUserId(userId);
    }

    @PostMapping("/transfer")
    public String transfer(@RequestHeader("Authorization") String token,
                           @RequestBody TransferRequestDto requestDto){
        Long userId=Long.valueOf(jwtService.getIdFromToken(token));
        return accountService.transfer(requestDto, userId);
    }
}

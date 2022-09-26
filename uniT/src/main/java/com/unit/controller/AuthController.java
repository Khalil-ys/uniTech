package com.unit.controller;

import com.unit.dto.CreateUserDto;
import com.unit.dto.LoginRequestDto;
import com.unit.dto.LoginResponseDto;
import com.unit.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto){
        return authService.login(loginRequestDto);
    }


    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody CreateUserDto createUserDto){
        authService.signup(createUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

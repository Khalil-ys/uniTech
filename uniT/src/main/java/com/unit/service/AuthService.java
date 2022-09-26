package com.unit.service;

import com.unit.dto.CreateUserDto;
import com.unit.dto.LoginRequestDto;
import com.unit.dto.LoginResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);

    void signup(CreateUserDto signUpDto);
}

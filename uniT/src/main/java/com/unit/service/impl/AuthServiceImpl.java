package com.unit.service.impl;

import com.unit.dto.CreateUserDto;
import com.unit.dto.LoginRequestDto;
import com.unit.dto.LoginResponseDto;
import com.unit.dto.UserDto;
import com.unit.entity.User;
import com.unit.exception.BadRequestException;
import com.unit.security.JwtService;
import com.unit.service.AuthService;
import com.unit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userService.getUserByPin(loginRequestDto.getPin());
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw new BadRequestException("Bad credentials");
        }

        String token = jwtService.generateToken(user);
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setId(user.getId());
        userDto.setPin(user.getPin());
        LoginResponseDto response = LoginResponseDto.builder()
                .token(token)
                .userDto(userDto)
                .build();
        return response;
    }

    @Override
    public void signup(CreateUserDto signUpDto) {
        userService.createUser(signUpDto);
    }

}

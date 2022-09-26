package com.unit.service;

import com.unit.dto.CreateUserDto;
import com.unit.dto.UserDto;
import com.unit.entity.User;

public interface UserService {

    void createUser(CreateUserDto newUser);

    User getUserByPin(String pin);

    UserDto getUserDtoByPin(String email);

    UserDto getUserById(Long userId);
}

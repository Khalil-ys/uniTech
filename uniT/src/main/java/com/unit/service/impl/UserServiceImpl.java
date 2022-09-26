package com.unit.service.impl;

import com.unit.dto.CreateUserDto;
import com.unit.dto.UserDto;
import com.unit.entity.User;
import com.unit.exception.ConflictException;
import com.unit.exception.NotFoundException;
import com.unit.repository.UserRepository;
import com.unit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(CreateUserDto newUser) {
        if (userRepository.existsByPin(newUser.getPin())){
            throw new ConflictException("This user is exist !");
        }
        User user = new User();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setName(newUser.getName());
        user.setPin(newUser.getPin());
        userRepository.save(user);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException:: new);
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setPin(user.getPin());
        return userDto;
    }

    @Override
    public UserDto getUserDtoByPin(String email) {
        User user = userRepository.findByPin(email).orElseThrow(NotFoundException :: new);
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setPin(user.getPin());
        return userDto;
    }


    @Override
    public User getUserByPin(String email) {
        User user = userRepository.findByPin(email).orElseThrow(NotFoundException :: new);
        return user;
    }
}

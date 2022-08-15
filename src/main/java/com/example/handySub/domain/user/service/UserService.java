package com.example.handySub.domain.user.service;

import com.example.handySub.domain.user.dto.UserDto;
import com.example.handySub.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImpli {
    @Autowired
    UserRepository userRepository;

    @Override
    public void userInsert(UserDto userDto){
        userRepository.userInsert(userDto);
    }

    @Override
    public void userUpdate(UserDto userDto){
        userRepository.userUpdate(userDto);
    }

    @Override
    public void userDelete(UserDto userDto){
        userRepository.userDelete(userDto);
    }

    @Override
    public UserDto loginCheck(String email, String pwd){
        return userRepository.loginCheck(email,pwd);
    }
}
package com.example.handySub.domain.user.service;

import com.example.handySub.domain.user.collection.UserCollections;
import com.example.handySub.domain.user.dto.UserDto;
import com.example.handySub.domain.user.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepositoryImpl userRepository;

    @Override
    public void userInsert(UserDto userDto){
        userRepository.userInsert(userDto);
    }

    @Override
    public void userInfoUpdate(String email, String newinfo){
        userRepository.userInfoUpdate(email, newinfo);
    }

    @Override
    public void userDelete(String email){
        userRepository.userDelete(email);
    }

    @Override
    public UserDto loginCheck(String email, String pwd){
        return userRepository.loginCheck(email,pwd);
    }
}
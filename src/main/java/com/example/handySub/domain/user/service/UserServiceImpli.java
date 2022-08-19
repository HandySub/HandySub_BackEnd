package com.example.handySub.domain.user.service;

import com.example.handySub.domain.user.dto.UserDto;

public interface UserServiceImpli {
    void userInsert(UserDto userDto); //유저 등록
    void userInfoUpdate(String email, String newinfo); //유저 정보 수정
    void userDelete(String email); //유저 삭제
    UserDto loginCheck(String email, String pwd);
}
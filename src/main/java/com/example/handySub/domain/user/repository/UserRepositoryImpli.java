package com.example.handySub.domain.user.repository;

import com.example.handySub.domain.user.dto.UserDto;

public interface UserRepositoryImpli {
    void userInsert(UserDto userDto); //유저 등록
    void userUpdate(UserDto userDto); //유저 정보 수정
    void userDelete(UserDto userDto); //유저 삭제
    UserDto loginCheck(String email, String pwd);
}

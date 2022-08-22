package com.example.handySub.domain.user.dto;

import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String nickname;
    private String password;
    private Boolean sex;
    private Boolean handicapped;
    private String info;
    private Double cookies;
}

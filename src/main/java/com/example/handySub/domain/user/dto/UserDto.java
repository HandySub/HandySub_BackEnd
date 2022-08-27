package com.example.handySub.domain.user.dto;

import com.example.handySub.domain.user.collection.UserCollections;
import lombok.Data;

@Data
public class UserDto {
    //@Autowired
    //private SequenceGeneratorService sequenceGeneratorService;
    private String email;
    private String nickname;
    private String password;
    private Boolean sex;
    private Boolean handicapped;
    private String info;
    private Double cookies;

    public UserCollections toEntity(){
        //Long temp = sequenceGeneratorService.generateSequence(UserCollections.SEQUENCE_NAME);
        return UserCollections.builder()
                //._id(Long.toString(temp))
                ._id("test")
                .email(email)
                .nickname(nickname)
                .password(password)
                .sex(sex)
                .handicapped(handicapped)
                .info(info)
                .cookies(cookies)
                .build();
    }
}

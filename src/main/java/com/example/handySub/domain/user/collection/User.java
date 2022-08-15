package com.example.handySub.domain.user.collection;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Transient;

@Getter
@NoArgsConstructor
@Document(collection = "user")
public class User {
    @Transient
    public static final String SEQUENCE_NAME = "posts_sequence";

    @Id
    private Long id; //자동 증가 인덱스
    private String email;
    private String nickname;
    private String password;
    private Boolean sex;
    private Boolean handicapped;
    private String info;
    private Double cookies;

    public void setId(Long id){
        this.id = id;
    }

    @Builder
    public User(String email, String nickname, String password,
                Boolean sex, Boolean handicapped, String info, Double cookies){
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.sex = sex;
        this.handicapped = handicapped;
        this.info = info;
        this.cookies = cookies;
    }

    public void updateInfo(String info){ //정보 수정
        this.info = info;
    }


}

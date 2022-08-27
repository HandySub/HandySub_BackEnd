package com.example.handySub.domain.user.collection;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Transient;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserCollections {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private String _id;

    private String email;

    private String nickname;

    private String password;

    private Boolean sex;

    private Boolean handicapped;

    private String info;

    private Double cookies;

    @Builder
    public UserCollections(String _id, String email, String nickname, String password,
                            Boolean sex, Boolean handicapped, String info, Double cookies){
        this._id = _id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.sex = sex;
        this.handicapped = handicapped;
        this.info = info;
        this.cookies = cookies;
    }
}

package com.example.handySub.domain.user.collection;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserCollections {

    @Id
    private ObjectId userId;

    private String email;

    private String nickname;

    private String password;

    private Boolean sex;

    private Boolean handicapped;

    private String info;

    private Double cookies;

    @Builder
    public UserCollections(String email, String nickname, String password,
                            Boolean sex, Boolean handicapped, String info, Double cookies){
            this.email = email;
            this.nickname = nickname;
            this.password = password;
            this.sex = sex;
            this.handicapped = handicapped;
            this.info = info;
            this.cookies = cookies;
    }
}

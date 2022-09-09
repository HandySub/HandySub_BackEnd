package com.example.handySub.domain.user.collection;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Document(collection = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserCollections {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private String userId;

    private String email;

    private String nickname;

    private String password;

    private Boolean sex;

    private Boolean handicapped;

    private String info;

    private Double cookies;

    @ElementCollection(fetch = FetchType.EAGER)
    List<UserRole> userRoles;

}

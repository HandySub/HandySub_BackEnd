//_id 자동 증가하도록 연속성 정보 저장하는 컬렉션
package com.example.handySub.domain.user.collection;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@Document(collection = "db_seq")
public class DatabaseSeq {
    @Id
    private String id;
    private Long seq;
}

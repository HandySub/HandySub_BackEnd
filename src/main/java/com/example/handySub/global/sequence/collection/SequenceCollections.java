package com.example.handySub.global.sequence.collection;

import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "sequence")
public class SequenceCollections {

    @Id
    private String matchId;
    private Long seq;
}

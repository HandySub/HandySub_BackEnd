package com.example.handySub.domain.sequence.collection;

import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "sequence")
public class SequenceCollections {

    @Id
    private String _id;
    private Long seq;
}

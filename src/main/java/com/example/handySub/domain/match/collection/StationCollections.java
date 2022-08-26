package com.example.handySub.domain.match.collection;

import lombok.*;
import org.mongodb.morphia.annotations.Transient;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "station")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class StationCollections {

    private Long line;
    private String name;

    @Builder
    public StationCollections(Long line, String name) {
        this.line = line;
        this.name = name;
    }
}

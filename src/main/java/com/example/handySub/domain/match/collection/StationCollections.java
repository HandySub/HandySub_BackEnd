package com.example.handySub.domain.match.collection;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "station")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class StationCollections {

    @Id
    private String _id;
    private Long line;
    private String name;

    @Builder
    public StationCollections(String _id, Long line, String name) {
        this._id = _id;
        this.line = line;
        this.name = name;
    }
}

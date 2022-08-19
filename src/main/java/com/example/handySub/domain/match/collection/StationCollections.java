package com.example.handySub.domain.match.collection;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "stations")
@Getter
@Setter
@NoArgsConstructor
public class StationCollections {

    @Id
    private ObjectId stationId;

    private Integer line;

    private String name;

    @Builder
    public StationCollections(Integer line, String name) {
        this.line = line;
        this.name = name;
    }
}

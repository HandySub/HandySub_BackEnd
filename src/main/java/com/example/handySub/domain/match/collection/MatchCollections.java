package com.example.handySub.domain.match.collection;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Document(collection="match")
@NoArgsConstructor
@Builder
@ToString
public class MatchCollections {

    @Id
    private String _id;
    private Long handicappedId;
    private Long nonHandicappedId;
    private Long startedAt;
    private Long finishedAt;
    private Integer requiredTime;
    private Long startStation;
    private Long finishStation;

    @Builder
    public MatchCollections(String _id, Long handicappedId, Long nonHandicappedId, Long startedAt,
                            Long finishedAt, Integer requiredTime, Long startStation, Long finishStation){
        this._id=_id;
        this.handicappedId = handicappedId;
        this.nonHandicappedId = nonHandicappedId;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.requiredTime = requiredTime;
        this.startStation = startStation;
        this.finishStation = finishStation;
    }
}
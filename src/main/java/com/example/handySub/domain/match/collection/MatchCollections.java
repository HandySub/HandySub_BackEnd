package com.example.handySub.domain.match.collection;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.sql.Date;

@Document(collection = "matchings")
@Getter
@Setter
@NoArgsConstructor
public class MatchCollections {
    @Id
    private ObjectId matchId;

    private ObjectId handicapped;

    private ObjectId nonHandicapped;

    private Date startedAt;

    private Date finishedAt;

    private Integer requiredTime;

    private Integer startStation;

    private Integer finishStation;

    @Builder
    public MatchCollections(ObjectId handicapped, ObjectId nonHandicapped, Date startedAt,
                            Date finishedAt, Integer requiredTime, Integer startStation, Integer finishStation){
        this.handicapped = handicapped;
        this.nonHandicapped = nonHandicapped;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.requiredTime = requiredTime;
        this.startStation = startStation;
        this.finishStation = finishStation;
    }
}
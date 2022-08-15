package com.example.handySub.domain.match.collection;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import java.sql.Date;

@Document(collection = "matchings")
@Getter
@Setter
public class MatchCollections {
    @Id
    private Long matchId;

    private Long handicapped;

    private Long nonHandicapped;

    private Long startedAt;

    private Long finishedAt;

    private Integer requiredTime;

    private Long startStation;

    private Long finishStation;

//    @Builder
//    public MatchCollections(Long handicapped, Long nonHandicapped, Date startedAt,
//                            Date finishedAt, Integer requiredTime, Integer startStation, Integer finishStation){
//        this.handicapped = handicapped;
//        this.nonHandicapped = nonHandicapped;
//        this.startedAt = startedAt;
//        this.finishedAt = finishedAt;
//        this.requiredTime = requiredTime;
//        this.startStation = startStation;
//        this.finishStation = finishStation;
//    }
}
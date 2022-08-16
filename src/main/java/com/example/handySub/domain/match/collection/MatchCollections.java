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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MatchCollections {

    @Id @Field
    private Long matchId;

    @Field
    private Long handicappedId;
    @Field
    private Long nonHandicappedId;
    @Field
    private Long startedAt;
    @Field
    private Long finishedAt;
    @Field
    private Integer requiredTime;
    @Field
    private Long startStation;
    @Field
    private Long finishStation;

    @Builder
    public MatchCollections(Long handicappedId, Long nonHandicappedId, Long startedAt,
                            Long finishedAt, Integer requiredTime, Long startStation, Long finishStation){
        this.handicappedId = handicappedId;
        this.nonHandicappedId = nonHandicappedId;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.requiredTime = requiredTime;
        this.startStation = startStation;
        this.finishStation = finishStation;
    }
}
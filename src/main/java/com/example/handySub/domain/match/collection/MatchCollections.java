package com.example.handySub.domain.match.collection;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String nonContents;
    private StationCollections startStation;
    private StationCollections finishStation;

    @Builder
    public MatchCollections(String _id, Long handicappedId, Long nonHandicappedId, Long startedAt,
                            Long finishedAt, Integer requiredTime, String nonContents, StationCollections startStation, StationCollections finishStation){
        this._id=_id;
        this.handicappedId = handicappedId;
        this.nonHandicappedId = nonHandicappedId;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.requiredTime = requiredTime;
        this.nonContents = nonContents;
        this.startStation = startStation;
        this.finishStation = finishStation;
    }
}
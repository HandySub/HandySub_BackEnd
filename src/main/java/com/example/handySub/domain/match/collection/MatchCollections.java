package com.example.handySub.domain.match.collection;

import lombok.*;
import org.mongodb.morphia.annotations.Transient;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection="match")
@NoArgsConstructor
@Builder
@ToString
public class MatchCollections {

    @Transient
    public static final String SEQUENCE_NAME = "match_sequence";

    @Id
    private String _id; //자동으로 증가하게 만들어야 함
    private Long handicappedId;
    private Long nonHandicappedId;
    private Long startedAt;
    private Long finishedAt;
    private Integer requiredTime;
    private String nonContents;
    private StationCollections startStation;
    private StationCollections finishStation;
    private boolean isDeleted;

    @Builder
    public MatchCollections(String _id, Long handicappedId, Long nonHandicappedId, Long startedAt, Long finishedAt, Integer requiredTime, String nonContents,
                            StationCollections startStation, StationCollections finishStation, boolean isDeleted){
        this._id=_id;
        this.handicappedId = handicappedId;
        this.nonHandicappedId = nonHandicappedId;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.requiredTime = requiredTime;
        this.nonContents = nonContents;
        this.startStation = new StationCollections(startStation.getLine(), startStation.getName());
        this.finishStation = new StationCollections(finishStation.getLine(), finishStation.getName());
        this.isDeleted=isDeleted;
    }
}
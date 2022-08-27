package com.example.handySub.domain.match.collection;

import com.example.handySub.domain.match.constant.MatchConstants;
import lombok.*;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Transient;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

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
    private String matchId;
    private String handicappedId;
    private String nonHandicappedId;

    private Long startedAt;
    private Long finishedAt;

    private Integer requiredTime;
    private String nonContents;
    private StationCollections startStation;
    private StationCollections finishStation;
    private ReviewCollections myReview;
    private boolean isDeleted;
    @Enumerated(EnumType.STRING)
    private MatchConstants.EMatchingStatus matchingStatus;

    @Builder
    public MatchCollections(String matchId, String handicappedId, String nonHandicappedId, Long startedAt, Long finishedAt, Integer requiredTime, String nonContents,
                            StationCollections startStation, StationCollections finishStation, ReviewCollections myReview, boolean isDeleted, MatchConstants.EMatchingStatus mathcingStatus){
        this.matchId=matchId;
        this.handicappedId = handicappedId;
        this.nonHandicappedId = nonHandicappedId;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.requiredTime = requiredTime;
        this.nonContents = nonContents;
        this.startStation = new StationCollections(startStation.getLine(), startStation.getName());
        this.finishStation = new StationCollections(finishStation.getLine(), finishStation.getName());
        this.myReview=new ReviewCollections(myReview.getCookies(),myReview.getComment(),myReview.getCreatedAt());
        this.isDeleted=isDeleted;
        this.matchingStatus=mathcingStatus;
    }
}
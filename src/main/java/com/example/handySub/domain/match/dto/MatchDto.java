package com.example.handySub.domain.match.dto;


import lombok.*;
import org.bson.types.ObjectId;

import java.sql.Date;

public class MatchDto {

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetNonMatch {
        private Long matchId;
        private Long handicapped;
        private Long nonHandicapped;
        private Date startedAt;
        private Date finishedAt;
        private Integer requiredTime;
        private Long startStation;
        private Long finishStation;
    }

}

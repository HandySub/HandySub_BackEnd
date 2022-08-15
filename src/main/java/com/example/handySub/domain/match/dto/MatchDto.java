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
        private Long startStation;
        private Long finishStation;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetNonMatchInfo {
        private Long matchId;
        private Long startStation;
        private Long finishStation;
        private String nickname;
    }

}

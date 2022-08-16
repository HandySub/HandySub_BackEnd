package com.example.handySub.domain.match.dto;


import lombok.*;
import com.example.handySub.domain.match.collection.MatchCollections;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public abstract class MatchDto {

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

    @Getter
    @Builder
    @ApiModel(description = "장애인의 매칭 신청 목록 조회를 위한 응답 객체")
    public static class GetAllResponse{
        private Long matchId;
        private Long HandicappedId;
        private Long nonHandicappedId;
        private Long startedAt;
        private Long finishedAt;
        private Integer requiredTime;
        private Long startStation;
        private Long finishStation;

        @QueryProjection
        public GetAllResponse(Long matchId, Long HandicappedId, Long nonHandicappedId, Long startedAt, Long finishedAt, Integer requiredTime, Long startStation, Long finishStation){
            this.matchId=matchId;
            this.HandicappedId=HandicappedId;
            this.nonHandicappedId=nonHandicappedId;
            this.startedAt=startedAt;
            this.finishedAt=finishedAt;
            this.requiredTime=requiredTime;
            this.startStation=startStation;
            this.finishStation=finishStation;
        }

        public static GetAllResponse convertMatchToDto(MatchCollections matchCollections){
            return new GetAllResponse(matchCollections.getMatchId(), matchCollections.getHandicappedId(), matchCollections.getNonHandicappedId(), matchCollections.getStartedAt(), matchCollections.getFinishedAt(), matchCollections.getRequiredTime(), matchCollections.getStartStation(),matchCollections.getFinishStation());
        }
    }

    @Getter
    @Builder
    @ApiModel(description = "장애인의 매칭 신청 삭제를 위한 요청 객체")
    public static class DeleteMatch{
            @ApiModelProperty(notes="매칭 id를 입력해주세요")
            private Long matchId;
    }
}

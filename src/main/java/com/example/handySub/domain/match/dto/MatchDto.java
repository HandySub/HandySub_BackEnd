package com.example.handySub.domain.match.dto;


import com.example.handySub.domain.match.collection.ReviewCollections;
import com.example.handySub.domain.match.collection.StationCollections;
import com.example.handySub.domain.match.constant.MatchConstants;
import com.example.handySub.global.sequence.service.SequenceGeneratorService;
import lombok.*;
import com.example.handySub.domain.match.collection.MatchCollections;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public abstract class MatchDto {


    @Setter
    @Getter
    @ApiModel(description = "비장애인의 매칭 신청 대기 목록 조회를 위한 응답 객체")
    public static class GetAllNonMatch {
        private String matchId;
        private StationCollections startStation;
        private StationCollections finishStation;
    }

    @Setter
    @Getter
    @ApiModel(description = "비장애인의 매칭 신청 대기 목록 개별 조회를 위한 응답 객체")
    public static class GetNonMatch {
        private String matchId;
        private StationCollections startStation;
        private StationCollections finishStation;
        private String nickname;
    }

    @Setter
    @Getter
    @ApiModel(description = "비장애인의 매칭 신청 대기 목록 개별 신청을 위한 요청 객체")
    public static class GetNonMatchApply {
        private String matchId;
        private Integer requiredTime;
        private String nonContents;
    }

    @Getter
    @Builder
    @ApiModel(description = "장애인의 매칭 신청 목록 조회를 위한 응답 객체")
    public static class GetAllResponse{
        private String matchId;
        private String handicappedId;
        private String nonHandicappedId;
        private Long startedAt;
        private Long finishedAt;
        private Integer requiredTime;
        private String nonContents;
        private Long startStationLine;
        private String startStationName;
        private Long finishStationLine;
        private String finishStationName;
        private MatchConstants.EMatchingStatus matchingStatus;

        public GetAllResponse(String matchId, String handicappedId, String nonHandicappedId, Long startedAt, Long finishedAt, Integer requiredTime, String nonContents, Long startStationLine, String startStationName, Long finishStationLine, String finishStationName, MatchConstants.EMatchingStatus matchingStatus) {
            this.matchId=matchId;
            this.handicappedId=handicappedId;
            this.nonHandicappedId=nonHandicappedId;
            this.startedAt=startedAt;
            this.finishedAt=finishedAt;
            this.requiredTime=requiredTime;
            this.nonContents=nonContents;
            this.startStationLine=startStationLine;
            this.finishStationLine=finishStationLine;
            this.finishStationName=finishStationName;
            this.matchingStatus=matchingStatus;
        }

        public static GetAllResponse convertMatchToDto(MatchCollections matchCollections){

            return matchCollections.isDeleted()==true?
                    new GetAllResponse(matchCollections.getMatchId(), null, null, null, null, null, "삭제된 매칭입니다", null, null, null, null, null):
                    new GetAllResponse(matchCollections.getMatchId(),matchCollections.getHandicappedId(),matchCollections.getNonHandicappedId(),
                            matchCollections.getStartedAt(),matchCollections.getFinishedAt(),matchCollections.getRequiredTime(),matchCollections.getNonContents(),
                            matchCollections.getStartStation().getLine(),matchCollections.getStartStation().getName(),matchCollections.getFinishStation().getLine(),matchCollections.getFinishStation().getName(),
                            matchCollections.getMatchingStatus());
        }

        public MatchCollections toEntity(){
            return MatchCollections.builder()
                    .matchId(matchId)
                    .handicappedId(handicappedId)
                    .nonHandicappedId(nonHandicappedId)
                    .startedAt(startedAt)
                    .finishedAt(finishedAt)
                    .requiredTime(requiredTime)
                    .nonContents(nonContents)
                    .startStation(new StationCollections(startStationLine,startStationName))
                    .finishStation(new StationCollections(finishStationLine,finishStationName))
                    .matchingStatus(matchingStatus)
                    .build();
        }
    }

    @Getter
    @ApiModel(description="장애인의 매칭 신청 등록을 위한 요청 객체")
    public static class CreateRequest{


        MatchConstants.EMatchingStatus defaultmatchingStatus= MatchConstants.EMatchingStatus.BEFORE;

        @Autowired
        private SequenceGeneratorService sequenceGeneratorService;

        private String handicappedId;

        private Long startStationLine;
        private String startStationName;
        private Long finishStationLine;
        private String finishStationName;

        @QueryProjection
        public CreateRequest(String handicappedId, Long startStationLine,
                             String startStationName, Long finishStationLine, String finishStationName){
            this.handicappedId=handicappedId;
            this.startStationLine=startStationLine;
            this.startStationName=startStationName;
            this.finishStationLine=finishStationLine;
            this.finishStationName=finishStationName;
        }

        public MatchCollections toEntity(){
            return MatchCollections.builder()
                    .matchId("matchings") //추후 변경
                    .handicappedId(handicappedId)
                    .nonHandicappedId("아직 매칭 전입니다")
                    .startedAt(0L) //자동 생성
                    .finishedAt(0L)
                    .requiredTime(0)
                    .nonContents("내용이 비어있습니다")
                    .startStation(new StationCollections(startStationLine,startStationName))
                    .finishStation(new StationCollections(finishStationLine,finishStationName))
                    .myReview(new ReviewCollections(0,"리뷰를 입력하지 않았습니다",null))
                    .matchingStatus(defaultmatchingStatus)
                    .build();
        }
    }

    @Getter
    @Builder
    @ApiModel(description="장애인의 매칭 신청 등록을 위한 응답 객체")
    public static class CreateResponse{
        private String matchId;
    }

    @Getter
    @Builder
    @ApiModel(description = "장애인의 매칭 신청 삭제를 위한 요청 객체")
    public static class DeleteMatch{
            @ApiModelProperty(notes="매칭 id를 입력해주세요")
            private String matchId;
    }

    @Getter
    @Builder
    @ApiModel(description = "매칭 결과 입력을 위한 요청 객체")
    public static class EnterMatch{
        private String matchId;
        private MatchConstants.EMatchingStatus matchingStatus;
    }

    @Getter
    @Builder
    @ApiModel(description = "리뷰 입력을 위한 요청 객체")
    public static class RequestReview{
        private String matchId;
        private double cookies;
        private String comment;
        private Long createdAt;
    }
}

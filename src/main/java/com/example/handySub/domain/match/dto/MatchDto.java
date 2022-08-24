package com.example.handySub.domain.match.dto;


import com.example.handySub.domain.match.collection.StationCollections;
import com.example.handySub.domain.sequence.service.SequenceGeneratorService;
import lombok.*;
import com.example.handySub.domain.match.collection.MatchCollections;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class MatchDto {

    @Setter
    @Getter
    @ApiModel(description = "비장애인의 매칭 신청 대기 목록 조회를 위한 응답 객체")
    public static class GetAllNonMatch {
        private String _id;
        private StationCollections startStation;
        private StationCollections finishStation;
    }

    @Setter
    @Getter
    @ApiModel(description = "비장애인의 매칭 신청 대기 목록 개별 조회를 위한 응답 객체")
    public static class GetNonMatch {
        private String _id;
        private StationCollections startStation;
        private StationCollections finishStation;
        private String nickname;
    }

    @Setter
    @Getter
    @ApiModel(description = "비장애인의 매칭 신청 대기 목록 개별 신청을 위한 요청 객체")
    public static class GetNonMatchApply {
        private String _id;
        private Integer requiredTime;
        private String nonContents;
    }

    @Getter
    @Builder
    @ApiModel(description = "장애인의 매칭 신청 목록 조회를 위한 응답 객체")
    public static class GetAllResponse{
        private String _id;
        private Long handicappedId;
        private Long nonHandicappedId;
        private Long startedAt;
        private Long finishedAt;
        private Integer requiredTime;
        private String nonContents;
        private Long startStationLine;
        private String startStationName;
        private Long finishStationLine;
        private String finishStationName;

        public static GetAllResponse convertMatchToDto(MatchCollections matchCollections){
            return new GetAllResponse(matchCollections.get_id(), matchCollections.getHandicappedId(), matchCollections.getNonHandicappedId(), matchCollections.getStartedAt(), matchCollections.getFinishedAt(), matchCollections.getRequiredTime(),
                    matchCollections.getNonContents(), matchCollections.getStartStation().getLine(),matchCollections.getStartStation().getName(), matchCollections.getFinishStation().getLine(),matchCollections.getFinishStation().getName());
        }

        public MatchCollections toEntity(){
            return MatchCollections.builder()
                    ._id(_id)
                    .handicappedId(handicappedId)
                    .nonHandicappedId(nonHandicappedId)
                    .startedAt(startedAt)
                    .finishedAt(finishedAt)
                    .requiredTime(requiredTime)
                    .nonContents(nonContents)
                    .startStation(new StationCollections(startStationLine,startStationName))
                    .finishStation(new StationCollections(finishStationLine,finishStationName))
                    .build();
        }
    }

    @Getter
    @ApiModel(description="장애인의 매칭 신청 등록을 위한 요청 객체")
    public static class CreateRequest{
        @Autowired
        private SequenceGeneratorService sequenceGeneratorService;

        private Long handicappedId;
        private Long startedAt;
        private Long finishedAt;
        private Long startStationLine;
        private String startStationName;
        private Long finishStationLine;
        private String finishStationName;

        @QueryProjection
        public CreateRequest(Long handicappedId, Long startedAt, Long finishedAt, Long startStationLine,
                             String startStationName, Long finishStationLine, String finishStationName){
            this.handicappedId=handicappedId;
            this.startedAt=startedAt;
            this.finishedAt=finishedAt;
            this.startStationLine=startStationLine;
            this.startStationName=startStationName;
            this.finishStationLine=finishStationLine;
            this.finishStationName=finishStationName;
        }

        public MatchCollections toEntity(){
            return MatchCollections.builder()
                    ._id("matchings") //추후 변경
                    .handicappedId(handicappedId)
                    .nonHandicappedId(0L)
                    .startedAt(startedAt)
                    .finishedAt(finishedAt)
                    .requiredTime(0)
                    .nonContents("내용이 비어있습니다")
                    .startStation(new StationCollections(startStationLine,startStationName))
                    .finishStation(new StationCollections(finishStationLine,finishStationName))
                    .build();
        }
    }

    @Getter
    @Builder
    @ApiModel(description="장애인의 매칭 신청 등록을 위한 응답 객체")
    public static class CreateResponse{
        private String _id;
    }

    @Getter
    @Builder
    @ApiModel(description = "장애인의 매칭 신청 삭제를 위한 요청 객체")
    public static class DeleteMatch{
            @ApiModelProperty(notes="매칭 id를 입력해주세요")
            private String _id;
    }
}

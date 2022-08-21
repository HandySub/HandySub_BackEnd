package com.example.handySub.domain.match.dto;


import com.example.handySub.domain.match.collection.StationCollections;
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
        private StationCollections startStation;
        private StationCollections finishStation;

        @QueryProjection
        public GetAllResponse(String _id, Long handicappedId, Long nonHandicappedId, Long startedAt, Long finishedAt, Integer requiredTime, StationCollections startStation, StationCollections finishStation){
            this._id=_id;
            this.handicappedId=handicappedId;
            this.nonHandicappedId=nonHandicappedId;
            this.startedAt=startedAt;
            this.finishedAt=finishedAt;
            this.requiredTime=requiredTime;
            this.startStation=startStation;
            this.finishStation=finishStation;
        }

        public static GetAllResponse convertMatchToDto(MatchCollections matchCollections){
            return new GetAllResponse(matchCollections.get_id(), matchCollections.getHandicappedId(), matchCollections.getNonHandicappedId(), matchCollections.getStartedAt(), matchCollections.getFinishedAt(), matchCollections.getRequiredTime(), matchCollections.getStartStation(),matchCollections.getFinishStation());
        }

        public MatchCollections toEntity(){
            return MatchCollections.builder()
                    ._id(_id)
                    .handicappedId(handicappedId)
                    .nonHandicappedId(nonHandicappedId)
                    .startedAt(startedAt)
                    .finishedAt(finishedAt)
                    .requiredTime(requiredTime)
                    .startStation(startStation)
                    .finishStation(finishStation)
                    .build();
        }
    }

    @Getter
    @Builder
    @ApiModel(description="장애인의 매칭 신청 등록을 위한 요청 객체")
    public static class CreateRequest{
        private Long handicappedId;
        private Long nonHandicappedId;
        private Long startedAt;
        private Long finishedAt;
        private Integer requiredTime;
        private StationCollections startStation;
        private StationCollections finishStation;

        @QueryProjection
        public CreateRequest(Long handicappedId, Long nonHandicappedId, Long startedAt, Long finishedAt, Integer requiredTime, StationCollections startStation, StationCollections finishStation){
            this.handicappedId=handicappedId;
            this.nonHandicappedId=nonHandicappedId;
            this.startedAt=startedAt;
            this.finishedAt=finishedAt;
            this.requiredTime=requiredTime;
            this.startStation=startStation;
            this.finishStation=finishStation;
        }

        public MatchCollections toEntity(){
            return MatchCollections.builder()
                    .handicappedId(handicappedId)
                    .nonHandicappedId(nonHandicappedId)
                    .startedAt(startedAt)
                    .finishedAt(finishedAt)
                    .requiredTime(requiredTime)
                    .startStation(startStation)
                    .finishStation(finishStation)
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

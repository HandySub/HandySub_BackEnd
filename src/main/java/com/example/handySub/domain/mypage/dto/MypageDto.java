package com.example.handySub.domain.mypage.dto;

import com.example.handySub.domain.match.collection.StationCollections;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class MypageDto {

    @Setter
    @Getter
    @ApiModel(description = "장애인의 마이 페이지 조회를 위한 응답 객체")
    public static class GetHandyInfo {
        //match 목록 (리뷰를 남긴 매칭들만)
        //-----------> match collection에서 handicappedId와 sessionId가 같은 경우,
        //             myReview의 comment가 null이 아닌 것
        private List<GetMatchList> matchList;
        //채팅 기록

        // 사용자 정보

    }

    @Setter
    @Getter
    @ApiModel(description = "비장애인의 마이 페이지 조회를 위한 응답 객체")
    public static class GetNonHandyInfo {
        //match 목록 (신청한 것들 중 완료된 매칭들만)
        //-----------> match collection에서 nonHandicappedId와 sessionId가 같은 경우,
        //             matchingStatus가 SUCCESS인 경우
        private List<GetMatchList> matchList;
        //채팅 기록

        // 사용자 정보

    }

    @Setter
    @Getter
    @ApiModel(description = "매칭 목록 조회를 위한 응답 객체")
    public static class GetMatchList {
        private String matchId;
        private StationCollections startStation;
        private StationCollections finishStation;
    }

    @Setter
    @Getter
    @ApiModel(description = "채팅 기록 조회를 위한 응답 객체")
    public static class GetChat {
    }

    @Setter
    @Getter
    @ApiModel(description = "비장애인의 쿠키 정보를 보여주기 위한 응답 객체")
    public static class GetNonCookies {
    }

    @Builder
    @Getter
    @ApiModel(description = "장애 여부 변경을 위한 요청 객체")
    public static class UpdateHandicapped {
    }
}

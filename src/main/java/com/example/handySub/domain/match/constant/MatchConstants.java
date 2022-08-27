package com.example.handySub.domain.match.constant;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class MatchConstants {

    @Getter
    @RequiredArgsConstructor
    public enum EMatchResponseMessage{
        GET_ALL_NON_MATCH_SUCCESS("매칭 신청 대기 목록을 조회했습니다."),
        GET_NON_MATCH_SUCCESS("매칭 신청 대기 목록을 개별 조회했습니다."),
        PATCH_NON_MATCH_APPLY_SUCCESS("매칭을 신청했습니다."),
        GET_ALL_MATCH_SUCCESS("매칭 신청 목록을 조회했습니다."),
        DELETE_MATCH_SUCCESS("매칭을 삭제하였습니다."),
        CREATE_MATCH_SUCCESS("매칭을 신청하였습니다"),
        ENTER_MATCH_RESULT("매칭 결과를 입력하였습니다"),
        CREATE_REVIEW_SUCCESS("리뷰를 작성하였습니다.");
        private final String message;
    }

    @Getter
    @RequiredArgsConstructor
    public enum EMatchingStatus{
        BEFORE("매칭 전입니다."),
        SUCCESS("매칭 성공입니다."),
        FAIL("매칭 실패입니다.");
        private final String status;
    }
}

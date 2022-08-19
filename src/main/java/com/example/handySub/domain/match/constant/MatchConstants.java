package com.example.handySub.domain.match.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class MatchConstants {

    @Getter
    @RequiredArgsConstructor
    public enum EMatchResponseMessage{
        GET_ALL_MATCH_SUCCESS("매칭 신청 목록을 조회했습니다."),
        DELETE_MATCH_SUCCESS("매칭을 삭제하였습니다."),
        CREATE_MATCH_SUCCESS("매칭을 신청하였습니다");
        private final String message;
    }
}

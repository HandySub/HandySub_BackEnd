package com.example.handySub.domain.match.service;

import com.example.handySub.domain.match.collection.MatchCollections;
import com.example.handySub.domain.match.dto.MatchDto;

import java.util.List;

public interface MatchService {

    List<MatchDto.GetAllResponse> getAllMatchByHandicappedId(String handicappedId);
    void createMatch(MatchDto.CreateRequest createRequest);
    void test();
    List<MatchDto.GetAllNonMatch> getAllNonMatchByStation(Long startStation, Long finishStation);
    MatchDto.GetNonMatch getNonMatchByID(String matchId);
    void patchNonMatchApply(MatchDto.GetNonMatchApply getNonMatchApply);
    void deleteMatch(String matchId);
    void getResultMatch(MatchDto.EnterMatch enterMatch);
    void createReview(MatchDto.RequestReview requestReview);
}



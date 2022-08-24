package com.example.handySub.domain.match.service;

import com.example.handySub.domain.match.collection.MatchCollections;
import com.example.handySub.domain.match.dto.MatchDto;

import java.util.List;

public interface MatchService {

    List<MatchDto.GetAllResponse> getAllMatchByHandicappedId(Long handicappedId);
    void createMatch(MatchDto.CreateRequest createRequest);
    void test();
    List<MatchDto.GetAllNonMatch> getAllNonMatchByStation(Long startStation, Long finishStation);
    MatchDto.GetNonMatch getNonMatchByID(String _id);
    void patchNonMatchApply(MatchDto.GetNonMatchApply getNonMatchApply);
    MatchCollections deleteMatch(String _id);
    MatchCollections validateMatchId(String _id);
}



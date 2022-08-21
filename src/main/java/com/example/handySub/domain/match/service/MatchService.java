package com.example.handySub.domain.match.service;

import com.example.handySub.domain.match.dto.MatchDto;

import java.util.List;

public interface MatchService {

    List<MatchDto.GetAllResponse> getAllMatchByHandicappedId(Long handicappedId);
    void createMatch(MatchDto.GetAllResponse getAllResponse);
    void test();
    List<MatchDto.GetAllNonMatch> getAllNonMatchByStation(Long startStation, Long finishStation);
    MatchDto.GetNonMatch getNonMatchByID(String _id);
}



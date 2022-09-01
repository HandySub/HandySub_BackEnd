package com.example.handySub.domain.mypage.service;

import com.example.handySub.domain.mypage.dto.MypageDto;

import java.util.List;

public interface MypageService {
    List<MypageDto.GetMatchList> GetAllMatchList(String handicappedId);
    List<MypageDto.GetMatchList> GetNonAllMatchList(String nonHandicappedId);
}

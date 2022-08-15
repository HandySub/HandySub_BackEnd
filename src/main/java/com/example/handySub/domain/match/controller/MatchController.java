package com.example.handySub.domain.match.controller;

import com.example.handySub.domain.match.dto.MatchDto;
import com.example.handySub.domain.match.service.MatchService;
import com.example.handySub.util.BaseException;
import com.example.handySub.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/match")
public class MatchController {
    @Autowired
    MatchService matchService;

    @ResponseBody
    @GetMapping("/non/list/{startStation}/{finishStation}")
    public BaseResponse<List<MatchDto.GetNonMatch>> getNonMatchList(@PathVariable Long startStation, @PathVariable Long finishStation) throws BaseException {
        try {
            List<MatchDto.GetNonMatch> getMatchNonList = matchService.getNonMatchList(startStation, finishStation);
            return new BaseResponse<>(getMatchNonList);
        }catch (Exception e) {
            return new BaseResponse<>(null);
        }
    }

    // matchController에 대한 test document 생성(서버 구현용)
    @GetMapping(value = "/test")
    public void test() {
        matchService.test();
    }

}

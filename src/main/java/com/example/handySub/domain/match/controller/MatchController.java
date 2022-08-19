package com.example.handySub.domain.match.controller;

import com.example.handySub.domain.match.constant.MatchConstants;
import com.example.handySub.domain.match.dto.MatchDto;
import com.example.handySub.domain.match.service.MatchService;
import com.example.handySub.global.dto.ResponseDto;
import com.example.handySub.util.BaseException;
import com.example.handySub.util.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/match")
@Api(tags="Match API")
public class MatchController {

    private final MatchService matchService;

//    @ResponseBody
//    @GetMapping("/non/list/{startStation}/{finishStation}")
//    public BaseResponse<List<MatchDto.GetNonMatch>> getNonMatchList(@PathVariable Long startStation, @PathVariable Long finishStation) throws BaseException {
//        try {
//            List<MatchDto.GetNonMatch> getMatchNonList = matchService.getNonMatchList(startStation, finishStation);
//            return new BaseResponse<>(getMatchNonList);
//        }catch (Exception e) {
//            return new BaseResponse<>(null);
//        }
//    }
//
    // matchController에 대한 test document 생성(서버 구현용)
    @GetMapping(value = "/test")
    public void test() {
        matchService.test();
    }

//    @ResponseBody
//    @GetMapping("/non/{_id}")
//    public BaseResponse<MatchDto.GetNonMatchInfo> getNonMatchData(@PathVariable Long _id) throws BaseException{
//        try{
//            MatchDto.GetNonMatchInfo getNonMatchInfo = matchService.getNonMatchInfo(_id);
//            return new BaseResponse<>(getNonMatchInfo);
//        }catch (Exception e) {
//            return new BaseResponse<>(null);
//        }
//    }

    @ApiOperation(value="매칭 신청 목록 전체 조회", notes="장애인의 매칭 신청 목록을 조회합니다")
    @GetMapping("/{handicappedId}")
    public ResponseEntity<ResponseDto<List<MatchDto.GetAllResponse>>> getAllmatch(@PathVariable Long handicappedId){
        return ResponseEntity.ok(ResponseDto.create(MatchConstants.EMatchResponseMessage.GET_ALL_MATCH_SUCCESS.getMessage(),this.matchService.getAllMatchByHandicappedId(handicappedId)));
    }

    @ApiOperation(value="매칭 신청", notes="장애인이 매칭을 신청합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto> createMatch(@Valid @ModelAttribute MatchDto.GetAllResponse getAllResponse){
        System.out.println(getAllResponse.toEntity().get_id());
        this.matchService.createMatch(getAllResponse);
        return ResponseEntity.ok(ResponseDto.create(MatchConstants.EMatchResponseMessage.CREATE_MATCH_SUCCESS.getMessage()));
    }

//    @ApiOperation(value="매칭 삭제", notes="매칭을 삭제합니다")
//    @DeleteMapping
//    public ResponseEntity<ResponseDto> deleteMatch(@Valid @RequestBody MatchDto.DeleteMatch deleteMatch){
//        this.matchService.deleteMatch(deleteMatch);
//        return ResponseEntity.ok(ResponseDto.create(MatchConstants.EMatchResponseMessage.DELETE_MATCH_SUCCESS.getMessage()));
//    }
}

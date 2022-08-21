package com.example.handySub.domain.match.controller;

import com.example.handySub.domain.match.constant.MatchConstants;
import com.example.handySub.domain.match.dto.MatchDto;
import com.example.handySub.domain.match.service.MatchService;
import com.example.handySub.global.dto.ResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/match")
@Api(tags="Match API")
public class MatchController {

    private final MatchService matchService;

    @ApiOperation(value="매칭 신청 대기 목록 전체 조회", notes="비장애인의 매칭 신청 대기 목록을 조회합니다.")
    @GetMapping("/non/{startStation}/{finishStation}")
    public ResponseEntity<ResponseDto<List<MatchDto.GetAllNonMatch>>> getAllNonMatch(@PathVariable Long startStation, @PathVariable Long finishStation) {
            return ResponseEntity.ok(ResponseDto.create(MatchConstants.EMatchResponseMessage.GET_ALL_NON_MATCH_SUCCESS.getMessage(), this.matchService.getAllNonMatchByStation(startStation, finishStation)));
    }

    // matchController에 대한 test document 생성(서버 구현용)
    @GetMapping(value = "/test")
    public void test() {
        matchService.test();
    }

    @ApiOperation(value="매칭 신청 대기 목록 개별 조회", notes="비장애인의 매칭 신청 대기 목록을 개별 조회합니다.")
    @GetMapping("/non/{_id}")
    public ResponseEntity<ResponseDto<MatchDto.GetNonMatch>> getNonMatch(@PathVariable String _id) {
        return ResponseEntity.ok(ResponseDto.create(MatchConstants.EMatchResponseMessage.GET_NON_MATCH_SUCCESS.getMessage(), this.matchService.getNonMatchByID(_id)));

    }

    @ApiOperation(value="매칭 신청", notes="비장애인이 매칭을 신청합니다.")
    @PatchMapping("/non/apply")
    public ResponseEntity<ResponseDto> patchNonMatchApply(@RequestBody MatchDto.GetNonMatchApply getNonMatchApply) {
        this.matchService.patchNonMatchApply(getNonMatchApply);
        return ResponseEntity.ok(ResponseDto.create(MatchConstants.EMatchResponseMessage.PATCH_NON_MATCH_APPLY_SUCCESS.getMessage()));
    }


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

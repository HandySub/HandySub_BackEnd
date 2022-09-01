package com.example.handySub.domain.mypage.controller;

import com.example.handySub.domain.mypage.dto.MypageDto;
import com.example.handySub.domain.mypage.service.MypageService;
import com.example.handySub.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/mypage")
@Api(tags="Match API")
public class MypageController {

    private final MypageService mypageService;
    //객체로 <match 목록, 채팅 기록, 사용자 정보> 제공
    @ApiOperation(value="장애인 마이페이지 정보", notes="장애인 마이페이지에서 (1)리뷰를 남긴 매칭 목록, (2)채팅 기록, (3)사용자 정보룰 조회합니다.")
    @PostMapping("/handy")
    public ResponseEntity<ResponseDto<MypageDto.GetHandyInfo>> getMypage(@SessionAttribute("userId") String userId){ //ResponseEntity -> 클라이언트에게 응답을 보냄
        MypageDto.GetHandyInfo getHandyInfo = new MypageDto.GetHandyInfo();
        getHandyInfo.setMatchList(mypageService.GetAllMatchList(userId));
        return ResponseEntity.ok(ResponseDto.create("장애인의 마이페이지 정보를 조회하였습니다.", getHandyInfo));
    }

    @ApiOperation(value="비장애인 마이페이지 정보", notes="비장애인 마이페이지에서 (1)신청한 매칭 목록 중 완료된 것, (2)채팅 기록, (3)사용자 정보(+쿠키 정보)를 조회합니다.")
    @PostMapping("/nonandy")
    public ResponseEntity<ResponseDto<MypageDto.GetNonHandyInfo>> getNonMypage(@SessionAttribute("userId") String userId){
        MypageDto.GetNonHandyInfo getHandyInfo = new MypageDto.GetNonHandyInfo();
        getHandyInfo.setMatchList(mypageService.GetNonAllMatchList(userId));
        return ResponseEntity.ok(ResponseDto.create("비장애인의 마이페이지 정보를 조회하였습니다.", getHandyInfo));
    }

    /*@ApiOperation(value="장애 여부 변경", notes="장애 여부를 변경합니다.")
    @PostMapping("/updatehandy")
    public ResponseEntity<ResponseDto> UpdateHandicapped(){
        return ResponseEntity.ok(ResponseDto.create("장애 여부를 변경했습니다.", ))
    }*/
}

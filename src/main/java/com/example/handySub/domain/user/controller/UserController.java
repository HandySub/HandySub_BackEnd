package com.example.handySub.domain.user.controller;

import com.example.handySub.domain.jwt.JwtUtil;
import com.example.handySub.domain.user.collection.UserCollections;
import com.example.handySub.domain.user.dto.UserDto;
import com.example.handySub.domain.user.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
@Api(tags="User API")
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    MongoTemplate mongoTemplate;

    String COLLECTION_NAME = "users";

    @ApiOperation(value="회원가입",notes="회원가입합니다")
    @PostMapping("/signup-back")
    public ModelAndView createUser(@Valid @ModelAttribute UserDto userDto){ //자동으로 바인딩하려면 name값이 UserDto의 인스턴스 명들과 일치해야 함.
        String msg = ""; //프론트에 보낼 팝업창 메세지
        String url = "";
        Query query = new Query(new Criteria("email").is(userDto.getEmail())); //같은 이메일 이미 있으면
        List<UserDto> member_check = mongoTemplate.find(query, UserDto.class, COLLECTION_NAME);
        if(member_check.size()>0) { //중복 회원
                                    // -> 팝업창 띄우고 로그인 화면으로
            msg="이미 가입된 회원입니다.";
            url="/login";
        }else{ //회원가입 가능(중복 없음)
            msg="회원가입 성공";
            url="/main";
            userService.userInsert(userDto);
        }
        ModelAndView mav = new ModelAndView(url);
        mav.addObject("message",msg);
        return mav;
    }

    @ApiOperation(value="로그인", notes="로그인합니다")
    @PostMapping("/login-back")
    public ModelAndView login(@RequestBody String email,@RequestBody String pwd, HttpSession session){
        UserDto userDto = userService.loginCheck(email,pwd);
        String msg = ""; //프론트에 보낼 팝업창 메세지
        String url = "";
        if(userDto==null){ //로그인 실패
            msg="회원이 아닙니다.";
            url="/login";
        }else{ //로그인 성공
            msg="로그인 성공";
            url="/main";
            session.setAttribute("email",email);
            session.setAttribute("nickname",userDto.getNickname());
        }
        ModelAndView mav = new ModelAndView(url);
        mav.addObject("message",msg);
        return mav;
    }

    @ApiOperation(value="로그아웃", notes="로그아웃합니다")
    @PostMapping("/logout-back")
    public String logout(HttpSession session){
        session.invalidate(); //세션 초기화
        return "redirect:/main";
    }

    @ApiOperation(value="회원탈퇴", notes="회원 삭제합니다")
    @PostMapping("/deleteuser-back")
    public String deleteUser(String email){ //현재 로그인된 회원 탈퇴
        userService.userDelete(email);
        return "redirect:/main";
    }

    @ApiOperation(value="비밀번호 찾기", notes="비밀번호를 찾습니다")
    @PostMapping("/findpwd-back")
    public String findPwd(String email){
        //비밀번호 찾는 거 이메일로 비밀번호 전송하려고 하는데 어떤가요...?
        return "/login";
    }

    @ApiOperation(value="장애인 유저 정보 수정", notes="info를 수정합니다")
    @PostMapping("/updateinfo-back")
    public String updateInfo(String email, String newinfo){
        //이메일로 장애인인지 확인 후 정보 수정. -> 프론트에서 장애인인 경우에만 info가 나타나야 함.
        //백에서 확인할 필요 X
        userService.userInfoUpdate(email, newinfo);
        return "redirect:/mypage"; //수정 완료되면 마이페이지로 리디렉트
    }
}

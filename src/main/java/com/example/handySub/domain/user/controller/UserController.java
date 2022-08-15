package com.example.handySub.domain.user.controller;

import com.example.handySub.domain.user.dto.UserDto;
import com.example.handySub.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signUp_back")
    public String createUser(@ModelAttribute UserDto userDto){ //자동으로 바인딩하려면 name값이 UserDto의 인스턴스 명들과 일치해야 함.
        userService.userInsert(userDto);
        return "/login"; //회원가입 후 로그인 페이지로 돌아가기
    }

    @PostMapping("/login_back")
    public ModelAndView login(String email, String pwd, HttpSession session){
        UserDto userDto = userService.loginCheck(email,pwd);
        String msg = "";
        String url = "";
        if(userDto==null){ //로그인 실패
            msg="회원이 아닙니다.";
            url="/login";
        }else{ //로그인 성공
            url="/main";
            session.setAttribute("email",email);
            session.setAttribute("nickname",userDto.getNickname());
        }
        return new ModelAndView(url);
    }

    @PostMapping("/logout_back")
    public String logout(HttpSession session){
        session.invalidate(); //세션 초기화
        return "redirect:/main";
    }
}

package com.shop.controller;

import com.shop.dto.User;
import com.shop.service.KakaoService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("/oauth")
public class KakaoController {


    @Autowired
    KakaoService kakaoService;

    @Autowired
    UserService userService;

    @RequestMapping("/kakaologin")
    public String testlogin(){
        return "oauth/kakaologin";
    }

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public Object kakaoLogin(@RequestParam(value = "code", required = false) String code, Model model, HttpSession session) throws Exception {
        String access_Token = kakaoService.getAccessToken(code);
        HashMap<String, String> userInfo = kakaoService.getUserInfo(access_Token);
        model.addAttribute("userObj", userInfo);
        //유저 회원가입 확인
        User user = userService.get_id(userInfo.get("user_id"));
        System.out.println(user);
        if(user != null){
            session.setAttribute("loginuser", user);
            model.addAttribute("is_check", "true");
        }
        return "oauth/firstlogin";
    }
}

package com.shop.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.dto.User;
import com.shop.service.NaverService;
import com.shop.service.UserService;


@Controller
public class NaverController {
    @Autowired
    NaverService naverService;

    @Autowired
    UserService userService;

    @RequestMapping("/naverlogin")
    public String callback(Model model, String code, HttpSession session) throws Exception {
        System.out.println("callback 호출");
        String access_Token = naverService.getAccessToken(code);  //토큰 가져오기
        HashMap<String, String> userInfo = naverService.getUserInfo(access_Token);
        model.addAttribute("userObj", userInfo);
        User user = userService.get_id(userInfo.get("user_id"));
        if (user != null) {
            session.setAttribute("loginuser", user);
            model.addAttribute("is_check", "true");
        }
        return "oauth/firstlogin";
    }
}

package com.shop.controller;

import com.shop.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.HashMap;

@Controller
public class KakaoController {

    @Autowired
    KakaoService kakaoService;

    @RequestMapping(value="/oauth/login", method= RequestMethod.GET)
    public Object kakaoLogin(@RequestParam(value = "code", required = false) String code, Model model) throws Exception {
        String access_Token = kakaoService.getAccessToken(code);
        HashMap<String, String> userInfo = kakaoService.getUserInfo(access_Token);
        model.addAttribute("userObj", userInfo);
        return "oauth/login";
    }
}

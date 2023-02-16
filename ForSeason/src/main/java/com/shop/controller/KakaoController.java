package com.shop.controller;

import com.shop.dto.User;
import com.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class KakaoController {

    @Autowired
    KakaoService kakaoService;

    @Autowired
    UserService userService;

    @Autowired
    CouponService couponService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;


    @RequestMapping(value = "/kakaoLogin", method = RequestMethod.GET)
    public Object kakaoLogin(@RequestParam(value = "code", required = false) String code, Model model, HttpSession session) throws Exception {
        String access_Token = kakaoService.getAccessToken(code);
        HashMap<String, String> userInfo = kakaoService.getUserInfo(access_Token);
        model.addAttribute("userObj", userInfo);
        User user = userService.get_id(userInfo.get("user_id"));
        if (user != null) {
            session.setAttribute("loginUser", user);
            session.setAttribute("coupon", couponService.getList(user.getUser_no())); //user 즉시 sesstion 넣어주기
            session.setAttribute("cartList", cartService.get_list(user.getUser_no())); // test를 위한 sesstion 처리
            session.setAttribute("order", orderService.get_list(user.getUser_no()));
            session.setAttribute("od", orderDetailService.getODList(user.getUser_no()));
            return "redirect:/";
        }else{
            model.addAttribute("center", "user/register");
        }
        return "main";
    }
}

package com.shop.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.dto.User;


@Controller
public class NaverController {
    @Autowired
    NaverService naverService;

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

    @RequestMapping("/naverLogin")
    public String naverLogin(Model model, String code, HttpSession session) throws Exception {
        String access_Token = naverService.getAccessToken(code);  //토큰 가져오기
        HashMap<String, String> userInfo = naverService.getUserInfo(access_Token);
        model.addAttribute("userObj", userInfo);
        User user = userService.get_id(userInfo.get("user_id"));
        if (user != null) {
            session.setAttribute("loginUser", user);
            session.setAttribute("coupon", couponService.getList(user.getUser_no())); //user 즉시 sesstion 넣어주기
            session.setAttribute("cartList", cartService.get_list(user.getUser_no())); // test를 위한 sesstion 처리
            session.setAttribute("order", orderService.get_list(user.getUser_no()));
            session.setAttribute("od", orderDetailService.getODList(user.getUser_no()));
            return "redirect:/";
        }
        model.addAttribute("center", "user/register");
        return "main";
    }

}

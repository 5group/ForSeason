package com.shop.controller;

import com.shop.dto.*;
import com.shop.frame.CryptoUtil;
import com.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class MainController{

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderService orderService;



    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    CouponService couponService;

    @Autowired
    HttpSession session;

    // http://127.0.0.1/
    @RequestMapping("/")
    public String main() {
        return "main";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("center", "about");
        return "main";
    }

    @RequestMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("center", "contact");
        return "main";
    }
    /////////////////////////////

    @RequestMapping("/ordercomplete")
    public String ordercomplete(Model model) {
        model.addAttribute("center", "order/detail/mydetail");
        return "main";
    }

    ///////////////////////////////////////////////
    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("center", "user/register");
        return "main";
    }

    @RequestMapping("/myPage")
    public String info(Model model) throws Exception{
        model.addAttribute("center", "user/myPage");
        return "main";
    }

    ////////////////////  로그인 /////////////////////
    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("center", "oauth/login");
        return "main";
    }

    @RequestMapping(value = "/loginimpl", method = RequestMethod.POST)
    public String loginimpl(String id, String pwd, Model model) {
        User user = null;
        String result = "user/loginfail";
        try {
            user = userService.get_id(id);
            if (user != null) {
                if (user.getUser_pwd().equals(CryptoUtil.encryptAES256(pwd, "123456testsogood"))) {
                    result = "user/loginok";
                    session.setAttribute("loginUser", user);
                    session.setAttribute("coupon", couponService.getList(user.getUser_no())); //user 즉시 sesstion 넣어주기
                    session.setAttribute("cartList", cartService.get_list(user.getUser_no())); // test를 위한 sesstion 처리
                    session.setAttribute("order", orderService.get_list(user.getUser_no()));
                    session.setAttribute("od", orderDetailService.getODList(user.getUser_no()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("center", result);
        return "main";
    }

    @RequestMapping("/logout")
    public String logout() {
        if (session != null) {
            session.invalidate();
        }
        return "main";
    }

    @RequestMapping(value = "/find_info", method = RequestMethod.GET)
    public String find_info(Model model) {
        model.addAttribute("center", "/user/findinfo");
        return "main";
    }

}
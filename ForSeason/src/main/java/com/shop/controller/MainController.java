package com.shop.controller;

import com.shop.dto.Coupon;
import com.shop.dto.User;
import com.shop.service.CouponService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    CouponService couponService;
    // http://127.0.0.1/
    @RequestMapping("/")
    public String main() {
        return "main";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("center", "login");
        return "main";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        if(session != null){
            session.invalidate();
        }
        return "main";
    }

    @RequestMapping("/loginimpl")
    public String loginimpl(HttpSession session, String id, String pwd, Model model) {
        User user = null;
        String result = "loginfail";
        try {
            user = userService.get_id(id);
            if (user != null) {
                if (user.getUser_pwd().equals(pwd)) {
                    result = "loginok";
                    session.setAttribute("loginuser", user);
                    List<Coupon> list = couponService.getList(user.getUser_no());
                    session.setAttribute("coupon", list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("center", result);
        return "main";
    }

}
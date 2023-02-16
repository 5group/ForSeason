package com.shop.controller;

import com.shop.dto.User;
import com.shop.frame.CryptoUtil;
import com.shop.service.CouponService;
import com.shop.service.MailService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    String dir = "user/";

    @Autowired
    UserService userService;

    @Autowired
    CouponService couponService;

    @Autowired
    HttpSession session;

    @Autowired
    MailService mailService;

    @RequestMapping("")
    public String main(Model model) {
        model.addAttribute("center", dir + "center");
        return "main";
    }

    @RequestMapping("/register")
    public String register(Model model, User user) {
        User userResult = null;
        try {
            if (userService.get_id(user.getUser_id()) == null || userService.get_id(user.getUser_id()).equals("")) {
                user.setUser_pwd(CryptoUtil.encryptAES256(user.getUser_pwd(), "123456testsogood"));
                userService.register(user);
                //(user.getUser_no());
                couponService.firstCoupon(user.getUser_no());
                model.addAttribute("center", dir + "registerok");
                userResult = userService.get_id(user.getUser_id());
                model.addAttribute("obj", userResult);
                return "redirect:/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("center", dir + "registerfail");
        }

        return "main";
    }

    @RequestMapping("/myPage/change_pwd")
    public String get(@SessionAttribute("loginUser") User user, Model model) {
        model.addAttribute("user", user);
        return dir + "/myPage/change_pwd";
    }


    @RequestMapping("/pwd_update")
    public String pwdUpdate(String user_pwd) {
        try {
            User user = (User) session.getAttribute("loginUser");
            user.setUser_id(user.getUser_id());
            user.setUser_pwd(CryptoUtil.encryptAES256(user_pwd, "123456testsogood"));
            userService.set_pwd(user);
            session.setAttribute("loginUser", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "main";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser() throws Exception {
        User user = (User) session.getAttribute("loginUser");
        userService.remove(user.getUser_id());
        session.invalidate();
        return "redirect:/";
    }

}
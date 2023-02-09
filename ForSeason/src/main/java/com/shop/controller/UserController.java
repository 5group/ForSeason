package com.shop.controller;

import com.shop.dto.User;
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
    public String register(Model model, User user, HttpSession session) {
        User userResult = null;
        try {
            if (userService.get_id(user.getUser_id()) == null || userService.get_id(user.getUser_id()).equals("")) {
                userService.register(user);
                model.addAttribute("center", dir + "registerok");
                userResult = userService.get_id(user.getUser_id());
                model.addAttribute("obj", userResult);
                session.setAttribute("loginUser", userResult);
                return "redirect:/";
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
            user.setUser_pwd(user_pwd);
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
    
   
    
//    @RequestMapping("/info_update")
//    public String infoUpdate(User info_user) {
//        try {
//            System.out.println(info_user);
//            User u = (User) session.getAttribute("loginUser");
//            u.setUser_id(u.getUser_id());
//            u.setUser_email(info_user.getUser_email());
//            u.setUser_name(info_user.getUser_name());
//            u.setUser_phone(info_user.getUser_phone());
//            u.setUser_address(info_user.getUser_address());
//            userService.modify(u);
//            session.setAttribute("loginUser", u);
//            System.out.println(session.getAttribute("loginUser"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "main";
//    }

}
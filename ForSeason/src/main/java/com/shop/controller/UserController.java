package com.shop.controller;

import com.google.gson.JsonObject;
import com.shop.dto.User;
import com.shop.service.KakaoService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("/user")
public class UserController {
    String dir = "user/";

    @Autowired
    UserService service;

    @Autowired
    HttpSession session;

    @RequestMapping("")
    public String main(Model model) {
        System.out.println("test");
        model.addAttribute("center", dir + "center");
        return "main";
    }

    @RequestMapping("/register")
    public String register(Model model, User user, HttpSession session) {
        User c = null;
        try {
            if (service.get_id(user.getUser_id()) == null || service.get_id(user.getUser_id()).equals("")) {
                service.register(user);
                model.addAttribute("center", dir + "registerok");
                c = service.get_id(user.getUser_id());
                model.addAttribute("obj", c);
                session.setAttribute("loginuser", c);
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("center", dir + "registerfail");
        }

        return "main";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("userInfo", null);
        return "oauth/firstlogin";
    }

    @RequestMapping("/info")
    public String info() {
        return dir + "info";
    }

    @RequestMapping("/change_pwd")
    public String get(@SessionAttribute("loginuser") User user, Model model) {
        model.addAttribute("user", user);
        return dir + "change_pwd";
    }

    @RequestMapping("change_info")
    public String infoUpdate(@SessionAttribute("loginuser") User user, Model model) {
        model.addAttribute("user", user);

        return dir + "change_info";
    }

    @RequestMapping("/pwd_update")
    public String pwdUpdate(String user_pwd) {
        try {
            User u = (User) session.getAttribute("loginuser");
            u.setUser_id(u.getUser_id());
            u.setUser_pwd(user_pwd);
            service.set_pwd(u);
            session.setAttribute("loginuser", u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "main";
    }

    @RequestMapping("info_update")
    public String infoUpdate(User info_user) {
        try {
            User u = (User) session.getAttribute("loginuser");
            u.setUser_id(u.getUser_id());
            u.setUser_email(info_user.getUser_email());
            u.setUser_name(info_user.getUser_name());
            u.setUser_phone(info_user.getUser_phone());
            u.setUser_address(info_user.getUser_address());
            service.modify(u);
            session.setAttribute("loginuser", u);
            System.out.println(session.getAttribute("loginuser"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "main";
    }

    @RequestMapping("/update")
    public String update(User user) {
        System.out.println(user);
        return "main";
    }
}
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
        User c = null;
        try {
            if (userService.get_id(user.getUser_id()) == null || userService.get_id(user.getUser_id()).equals("")) {
                userService.register(user);
                model.addAttribute("center", dir + "registerok");
                c = userService.get_id(user.getUser_id());
                model.addAttribute("obj", c);
                session.setAttribute("loginUser", c);
                return "redirect:/";
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

    @RequestMapping("/info/change_pwd")
    public String get(@SessionAttribute("loginUser") User user, Model model) {
        model.addAttribute("user", user);
        return dir + "/info/change_pwd";
    }

    @RequestMapping("/info/change_info")
    public String infoUpdate(@SessionAttribute("loginUser") User user, Model model) {
        model.addAttribute("user", user);
        return dir + "info/change_info";
    }

    @RequestMapping("/pwd_update")
    public String pwdUpdate(String user_pwd) {
        try {
            User u = (User) session.getAttribute("loginUser");
            u.setUser_id(u.getUser_id());
            u.setUser_pwd(user_pwd);
            userService.set_pwd(u);
            session.setAttribute("loginUser", u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "main";
    }

    @RequestMapping("/info_update")
    public String infoUpdate(User info_user) {
        try {
            System.out.println(info_user);
            User u = (User) session.getAttribute("loginUser");
            u.setUser_id(u.getUser_id());
            u.setUser_email(info_user.getUser_email());
            u.setUser_name(info_user.getUser_name());
            u.setUser_phone(info_user.getUser_phone());
            u.setUser_address(info_user.getUser_address());
            userService.modify(u);
            session.setAttribute("loginUser", u);
            System.out.println(session.getAttribute("loginUser"));
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

    @RequestMapping("/info/delete")
    public String delete() {
        return dir + "/info/delete_user";
    }

    @RequestMapping("/info/delete_user")
    public String deleteUser(String user_pwd) {
        System.out.println("test");
        User user = (User) session.getAttribute("loginUser");
        String pwd = user.getUser_pwd();
        if (pwd.equals(user_pwd)) {
            try {
                userService.remove(user.getUser_id());
                session.invalidate();
                return "redirect:/";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("test");
        return dir + "/info/delete_user";
    }

    @RequestMapping("/info/coupon_insert")
    public String insertCoupon() {
        return dir + "/info/coupon_insert";
    }

    @RequestMapping("/info/coupon")
    public String coupon() {
        return dir + "/info/coupon";
    }

    @RequestMapping("/info/myOrder")
    public String order() {
        return dir + "/info/myOrder";
    }

    @RequestMapping("/info/myOrderDetail?no={no}")
    public String orderDetail(@PathVariable("no") int no) {
        System.out.println("test:" + no);
        return "user/info/myOrderDetail?no=" + no;
    }

    @RequestMapping("/info/myOrderDetail")
    public String orderDDD() {
        return "user/info/myOrderDetail";
    }


}
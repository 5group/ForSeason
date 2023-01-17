package com.shop.controller;

import com.shop.dto.Coupon;
import com.shop.dto.User;
import com.shop.service.CouponService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import javax.servlet.http.HttpSession;
import java.util.List;

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
            if (userService.get_id(user.getUser_id()) == null || userService.get_id(user.getUser_id()).equals("")) {
                userService.register(user);
                model.addAttribute("center", dir + "registerok");
                c = userService.get_id(user.getUser_id());
                model.addAttribute("obj", c);
                session.setAttribute("loginuser", c);
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
    public String get(@SessionAttribute("loginuser") User user, Model model) {
        model.addAttribute("user", user);
        return dir + "/info/change_pwd";
    }

    @RequestMapping("/info/change_info")
    public String infoUpdate(@SessionAttribute("loginuser") User user, Model model) {
        model.addAttribute("user", user);

        return dir + "info/change_info";
    }

    @RequestMapping("/pwd_update")
    public String pwdUpdate(String user_pwd) {
        try {
            User u = (User) session.getAttribute("loginuser");
            u.setUser_id(u.getUser_id());
            u.setUser_pwd(user_pwd);
            userService.set_pwd(u);
            session.setAttribute("loginuser", u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "main";
    }

    @RequestMapping("/info_update")
    public String infoUpdate(User info_user) {
        try {
            User u = (User) session.getAttribute("loginuser");
            u.setUser_id(u.getUser_id());
            u.setUser_email(info_user.getUser_email());
            u.setUser_name(info_user.getUser_name());
            u.setUser_phone(info_user.getUser_phone());
            u.setUser_address(info_user.getUser_address());
            userService.modify(u);
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

    @RequestMapping("/info/delete")
    public String delete(){
        return dir + "/info/delete_user";
    }

    @RequestMapping("/info/delete_user")
    public String deleteUser(String user_pwd){
        System.out.println("test");
        User user = (User) session.getAttribute("loginuser");
        String pwd = user.getUser_pwd();
        if(pwd.equals(user_pwd)){
            try {
                userService.remove(user.getUser_id());
                session.invalidate();
                return "redirect:/";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("test");
        return dir+"/info/delete_user";
    }

    @RequestMapping("/info/coupon")
    public String selectCoupon(){
        try {
            List<Coupon> list = (List<Coupon>) session.getAttribute("coupon");
            for(Coupon coupon : list) System.out.println(coupon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dir + "/info/coupon";
    }

    @RequestMapping("/info/coupon_insert")
    public String insertCoupon(){
        return dir +"/info/coupon_insert";
    }

    @RequestMapping("/info/createCoupon")
    public String createCoupon(@RequestParam String cou_name, @RequestParam int cou_price, @RequestParam int user_no){
        Coupon coupon = new Coupon();
        User user = new User();
        System.out.println(coupon);
        try {
            coupon.setCou_name(cou_name);
            coupon.setCou_price(cou_price);
            coupon.setUser_no(user_no);
            user.setUser_no(user_no);
            couponService.register(coupon);
            List<Coupon> list = couponService.getList(user.getUser_no());
            session.setAttribute("coupon", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "main";
    }
}
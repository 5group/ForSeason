package com.admin.controller;

import com.admin.dto.Coupon;
import com.admin.dto.User;
import com.admin.service.CouponService;
import com.admin.service.MailService;
import com.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/userInfo")
public class userInfoController {

    @Autowired
    UserService userService;

    @Autowired
    CouponService couponService;

    @Autowired
    MailService mailService;

    @Autowired
    HttpSession session;

    String dir = "userInfo/";

    @RequestMapping("")
    public String main(Model model) throws Exception {
        List<User> userList = userService.get();
        model.addAttribute("userList", userList);
        model.addAttribute("center", dir + "centerUser");
        return "index";
    }

    @RequestMapping(value = "/createCoupon", method = RequestMethod.POST)
    public String createCoupon(@RequestParam String cou_name, @RequestParam int cou_price, @RequestParam(value = "noList[]") List<Integer> noList) throws Exception {
        Coupon coupon = new Coupon();
        for (Integer no : noList) {
            coupon.setCou_name(cou_name);
            coupon.setCou_price(cou_price);
            coupon.setUser_no(no);
            couponService.register(coupon);
        }
        return "index";
    }

    @RequestMapping(value = "/pushMail")
    public String pushMail(@RequestParam String subMessage, @RequestParam String textMessage, @RequestParam(value = "mailList[]") List<String> mailList) {
        mailService.sendMailToMultipleRecipients(mailList, subMessage, textMessage);
        return "index";
    }
}

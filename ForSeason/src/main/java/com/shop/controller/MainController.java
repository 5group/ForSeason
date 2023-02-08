package com.shop.controller;

import com.shop.dto.*;
import com.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderService orderService;

    @Autowired
    MailService mailService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    CouponService couponService;

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
    public String info(Model model) {
        model.addAttribute("center", "user/myPage");
        return "main";
    }

    ////////////////////  로그인 /////////////////////
    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("center", "oauth/login");
        return "main";
    }

    //@RequestMapping("/loginimpl")
    @RequestMapping(value = "/loginimpl", method = RequestMethod.POST)
    public String loginimpl(HttpSession session, String id, String pwd, Model model) {
        System.out.println("haha");
        User user = null;
        String result = "user/loginfail";
        try {
            user = userService.get_id(id);
            if (user != null) {
                if (user.getUser_pwd().equals(pwd)) {
                    result = "user/loginok";
                    List<Coupon> coupon_list = couponService.getList(user.getUser_no());
                    List<Cart> cart_list = cartService.get_list(user.getUser_no());
                    List<Order> order_list = orderService.get_list(user.getUser_no());
                    List<OrderDetail> od_list = orderDetailService.getODList(user.getUser_no());
                    session.setAttribute("loginUser", user);
                    session.setAttribute("coupon", coupon_list); //user 즉시 sesstion 넣어주기
                    session.setAttribute("cartList", cart_list); // test를 위한 sesstion 처리
                    session.setAttribute("order", order_list);
                    session.setAttribute("od", od_list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("center", result);
        return "main";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "main";
    }

    @RequestMapping(value = "/find_pwd",method = RequestMethod.GET)
    public String find_pwd(Model model){
        model.addAttribute("center", "/user/findpwd");
        return "main";
    }

    @RequestMapping(value = "/find_pwd", method = RequestMethod.POST)
    public String find_pwd(Model model, String userId, String userPhone, String toEmail){
        User findUser = null;
        try {
            findUser = userService.get_id(userId);
            if(findUser != null&&findUser.getUser_phone().equals(userPhone)){
                User user = new User();
                user.setUser_id(findUser.getUser_id());
                String subMessage = findUser.getUser_name() + "님의 비밀번호가 변경되었습니다.";
                String pwd = mailService.setMailPwd();
                mailService.sendMail(toEmail, subMessage, "password:"+pwd);
                user.setUser_pwd(pwd);// 암호화할때 암호화 해야함..
                userService.set_pwd(user);
                model.addAttribute("center", "center");
                return "main";
            }
        } catch (Exception e) {
            System.out.println("정상적이지 않습니다.");
        }
        return "/find_pwd";
    }

}
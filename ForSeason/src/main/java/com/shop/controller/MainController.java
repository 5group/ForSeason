package com.shop.controller;

import com.shop.dto.*;
import com.shop.service.*;
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
    CartService cartService;

    @Autowired
    OrderService orderService;

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
	
	@RequestMapping("/cart")
    public String cart(Model model) {
		model.addAttribute("center", "cart/cart");
		return "main";
    }
	
	@RequestMapping("/order")
    public String order(Model model) {
		model.addAttribute("center", "order/checkout");
		return "main";
    }
	
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
    
    @RequestMapping("/info")
    public String info(Model model) {
		model.addAttribute("center", "user/info");
        return "main";
	}
	////////////////////  로그인 /////////////////////
    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("center", "oauth/login");
        return "main";
    }

    @RequestMapping("/loginimpl")
    public String loginimpl(HttpSession session, String id, String pwd, Model model) {
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
                    session.setAttribute("loginuser", user);
                    session.setAttribute("coupon", coupon_list); //user 즉시 sesstion 넣어주기
                    session.setAttribute("cart", cart_list); // test를 위한 sesstion 처리
                    session.setAttribute("order", order_list);
                    session.setAttribute("od", od_list);
                    //1234567890 cartList -> copy_cartList(javascript) -> new cartList()
                    // 대권님 -> 장바구니
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
        if(session != null){
            session.invalidate();
        }
        return "main";
    }

}
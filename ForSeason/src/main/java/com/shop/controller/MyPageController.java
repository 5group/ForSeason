package com.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.shop.dto.Item;
import com.shop.dto.Stock;
import com.shop.dto.User;
import com.shop.dto.WishList;
import com.shop.service.ItemService;
import com.shop.service.UserService;
import com.shop.service.WishListService;

@Controller
public class MyPageController {
	
	@Autowired
    HttpSession session;
	
	@Autowired
    UserService userService;
	
	@Autowired
    ItemService itemService;
	
	@Autowired
    WishListService wishListService;
	
	@RequestMapping("/myOrder")
	public String myOrder(Model model, HttpSession session) {
    	model.addAttribute("center", "user/myPage");
    	model.addAttribute("infocenter", "user/myPage/myOrder");
        return "main";
    }
	
	@RequestMapping("/myOrderDetail")  
    public String orderDetail(@RequestParam("no") int no, Model model) {
        System.out.println("order_no:" + no);
        model.addAttribute("order_no", no);
        model.addAttribute("center", "user/myPage");
    	model.addAttribute("infocenter", "user/myPage/myOrderDetail");
        return "main";
    }
	
	@RequestMapping("/myCoupon")
    public String myCoupon(Model model) {
    	model.addAttribute("center", "user/myPage");
    	model.addAttribute("infocenter", "user/myPage/myCoupon");
        return "main";
    }
	
	@RequestMapping("/myReview")
    public String myReview(Model model) {
    	model.addAttribute("center", "user/myPage");
    	model.addAttribute("infocenter", "user/myPage/myReview");
        return "main";
    }
	
	@RequestMapping("/myQuestion")
    public String myQuestion(Model model) {
    	model.addAttribute("center", "user/myPage");
    	model.addAttribute("infocenter", "user/myPage/myQuestion");
        return "main";
    }
	
	@RequestMapping("/myWishList")
    public String myWishList(Model model, HttpSession session) throws Exception {
		User user = (User) session.getAttribute("loginUser");
		List<WishList> wish_list = wishListService.get_list(user.getUser_no());
				
        List<Item> itemList = new ArrayList<Item>();
        for(WishList wish : wish_list){
            Item item = itemService.get(wish.getItem_no());
            itemList.add(item);
        }
        model.addAttribute("item", itemList); 
        model.addAttribute("wish", wish_list);
        
        System.out.println(wish_list);
        System.out.println(itemList);
		
		
    	model.addAttribute("center", "user/myPage");
    	model.addAttribute("infocenter", "user/myPage/myWishList");
        return "main";
    }
	
	@RequestMapping("/changeMyInfo")
    public String changeMyInfo(@SessionAttribute("loginUser") User user, Model model) {
		model.addAttribute("user", user);
    	model.addAttribute("center", "user/myPage");
    	model.addAttribute("infocenter", "user/myPage/changeMyInfo");
        return "main";
    }
	
	@RequestMapping("/checkPwd")
    public String checkPwd(@SessionAttribute("loginUser") User user, Model model, @RequestParam("loc") String loc) {
		System.out.println(loc);
		model.addAttribute("user", user);
		model.addAttribute("loc", loc);
    	model.addAttribute("center", "user/myPage/checkPwd");
        return "main";
    }
	
	@RequestMapping("/deleteUserView")
    public String deleteUserView(@SessionAttribute("loginUser") User user, Model model) {
		System.out.println("deleteUserView");		
		model.addAttribute("user", user);
    	model.addAttribute("center", "user/myPage/deleteUser");
        return "main";
    }
	
	@RequestMapping("/changeMyInfoView")
    public String changeMyInfoView(@SessionAttribute("loginUser") User user, Model model) {
		System.out.println("changeMyInfoView");		
		model.addAttribute("user", user);
    	model.addAttribute("center", "user/myPage/changeMyInfo");
        return "main";
    }
	
	@RequestMapping("/changeMyPwdView")
    public String changeMyPwd(@SessionAttribute("loginUser") User user, Model model) {
		System.out.println("changeMyPwdView");		
		model.addAttribute("user", user);
    	model.addAttribute("center", "user/myPage/changeMyPwd");
        return "main";
    }
	
}

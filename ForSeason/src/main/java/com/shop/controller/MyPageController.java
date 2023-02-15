package com.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.shop.dto.Category;
import com.shop.dto.Item;
import com.shop.dto.Qna;
import com.shop.dto.Review;
import com.shop.dto.User;
import com.shop.dto.WishList;
import com.shop.frame.CryptoUtil;
import com.shop.service.CategoryService;
import com.shop.service.FileService;
import com.shop.service.ItemService;
import com.shop.service.QnaService;
import com.shop.service.ReviewService;
import com.shop.service.StockService;
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

    @Autowired
    QnaService qnaservice;

    @Autowired
    StockService stockservice;

    @Autowired
    ReviewService reviewservice;    
    
    @Autowired
    FileService fileService;
    
    @Autowired
    CategoryService categoryService;
    
    @Value("${custdir}")
    String custdir;    
    

    
    @RequestMapping("/myOrder")
    public String myOrder(Model model, HttpSession session) {
        model.addAttribute("center", "user/myPage");
        model.addAttribute("infocenter", "user/myPage/myOrder");
        return "main";
    }

    @RequestMapping("/myOrderDetail")
    public String orderDetail(@RequestParam("no") int no, Model model) {
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

    @RequestMapping("/myReview" )
    public String myReview(Model model, @SessionAttribute("loginUser") User user) throws Exception {
        Map<Integer, Category> allCateList = new HashMap<Integer, Category>();
        Map<Integer, String> titleImgList = new HashMap<Integer, String>();

        Item item = null;
        List<Review> reviewlist = null;
        Category category = null;
    	reviewlist = reviewservice.userselect(user.getUser_no());

    	for(Review r:reviewlist) {

    	category = itemService.getCategorys(r.getItem_no()); // 해당 아이템에 대중소 카테고리 이름불러옴 //item_no로 카테고리 다 알 수 있음
    	item = itemService.get(r.getItem_no());
    	
    	allCateList.put(r.getItem_no(), category);
        
    	String[] cateName = {category.getTop_cate_name(), category.getMid_cate_name(), category.getCate_name()};
        String[] imgnames = fileService.getFileList(custdir, category.getTop_cate_name(), category.getMid_cate_name(), category.getCate_name(), item.getItem_name());
        if (imgnames != null) {
            String titleImg = imgnames[0];
            titleImgList.put(r.getItem_no(), titleImg);
        	}
    	}
    	System.out.println(reviewlist);
        
    	model.addAttribute("item", item);  //타이틀 이미지 리스트(.jpg)
        model.addAttribute("titleImgList", titleImgList);  //타이틀 이미지 리스트(.jpg)
        model.addAttribute("allCateList", allCateList);
        model.addAttribute("reviewlist", reviewlist);
        model.addAttribute("center", "user/myPage");
        model.addAttribute("infocenter", "user/myPage/myReview");
        
        return "main";
    }
    
    @PostMapping("/getReview")
    @ResponseBody
    public Review getReview(@RequestParam("revNo") Integer rev_no) {
    	Review review = null;
    try {
		review = reviewservice.revnoselect(rev_no);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      System.out.println(review);
      return review;
    }
    
    @RequestMapping(value = "/deletereview")
    public String deleteReview(@RequestParam("rev_no") int rev_no, Model model) throws Exception {
    	System.out.println(rev_no);
    	reviewservice.remove(rev_no);
        model.addAttribute("center", "user/myPage");
        model.addAttribute("infocenter", "user/myPage/myReview");

        return "main";
    }

    @RequestMapping("/myQuestion")
    public String myQuestion(Model model, @SessionAttribute("loginUser") User user) throws Exception {
    	Qna qna = null;
    	List<Qna> qnalist = null;
    	qnalist = qnaservice.userselect(user.getUser_no());
    	System.out.println(qnalist);
    	
    	
    	model.addAttribute("qnalist", qnalist);
    	model.addAttribute("center", "user/myPage");
        model.addAttribute("infocenter", "user/myPage/myQuestion");
        return "main";
    }
    @RequestMapping("/qna/get")
    @ResponseBody
    public Qna getQna(@RequestParam("qnaNo") int qnaNo, Model model) {
        System.out.println(qnaNo);
    	
    	Qna qna = null;
    	try {
			qna = qnaservice.qnaselect(qnaNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(qna);
    	
        return qna;
    }

    
    
    
    @RequestMapping("/myWishList")
    public String myWishList(Model model, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("loginUser");
        List<WishList> wish_list = wishListService.get_list(user.getUser_no());
        List<Item> itemList = new ArrayList<Item>();
        Map<Integer, Category> allCateList = new HashMap<Integer, Category>();
        Map<Integer, String> titleImgList = new HashMap<Integer, String>();
        Category category = null;
        
        for (WishList wish : wish_list) {
            Item item = itemService.get(wish.getItem_no());
            itemList.add(item);
            
            category = itemService.getCategorys(wish.getItem_no()); // 해당 아이템에 대중소 카테고리 이름불러옴 //item_no로 카테고리 다 알 수 있음
        	
        	allCateList.put(wish.getItem_no(), category);
            
        	String[] cateName = {category.getTop_cate_name(), category.getMid_cate_name(), category.getCate_name()};
            String[] imgnames = fileService.getFileList(custdir, category.getTop_cate_name(), category.getMid_cate_name(), category.getCate_name(), item.getItem_name());
            if (imgnames != null) {
                String titleImg = imgnames[0];
                titleImgList.put(wish.getItem_no(), titleImg);
            }
        }
            
        model.addAttribute("titleImgList", titleImgList);  //타이틀 이미지 리스트(.jpg)
        model.addAttribute("allCateList", allCateList);
        model.addAttribute("item", itemList);
        model.addAttribute("wish", wish_list);
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
        model.addAttribute("user", user);
        model.addAttribute("loc", loc);
        model.addAttribute("center", "user/myPage/checkPwd");
        return "main";
    }

    @RequestMapping("/deleteUserView")
    public String deleteUserView(@SessionAttribute("loginUser") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("center", "user/myPage/deleteUser");
        return "main";
    }

    @RequestMapping("/changeMyInfoView")
    public String changeMyInfoView(@SessionAttribute("loginUser") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("center", "user/myPage/changeMyInfo");
        return "main";
    }

    @RequestMapping("/changeMyPwdView")
    public String changeMyPwd(@SessionAttribute("loginUser") User user, Model model) throws Exception{
        user.setUser_pwd(CryptoUtil.decryptAES256(user.getUser_pwd(),"123456testsogood"));
        model.addAttribute("user", user);
        model.addAttribute("center", "user/myPage/changeMyPwd");
        return "main";
    }

}

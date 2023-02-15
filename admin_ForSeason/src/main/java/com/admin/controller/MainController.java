package com.admin.controller;

import com.admin.dto.Admin;
import com.admin.dto.Qna;
import com.admin.dto.Reply;
import com.admin.dto.Review;
import com.admin.dto.Stock;
import com.admin.dto.User;
import com.admin.service.AdminService;
import com.admin.service.QnaService;
import com.admin.service.ReplyService;
import com.admin.service.ReviewService;
import com.admin.service.StockService;
import com.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    StockService stockService;

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;
    
	@Autowired
	QnaService qnaservice;

	@Autowired
	ReplyService replyservice;

	@Autowired
	ReviewService reviewservice;
		
    
    
    @RequestMapping("/")
    public String index(Model model) throws Exception {
        if(session.getAttribute("adminLogin") == null){
            return "redirect:/login";
        }
        //model.addAttribute("center2", "/chartList/center");
        //List<Stock> stockList = stockService.getAdminMainList();
        //model.addAttribute("stockList", stockList);
        //return "main";
        return "index";
    }
    
    @RequestMapping("/orderList")
    public String blank(Model model) throws Exception{
    	model.addAttribute("center", "/orderList/orderList");
    	List<Stock> stockList = stockService.getAdminMainList();
        model.addAttribute("stockList", stockList);
    	return "index";
    }
    
    @RequestMapping("/login")
    public String login() {
        //return "admin/login";
        return "admin/index-login";
    }
    
    @RequestMapping(value = "/checkLogin")
    public String checkLogin(String id, String pwd) throws Exception {
        Admin admin = adminService.get(id);
        System.out.println(admin);
        if (admin != null && admin.getAdmin_password().equals(pwd)) {
            session.setAttribute("adminLogin", admin);
            return "redirect:/";
        }
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String logout(){
        session.invalidate();
        return "redirect:/";
    }
    
	@RequestMapping("/userQuestion")
	public String userQuestion(Model model) throws Exception {

		List<Qna> qnaList = null;
		qnaList = qnaservice.userselect();
		System.out.println(qnaList);
		model.addAttribute("qnalist", qnaList);
		model.addAttribute("center", "userQuestion/qnaList");
		return "index";
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

	@RequestMapping(value = "/qnaInsert", method = RequestMethod.POST)
	public String qnaInsert(int qna_no, String rep_content) throws Exception {
//        Admin ad = (Admin)session.getAttribute("adminLogin");
//        if (ad == null) {
//            System.out.println("ad is null");
//            return 0;
//        }
		Reply reply = new Reply(qna_no, "admin01", rep_content, null);
		System.out.println(reply);
		replyservice.register(reply);
		return "index";
	}

	@RequestMapping(value = "/qna/modify", method = RequestMethod.POST)
	public String updatereply(Reply reply) throws Exception {
		System.out.println("컨트롤러 시작");

		reply.setRep_date(new Date());

		System.out.println(reply);
		replyservice.modify(reply);
		return "redirect:/qna";
	}

	@RequestMapping(value = "/qna/delete", method = RequestMethod.POST)
	public String deleteQna(@RequestParam(name = "qnaNo") int qnaNo) {
		System.out.println(qnaNo);
		try {
			qnaservice.remove(qnaNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/qna";
	}
	
	
	@RequestMapping("/userReview")
	public String userReview(Model model) throws Exception {

		List<Review> revList = null;
		revList = reviewservice.revselect();
		System.out.println(revList);
		model.addAttribute("revlist", revList);
		model.addAttribute("center", "userReview/revList");
		return "index";
	}

	@RequestMapping("/rev/get")
	@ResponseBody
	public Review getRev(@RequestParam("revNo") int revNo, Model model) {
		System.out.println(revNo);

		Review review = null;
		try {
			review = reviewservice.revnoselect(revNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(review);

		return review;
	}

	@RequestMapping(value = "/rev/delete", method = RequestMethod.POST)
	public String deleteRev(@RequestParam(name = "revNo") int revNo) {
		System.out.println(revNo);
		try {
			reviewservice.remove(revNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/qna";
	}
    
    
    //main -> index
    @RequestMapping("/main")
    public String main(Model model) throws Exception {
        model.addAttribute("center2", "/chartList/center");
        List<Stock> stockList = stockService.getAdminMainList();
        model.addAttribute("stockList", stockList);
        return "main";
    }
}
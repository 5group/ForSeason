package com.shop.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shop.dto.Cart;
import com.shop.dto.Coupon;
import com.shop.dto.Item;
import com.shop.dto.Review;
import com.shop.dto.Size;
import com.shop.dto.Stock;
import com.shop.dto.User;
import com.shop.dto.WishList;
import com.shop.service.CartService;
import com.shop.service.ColorService;
import com.shop.service.ItemService;
import com.shop.service.ReviewService;
import com.shop.service.StockService;
import com.shop.service.UserService;
import com.shop.service.WishListService;

@RestController
public class DataController {

    @Value("${custdir}")
    String custdir;

    @Autowired
    HttpSession session;

    @Autowired
    StockService stockService;

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @Autowired
    ColorService colorService;

    @Autowired
    UserService userService;

    @Autowired
    WishListService wishListService;

    @Autowired
    ReviewService reviewService;

    @RequestMapping(value = "/couponList", method = RequestMethod.GET)
    public List<Coupon> coupon_list() {
        List<Coupon> list = (List<Coupon>) session.getAttribute("coupon");
        if (list == null) {
            return null;
        }
        return list;
    }

    public List<HashMap<String, Object>> requiredOrderMap(List<Cart> cartList) throws Exception {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        for (Cart cart : cartList) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            int cart_cnt = cart.getCart_cnt();
            Stock stock = stockService.get(cart.getStock_no());
            int item_no = stock.getItem_no();
            Item item = itemService.get(item_no);
            map.put("cart_cnt", cart_cnt);
            map.put("item_name", item.getItem_name());
            map.put("item_price", item.getItem_price());
            map.put("item_discnt", item.getItem_discnt());
            map.put("stock", stock);
            list.add(map);
        }
        return list;
    }

    @RequestMapping("test")
    public ModelAndView item_list() throws Exception {
        List<Cart> list = (List<Cart>) session.getAttribute("cart");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/order/index");
        modelAndView.addObject("maplist", requiredOrderMap(list));
        return modelAndView;
    }

    @RequestMapping(value = "/orderByCartList", method = RequestMethod.POST)
    public void orderByCartList(@RequestParam(value = "cartList[]") List<Integer> cartList, @RequestParam(value = "cartCntList[]") List<Integer> cartCntList) throws Exception {
        List<Cart> orderCartList = new ArrayList<Cart>();
        for (int i = 0; i < cartList.size(); i++) {
            Cart cart = new Cart();
            cart.setCart_no(cartList.get(i));
            cart.setCart_cnt(cartCntList.get(i));
            cartService.modify(cart);
            orderCartList.add(cartService.get(cartList.get(i)));
        }
        session.setAttribute("orderCartList", orderCartList);
    }

    @RequestMapping(value = "/insertWish", method = RequestMethod.GET)
    public void insertWish(int item_no) throws Exception {
        User u = (User) session.getAttribute("loginUser");
        WishList wishList = new WishList(0, u.getUser_no(), item_no);
        wishListService.register(wishList);
    }

    @RequestMapping(value = "/deleteWish", method = RequestMethod.GET)
    public void deleteWish(int item_no) throws Exception {
        User u = (User) session.getAttribute("loginUser");
        WishList wishList = new WishList(0, u.getUser_no(), item_no);
        wishListService.deleteUserWish(wishList);
    }

    @RequestMapping("/cartInsert")
    public Object cartinsert(int item_no, int color_no, int size_no, int cart_cnt) throws Exception {
        int result = 0;
        User user = (User) session.getAttribute("loginUser");
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("item_no", item_no);
        map.put("color_no", color_no);
        map.put("size_no", size_no);
        int stock_no = stockService.getStockNo(map);
        Cart cart = new Cart(0, stock_no, user.getUser_no(), cart_cnt, null);
        Cart checkCart = cartService.checkCartList(cart);
        if (checkCart != null) { //카트에 이미 담겨있으면 카트DB에 넣지 말고 return하기
            result = 1;
            return result;
        }
        cartService.register(cart);
        List<Cart> cart_list = cartService.get_list(user.getUser_no());
        session.setAttribute("cartList", cart_list); // test를 위한 sesstion 처리
        System.out.println(user.getUser_no());
        return result;
    }

    @RequestMapping("/getStock")
    public Object getStock(int item_no, int color_no, int color_index, @RequestParam(value = "CateArr[]") String[] CateArr) throws Exception {  //현재 선택한 컬러의 이미지파일 가져가면 되지 않나..?
        JSONObject jo = new JSONObject();
        List<Size> colorStock = stockService.getStock(item_no, color_no);   //현재 존재하는 재고만(사이즈) 리스트 불러오기 ex) 노랑색은 xs,s 분홍색은 xs만 있을 수 있으니깐
        Item item = itemService.get(item_no); //해당 아이템 이름
        String item_name = item.getItem_name();
        String src = CateArr[0] + "/" + CateArr[1] + "/" + CateArr[2] + "/" + item_name;
        File dir = new File(custdir + src);
        String imgnames[] = dir.list();
        jo.put("imgsrc", imgnames[color_index]);
        jo.put("colorStock", colorStock);
        return jo;
    }

    @RequestMapping("/checkUserPwd")
    public Object checkPwd(String user_pwd, Model model) throws Exception {
        int result = 0;

        User user = (User) session.getAttribute("loginUser");
        String pwd = user.getUser_pwd();
        if (pwd.equals(user_pwd)) {
            result = 1;
        }
        return result;
    }

    @RequestMapping(value = "/reviewInsert", method = RequestMethod.POST)
    public Object reviewInsert(int item_no, String rev_title, String rev_content, double rev_score) throws Exception {
        int result = 0;
        User user = (User) session.getAttribute("loginUser");
        Review review = new Review(user.getUser_no(), item_no, rev_title, rev_content, rev_score);
        reviewService.register(review);
        return result;
    }

    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public void infoUpdate(User info_user) throws Exception {
        User u = (User) session.getAttribute("loginUser");
        u.setUser_id(u.getUser_id());
        u.setUser_email(info_user.getUser_email());
        u.setUser_name(info_user.getUser_name());
        u.setUser_phone(info_user.getUser_phone());
        u.setUser_address(info_user.getUser_address());
        userService.modify(u);
        session.setAttribute("loginUser", u);
    }
}

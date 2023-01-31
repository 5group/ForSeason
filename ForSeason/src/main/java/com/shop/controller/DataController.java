package com.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shop.dto.Cart;
import com.shop.dto.Coupon;
import com.shop.dto.Item;
import com.shop.dto.Stock;
import com.shop.service.CartService;
import com.shop.service.ItemService;
import com.shop.service.StockService;

@RestController
public class DataController {

    @Autowired
    HttpSession session;

    @Autowired
    StockService stockService;

    @Autowired
    ItemService itemService;
    
    @Autowired
    CartService cartService;

    @RequestMapping(value = "/couponList", method = RequestMethod.GET)
    public List<Coupon> coupon_list() {
        List<Coupon> list = (List<Coupon>) session.getAttribute("coupon");
        if(list == null){
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
        System.out.println(list);
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
    
    @RequestMapping("/cartinsert")
   	public Object cartinsert(int item_no, int color_no, int size_no, int cart_cnt) {
   		int result=0;
   		//System.out.println("cartinsert");
   		System.out.println(item_no+", "+color_no+", "+size_no+", "+cart_cnt);
   		//장바구니 DB 넣기
   		int user_no=1;
   		
   		
   		//(item객체랑 사이즈객체랑 색상객체를 넣어줘서 한꺼번에) 
   		HashMap<String, Integer> map = new HashMap<String, Integer>(); 
   		map.put("item_no", item_no);
   		map.put("color_no", color_no);
   		map.put("size_no", size_no);
   		try {
   			int stock_no=stockService.getstockno(map);
   			Cart cart = new Cart(0, stock_no, user_no, cart_cnt, null);
   			//System.out.println(stock_no);
   			cartService.register(cart);
   		} catch (Exception e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   				
   		return result;
   	}
}

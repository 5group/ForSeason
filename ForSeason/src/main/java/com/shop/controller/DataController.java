package com.shop.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.shop.dto.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shop.service.CartService;
import com.shop.service.ColorService;
import com.shop.service.ItemService;
import com.shop.service.StockService;

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
        System.out.println(orderCartList);
        session.setAttribute("orderCartList", orderCartList);
    }

    @RequestMapping("/cartInsert")
    public Object cartinsert(int item_no, int color_no, int size_no, int cart_cnt) {
        int result = 0;
        //System.out.println("cartinsert");
        System.out.println(item_no + ", " + color_no + ", " + size_no + ", " + cart_cnt);
        //장바구니 DB 넣기
        int user_no = 1;


        //(item객체랑 사이즈객체랑 색상객체를 넣어줘서 한꺼번에)
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("item_no", item_no);
        map.put("color_no", color_no);
        map.put("size_no", size_no);
        try {
            int stock_no = stockService.getStockNo(map);
            Cart cart = new Cart(0, stock_no, user_no, cart_cnt, null);
            //System.out.println(stock_no);
            cartService.register(cart);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping("/getStock")
    public Object getStock(int item_no, int color_no, int color_index, @RequestParam(value = "CateArr[]") String[] CateArr) throws Exception {  //현재 선택한 컬러의 이미지파일 가져가면 되지 않나..?
        JSONObject jo = new JSONObject();

        List<Size> colorStock = stockService.getStock(item_no, color_no);   //현재 존재하는 재고만(사이즈) 리스트 불러오기 ex) 노랑색은 xs,s 분홍색은 xs만 있을 수 있으니깐

        Item item = itemService.get(item_no); //해당 아이템 이름
        String item_name = item.getItem_name();

        Color color = colorService.get(color_no);

        String src = CateArr[0] + "/" + CateArr[1] + "/" + CateArr[2] + "/" + item_name;
        File dir = new File(custdir + src);
        String imgnames[] = dir.list();   //아이템 이미지파일들의 이름 리스트로 저장

//		for (int i = 0; i < imgnames.length; i++) {
//		    System.out.println("imgnames : "+imgnames[i]);
//		}
//    	System.out.println("최종:"+imgnames[color_index]);
        jo.put("imgsrc", imgnames[color_index]);
        jo.put("colorStock", colorStock);

        return jo;
    }
}

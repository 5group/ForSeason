package com.shop.controller;

import com.shop.dto.*;
import com.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ItemService itemService;

    @Autowired
    HttpSession session;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    StockService stockService;

    @Autowired
    CouponService couponService;

    String dir = "order/";

    @RequestMapping("/order/kakaopay")
    public String kakaopay(Model model) throws Exception {
        List<Cart> list = (List<Cart>) session.getAttribute("cart");
        Item item = null;
        int tot_price = 0;
        int index = 0;
        int item_count = 0;
        String text = "";
        for (Cart cart : list) {
            Stock stock = stockService.get(cart.getStock_no());
            item_count = cart.getCart_cnt();
            item = itemService.get(stock.getItem_no());
            if (index == 0) text += item.getItem_name();
            tot_price += item.getItem_price() * item_count;
            index += 1;
        }
        if (index == 1) {
            text = String.format(text + "같은 %d개 제품", item_count);
            setItemModel(text, tot_price, model);
        } else {
            text = String.format(text + "외%d개 제품", item_count - 1);
            setItemModel(text, tot_price, model);
        }
        return dir + "kakaopay";
    }

    public void setItemModel(String text, int tot_price, Model model) {
        model.addAttribute("item_name", text);
        model.addAttribute("item_tot", tot_price);
    }

    @RequestMapping("/order/order_detail")
    public String orderDetail(@RequestBody Map<String, Object> params) throws Exception {
        User user = (User)session.getAttribute("loginuser");
        Order order = orderService.createItemsByOrder(params, user.getUser_no());// 주문DB 만들어 주고 주문 반환
        successOrder(order.getOrder_no()); // 반환받은 주문의 넘버를 받아서 detail에 넣어줌 - 재고에서 재고업데이트
        String coupon_no =(String) params.get("coupon_no");
        if(!Objects.equals(coupon_no, "0") && !Objects.equals(coupon_no, "null")) couponService.useCoupon(Integer.parseInt(coupon_no));//쿠폰 상태 변환
        return "main";
    }

    // 서비스에 있어야하나 지금은 애매하여 일단둠
    public void successOrder(int order_no) throws Exception {
        List<Cart> list = (List<Cart>) session.getAttribute("cart");
        for (Cart cart : list) {
            OrderDetail od = new OrderDetail();
            Stock stock = stockService.get(cart.getStock_no());
            Item item = itemService.get(stock.getItem_no());
            int stock_no = cart.getStock_no();
            int cnt = cart.getCart_cnt();
            int price = item.getItem_price();
            int discnt = item.getItem_discnt();
            orderDetailService.createOrderDetail(order_no, stock_no, cnt, price, discnt);
            stockService.setAmount(stock_no, cnt);
        }
    }
}

package com.shop.controller;

import com.shop.dto.*;
import com.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @Autowired
    CartService cartService;

    String dir = "order/";

    @RequestMapping("/order")
    public String order(Model model) throws Exception {
        List<Cart> cartList = (List<Cart>) session.getAttribute("orderCartList");
        Item item = null;
        int tot_price = 0;
        int index = 0;
        int item_count = 0;
        String text = "";
        List<Item> itemList = new ArrayList<Item>();
        List<Stock> stockList = new ArrayList<Stock>();
        for (Cart cart : cartList) {
            Stock stock = stockService.getColorSizeName(cart.getStock_no());// cart get.stock ->
            item_count = cart.getCart_cnt();// cart_no -> stock_no -> color name, size_name
            item = itemService.get(stock.getItem_no()); // 상품 <- -> 재고
            if (index == 0) text += item.getItem_name();
            tot_price += item.getItem_price() * item_count;
            index += 1;
            itemList.add(item);
            stockList.add(stock);
        }
        text = String.format(text + "외%d개 제품", cartList.size() - 1);
        if (index == 1) text = String.format(text + "같은 제품%d개", item_count);
        model.addAttribute("stockList", stockList); // 사이즈 색상(리스트)
        model.addAttribute("center", "order/checkout"); //
        model.addAttribute("cartList", cartList); //session으로 쓸생각 고려
        model.addAttribute("itemList", itemList); // 이름, 가격, 할인율
        model.addAttribute("item_name", text);
        model.addAttribute("item_tot", tot_price);
        return "main";
    }

    @RequestMapping("/order/order_detail")
    public String orderDetail(@RequestBody Map<String, Object> params) throws Exception {
        List<Cart> list = (List<Cart>) session.getAttribute("orderCartList");
        User user = (User) session.getAttribute("loginUser");
        Order order = orderService.createItemsByOrder(params, user.getUser_no());// 주문DB 만들어 주고 주문 반환
        successOrder(order.getOrder_no(), list); // 반환받은 주문의 넘버를 받아서 detail에 넣어줌 - 재고에서 재고업데이트
        String coupon_no = (String) params.get("coupon_no");
        if (!Objects.equals(coupon_no, "0") && !Objects.equals(coupon_no, "null"))
            couponService.useCoupon(Integer.parseInt(coupon_no));//쿠폰 상태 변환
        List<Cart> cartList = cartService.setCartList(user.getUser_no(), list);
        session.setAttribute("cartList", cartList);
        System.out.println(cartList);
        
        List<Order> order_list = orderService.get_list(user.getUser_no());
        session.setAttribute("order", order_list);
        
        return "main";
    }

    // 서비스에 있어야하나 지금은 애매하여 일단둠
    public void successOrder(int order_no, List<Cart> list) throws Exception {
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

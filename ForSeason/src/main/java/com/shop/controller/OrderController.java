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
        int tot_price = 0;
        int item_count = 0;
        String text = "";
        List<Item> itemList = new ArrayList<Item>();
        List<Stock> stockList = new ArrayList<Stock>();
        for (Cart cart : cartList) {
            Stock stock = stockService.getColorSizeName(cart.getStock_no());
            item_count = cart.getCart_cnt();
            Item item = itemService.get(stock.getItem_no());
            if (item.getItem_discnt() == 0) {
                tot_price += item.getItem_price() * item_count;
            } else {
                tot_price += (item.getItem_price() - item.getItem_price() * item.getItem_discnt() / 100) * item_count;
            }
            itemList.add(item);
            stockList.add(stock);
        }
        text = String.format("%s 외 %d 개 제품", itemList.get(0).getItem_name(), cartList.size() - 1);
        if (itemList.size() == 1) {
            text = String.format("%s 같은 제품 %d 개", text, item_count);
        }
        model.addAttribute("stockList", stockList);
        model.addAttribute("center", "order/checkout");
        model.addAttribute("cartList", cartList);
        model.addAttribute("itemList", itemList);
        model.addAttribute("item_name", text);
        model.addAttribute("item_tot", tot_price);

        return "main";
    }

        @RequestMapping("/order/order_detail")
        public String orderDetail (@RequestBody Map < String, Object > params) throws Exception {
            List<Cart> cartList = (List<Cart>) session.getAttribute("orderCartList");
            User user = (User) session.getAttribute("loginUser");
            Order order = orderService.createItemsByOrder(params, user.getUser_no());
            int orderNo = order.getOrder_no();
            orderDetailService.successOrder(orderNo, cartList);
            String couponNo = (String) params.get("coupon_no");
            if (!couponNo.equals("0") && !couponNo.equals("null")) {
                couponService.useCoupon(Integer.parseInt(couponNo));
            }
            List<Cart> updatedCartList = cartService.setCartList(user.getUser_no(), cartList);
            session.setAttribute("cartList", updatedCartList);
            List<Order> orderList = orderService.get_list(user.getUser_no());
            session.setAttribute("order", orderList);
            return "main";
        }

        // 서비스에 있어야하나 지금은 애매하여 일단둠

    }

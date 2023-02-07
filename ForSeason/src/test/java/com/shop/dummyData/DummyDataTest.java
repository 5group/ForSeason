package com.shop.dummyData;

import com.shop.dto.*;
import com.shop.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
public class DummyDataTest {

    @Autowired
    ItemService itemService;

    @Autowired
    StockService stockService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    UserService userService;

    @Test
    public void dummyDataPut() throws Exception {
        Random random = new Random();
        List<User> userList = userService.get();
        User user = userList.get(random.nextInt(userList.size()));
        int total = 0;
        int itot = 0;
        cartService.remove(user.getUser_no());
        List<Integer> disCountList = new ArrayList<Integer>();
        for(int i = 0; i< random.nextInt(5)+3; i ++){
            List<Item> itemList = itemService.get();
            Item item = itemList.get(random.nextInt(itemList.size()-1));
            List<Stock> stockList = stockService.getItemByStock(item.getItem_no());
            Stock stock = stockList.get(random.nextInt(stockList.size()-1));
            int cart_cnt =  random.nextInt(5) + 1;
            cartService.register(new Cart(0, stock.getStock_no(), user.getUser_no(), cart_cnt, null));
            disCountList.add(item.getItem_discnt());
            if(item.getItem_discnt() == 0){
                itot = item.getItem_price() * cart_cnt;// total += 이렇게 할려했는데 ? 도대체 이게왜안되는지 내논리론 이해안됨ㅇㅇ..
                total += itot;
            }else {
                itot = (item.getItem_price() - item.getItem_price() * item.getItem_discnt() / 100) * cart_cnt;
                total += itot;
            }
        }
        Order order = new Order();
        order.setUser_no(user.getUser_no());
        order.setOrder_tot(total);// 1000원, 2000원, 3000원
        order.setOrder_pay("카카오페이"); // 카카오페이 고정 ex) 카카오페이
        order.setShip_addr("경기도 남양주 다산동"); // 주소지 고정 ex) 경기도 남양주 다산동
        order.setShip_cust(user.getUser_name()); // 받는이 고정 ex) 조민수
        order.setShip_stat("입고준비중"); // 배송 상태 고정 ex) 입고 준비중
        order.setOrder_stat("결제완료"); // 결제 상태 고정 ex) 결제 완료
        order.setOrder_cp(0);
        order.setOrder_udate(cartByOrderTotal());
        orderService.TestRegister(order); // user_no, total, user_name
        List<Cart> cartList = cartService.get_list(user.getUser_no());
        System.out.println(cartList);
        int a = 0;
        for(Cart cart:cartList){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder_no(order.getOrder_no());
            orderDetail.setStock_no(cart.getStock_no());
            orderDetail.setOd_cnt(cart.getCart_cnt());
            orderDetail.setOd_dicnt(disCountList.get(a));
            a += 1;
            orderDetailService.register(orderDetail);
        }
        cartService.remove(user.getUser_no());
    }

    @Test
    void cartUser() throws Exception {
        for(int i = 0; i < 1000; i++){
            dummyDataPut();
        }
    }

    public Date cartByOrderTotal() throws ParseException {
        Random random = new Random();
        Date today = new Date();
        SimpleDateFormat sbFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, random.nextInt(364)+1);
        return sbFormat.parse(sbFormat.format(cal.getTime()));
    }
}

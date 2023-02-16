package com.shop.service;

import com.shop.dto.Cart;
import com.shop.dto.Item;
import com.shop.dto.OrderDetail;
import com.shop.dto.Stock;
import com.shop.frame.MyService;
import com.shop.mapper.OrderDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService implements MyService<Integer, OrderDetail> {

    @Autowired
    OrderDetailMapper mapper;

    @Autowired
    StockService stockService;

    @Autowired
    ItemService itemService;

    @Override
    public void register(OrderDetail orderDetail) throws Exception {
        mapper.insert(orderDetail);
    }

    @Override
    public void remove(Integer id) throws Exception {
        mapper.delete(id);
    }

    @Override
    public void modify(OrderDetail orderDetail) throws Exception {
        mapper.update(orderDetail);
    }

    @Override
    public OrderDetail get(Integer id) throws Exception {
        return mapper.select(id);
    }

    @Override
    public List<OrderDetail> get() throws Exception {
        return mapper.selectAll();
    }

    public List<OrderDetail> get_list(int user_no) throws Exception {
        return mapper.selectList(user_no);
    }

    public List<OrderDetail> getODList(int user_no) throws Exception {
        return mapper.select_userODList(user_no);
    }

    public void successOrder(int order_no, List<Cart> list) throws Exception {
        for (Cart cart : list) {
            Stock stock = stockService.get(cart.getStock_no());
            Item item = itemService.get(stock.getItem_no());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder_no(order_no);
            orderDetail.setStock_no(cart.getStock_no());
            orderDetail.setOd_cnt(cart.getCart_cnt());
            orderDetail.setOd_price(item.getItem_price());
            orderDetail.setOd_dicnt(item.getItem_discnt());
            register(orderDetail);
            stockService.setAmount(cart.getStock_no(), cart.getCart_cnt());
        }
    }
}

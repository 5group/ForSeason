package com.shop.service;

import com.shop.dto.OrderDetail;
import com.shop.frame.MyService;
import com.shop.mapper.OrderDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService implements MyService<Integer, OrderDetail> {

    @Autowired
    OrderDetailMapper mapper;

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

    public List<OrderDetail> get_list(int user_no) throws Exception{
        return mapper.selectList(user_no);
    }

    public List<OrderDetail> getODList(int user_no) throws Exception{
        return mapper.select_userODList(user_no);
    }

    public void createOrderDetail(int order_no, int stock_no, int cnt, int price, int discnt) throws Exception {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder_no(order_no);
        orderDetail.setStock_no(stock_no);
        orderDetail.setOd_cnt(cnt);
        orderDetail.setOd_price(price);
        orderDetail.setOd_dicnt(discnt);
        mapper.insert(orderDetail);
    }
}

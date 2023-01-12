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
        return mapper.selectall();
    }
}

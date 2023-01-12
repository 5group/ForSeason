package com.shop.service;

import com.shop.dto.Order;
import com.shop.frame.MyService;
import com.shop.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements MyService<Integer, Order> {
    @Autowired
    OrderMapper mapper;

    @Override
    public void register(Order order) throws Exception {
        mapper.insert(order);
    }

    @Override
    public void remove(Integer id) throws Exception {
        mapper.delete(id);
    }

    @Override
    public void modify(Order order) throws Exception {
        mapper.update(order);
    }

    @Override
    public Order get(Integer id) throws Exception {
        return mapper.select(id);
    }

    @Override
    public List<Order> get() throws Exception {
        return mapper.selectall();
    }
}

package com.admin.service;

import com.admin.dto.Order;
import com.admin.frame.MyService;
import com.admin.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        return mapper.selectAll();
    }

}
package com.admin.service;

import com.admin.dto.OrderDetail;
import com.admin.frame.MyService;
import com.admin.mapper.OrderDetailMapper;
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

    public List<OrderDetail> getTopCateNameByTotalList(String cate_name){
        return mapper.topCateOrderTotalList(cate_name);
    }
}
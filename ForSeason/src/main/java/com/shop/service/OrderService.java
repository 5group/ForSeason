package com.shop.service;

import com.shop.dto.Order;
import com.shop.frame.MyService;
import com.shop.mapper.OrderMapper;
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
    public Order createItemsByOrder(Map<String, Object> params, int user_no) throws Exception {
        Order order = new Order();
        order.setUser_no(user_no);
        order.setOrder_tot((Integer) params.get("order_tot"));
        order.setOrder_pay((String) params.get("order_pay"));
        order.setShip_addr((String) params.get("ship_addr"));
        order.setShip_cust((String) params.get("ship_cust"));
        order.setShip_stat((String) params.get("ship_stat"));
        order.setOrder_stat((String) params.get("order_stat"));
        if (params.get("cou_price") == null || params.get("cou_price") == "") {
            order.setOrder_cp(0);
        } else {
            order.setOrder_cp(Integer.parseInt((String) params.get("cou_price")));
        }
        mapper.insert(order); // 새로운 오더 테이블 만들어주기
        return order;
    }
    public List<Order> get_list(int user_no) throws Exception {
        return mapper.selectList(user_no);
    }
}

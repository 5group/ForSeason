package com.admin.service;

import com.admin.dto.Cart;
import com.admin.frame.MyService;
import com.admin.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements MyService<Integer, Cart> {

    @Autowired
    CartMapper mapper;

    @Override
    public void register(Cart cart) throws Exception {
        mapper.insert(cart);
    }

    @Override
    public void remove(Integer no) throws Exception {
        mapper.delete(no);
    }

    @Override
    public void modify(Cart cart) throws Exception {
        mapper.update(cart);
    }

    @Override
    public Cart get(Integer no) throws Exception {
        return mapper.select(no);
    }

    @Override
    public List<Cart> get() throws Exception {
        return mapper.selectAll();
    }

    public List<Cart> get_list(int user_no) throws Exception{
        return mapper.selectList(user_no);
    }
}

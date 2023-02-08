package com.shop.service;

import com.shop.dto.Cart;
import com.shop.frame.MyService;
import com.shop.mapper.CartMapper;
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

    public List<Cart> setCartList(int user_no, List<Cart> orderCartList) throws Exception {
        for (Cart copyCart : orderCartList) {
            remove(copyCart.getCart_no());
        }
        return get_list(user_no);
    }
    
    public Cart checkCartList(Cart cart) throws Exception{
    	return mapper.checkCartList(cart);
    }
}

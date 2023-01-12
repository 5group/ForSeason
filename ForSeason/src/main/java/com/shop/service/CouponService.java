package com.shop.service;

import com.shop.dto.Coupon;
import com.shop.frame.MyService;
import com.shop.mapper.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService implements MyService<Integer, Coupon> {
    @Autowired
    CouponMapper mapper;

    @Override
    public void register(Coupon coupon) throws Exception {
        mapper.insert(coupon);
    }

    @Override
    public void remove(Integer id) throws Exception {
        mapper.delete(id);
    }

    @Override
    public void modify(Coupon coupon) throws Exception {
        mapper.update(coupon);
    }

    @Override
    public Coupon get(Integer id) throws Exception {
        return mapper.select(id);
    }

    @Override
    public List<Coupon> get() throws Exception {
        return mapper.selectall();
    }
}

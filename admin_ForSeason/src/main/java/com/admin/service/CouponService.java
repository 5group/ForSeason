package com.admin.service;

import com.admin.dto.Coupon;
import com.admin.frame.MyService;
import com.admin.mapper.CouponMapper;
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

    public List<Coupon> getList(int user_no) throws Exception{
        return mapper.select_list(user_no);
    }

    public void useCoupon(int coupon_no) throws Exception {
        Coupon coupon = new Coupon();
        coupon.setCou_no(coupon_no);
        mapper.update(coupon);
    }
}

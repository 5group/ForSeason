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
        return mapper.selectAll();
    }

    public List<Coupon> getList(int user_no) throws Exception{
        return mapper.selectList(user_no);
    }

    public void useCoupon(int coupon_no) throws Exception {
        Coupon coupon = new Coupon();
        coupon.setCou_no(coupon_no);
        mapper.update(coupon);
    }

    public void firstCoupon(int user_no) throws Exception{
        int price = 5000;
        for(int i = 1; i <= 4; i++) {
            int resultPrice = i * price;
            String couponName = String.format("회원가입 기념 %s원 할인쿠폰", resultPrice);
            Coupon coupon = new Coupon(couponName, resultPrice, user_no);
            register(coupon);
        }
    }
}

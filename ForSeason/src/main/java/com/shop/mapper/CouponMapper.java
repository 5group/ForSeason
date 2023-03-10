package com.shop.mapper;

import com.shop.dto.Coupon;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CouponMapper extends MyMapper<Integer, Coupon> {

    public List<Coupon> selectList(int user_no);
}

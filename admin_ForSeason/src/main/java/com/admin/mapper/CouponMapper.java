package com.admin.mapper;

import com.admin.dto.Coupon;
import com.admin.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CouponMapper extends MyMapper<Integer, Coupon> {

    public List<Coupon> select_list(int user_no);
}

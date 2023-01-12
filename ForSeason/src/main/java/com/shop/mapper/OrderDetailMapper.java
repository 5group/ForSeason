package com.shop.mapper;

import com.shop.dto.OrderDetail;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderDetailMapper extends MyMapper<Integer, OrderDetail> {

}

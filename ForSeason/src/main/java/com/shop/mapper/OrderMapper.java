package com.shop.mapper;

import com.shop.dto.Order;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper extends MyMapper<Integer, Order> {

}

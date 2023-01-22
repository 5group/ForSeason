package com.shop.mapper;

import com.shop.dto.Order;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper extends MyMapper<Integer, Order> {
    public List<Order> select_list(int user_no);
}

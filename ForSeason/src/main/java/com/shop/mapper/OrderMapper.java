package com.shop.mapper;

import com.shop.dto.Order;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper extends MyMapper<Integer, Order> {
    public List<Order> selectList(int user_no);

//    public void insertTest(Order order) throws Exception;
}

package com.shop.mapper;

import com.shop.dto.OrderDetail;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderDetailMapper extends MyMapper<Integer, OrderDetail> {
    public List<OrderDetail> select_list(int user_no);
    public List<OrderDetail> select_userODList(int user_no);
}

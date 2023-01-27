package com.admin.mapper;

import com.admin.dto.Order;
import com.admin.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper extends MyMapper<Integer, Order> {
    public List<Order> select_list(int user_no);
    List<Order> dateBySelect(String dateFormat);
}

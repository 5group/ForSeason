package com.admin.mapper;

import com.admin.dto.OrderDetail;
import com.admin.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderDetailMapper extends MyMapper<Integer, OrderDetail> {
    public List<OrderDetail> selectList(int user_no);
    public List<OrderDetail> select_userODList(int user_no);
}

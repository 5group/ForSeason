package com.admin.mapper;

import com.admin.dto.Cart;
import com.admin.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CartMapper extends MyMapper<Integer, Cart> {
    public List<Cart> select_list(int user_no);
}

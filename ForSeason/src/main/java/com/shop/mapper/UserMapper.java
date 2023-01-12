package com.shop.mapper;

import com.shop.dto.User;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends MyMapper<Integer, User> {
    public User select_id(String id) throws Exception;
    public void delete(String id) throws Exception;
}

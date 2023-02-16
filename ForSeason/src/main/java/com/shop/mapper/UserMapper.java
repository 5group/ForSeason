package com.shop.mapper;

import com.shop.dto.User;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Mapper
@Repository
public interface UserMapper extends MyMapper<Integer, User> {
    public User selectId(String id) throws Exception;

    public void delete(String id) throws Exception;

    public void pwd_update(User user) throws Exception;

    public String selectFindUser(HashMap<String, String> hashMap) throws Exception;
}

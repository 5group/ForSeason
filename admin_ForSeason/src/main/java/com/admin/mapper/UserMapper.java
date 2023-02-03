package com.admin.mapper;

import com.admin.dto.User;
import com.admin.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends MyMapper<Integer, User> {
    public User selectId(String id) throws Exception;
    public void delete(String id) throws Exception;
    public void pwd_update(User user) throws Exception;
}

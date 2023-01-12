package com.shop.service;


import com.shop.dto.User;
import com.shop.frame.MyService;
import com.shop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements MyService<Integer, User> {
    @Autowired
    UserMapper mapper;

    @Override
    public void register(User user) throws Exception {
        mapper.insert(user);
    }

    @Override
    public void remove(Integer integer) throws Exception {
        mapper.delete(integer);
    }

    public void remove(String string) throws Exception{
        mapper.delete(string);
    }

    @Override
    public void modify(User user) throws Exception {
        mapper.update(user);
    }

    public User get_id(String id) throws Exception {
        return mapper.select_id(id);
    }

    @Override
    public User get(Integer integer) throws Exception {
        return mapper.select(integer);
    }

    @Override
    public List<User> get() throws Exception {
        return mapper.selectall();
    }
}

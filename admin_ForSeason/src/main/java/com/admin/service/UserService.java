package com.admin.service;


import com.admin.dto.User;
import com.admin.frame.MyService;
import com.admin.mapper.UserMapper;
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

    public void remove(String string) throws Exception {
        mapper.delete(string);
    }

    @Override
    public void modify(User user) throws Exception {
        mapper.update(user);
    }

    @Override
    public User get(Integer integer) throws Exception {
        return mapper.select(integer);
    }

    @Override
    public List<User> get() throws Exception {
        return mapper.selectAll();
    }
}

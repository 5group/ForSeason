package com.admin.service;


import com.admin.dto.Admin;
import com.admin.frame.MyService;
import com.admin.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements MyService<String, Admin> {

    @Autowired
    AdminMapper mapper;

    @Override
    public void register(Admin admin) throws Exception {
        mapper.insert(admin);
    }

    @Override
    public void remove(String id) throws Exception {
        mapper.delete(id);
    }

    @Override
    public void modify(Admin admin) throws Exception {
        mapper.update(admin);
    }

    @Override
    public Admin get(String s) throws Exception {
        return mapper.select(s);
    }

    @Override
    public List<Admin> get() throws Exception {
        return mapper.selectall();
    }
}

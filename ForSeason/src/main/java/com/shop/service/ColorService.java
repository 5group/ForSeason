package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dto.Color;
import com.shop.frame.MyService;
import com.shop.mapper.ColorMapper;

@Service
public class ColorService implements MyService<Integer, Color> {

    @Autowired
    ColorMapper mapper;

    @Override
    public void register(Color color) throws Exception {
        mapper.insert(color);
    }

    @Override
    public void remove(Integer no) throws Exception {
        mapper.delete(no);
    }

    @Override
    public void modify(Color color) throws Exception {
        mapper.update(color);
    }

    @Override
    public Color get(Integer no) throws Exception {
        return mapper.select(no);
    }

    @Override
    public List<Color> get() throws Exception {
        return mapper.selectall();
    }
   
}

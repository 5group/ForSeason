package com.shop.service;

import com.shop.dto.Ship;
import com.shop.frame.MyService;
import com.shop.mapper.ShipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService implements MyService<Integer, Ship> {
    @Autowired
    ShipMapper mapper;

    @Override
    public void register(Ship ship) throws Exception {
        mapper.insert(ship);
    }

    @Override
    public void remove(Integer id) throws Exception {
        mapper.delete(id);
    }

    @Override
    public void modify(Ship ship) throws Exception {
        mapper.update(ship);
    }

    @Override
    public Ship get(Integer id) throws Exception {
        return mapper.select(id);
    }

    @Override
    public List<Ship> get() throws Exception {
        return mapper.selectall();
    }
}

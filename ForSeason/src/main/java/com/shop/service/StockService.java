package com.shop.service;

import com.shop.dto.Stock;
import com.shop.frame.MyService;
import com.shop.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StockService implements MyService<Integer, Stock> {

    @Autowired
    StockMapper mapper;

    @Override
    public void register(Stock stock) throws Exception {
        mapper.insert(stock);
    }

    @Override
    public void remove(Integer no) throws Exception {
        mapper.delete(no);
    }

    @Override
    public void modify(Stock stock) throws Exception {
        mapper.update(stock);
    }

    @Override
    public Stock get(Integer no) throws Exception {
        return mapper.select(no);
    }

    @Override
    public List<Stock> get() throws Exception {
        return mapper.selectall();
    }

    public void setAmount(int no, int cnt) throws Exception{
        Stock stock = mapper.select(no);
        stock.setAmount(stock.getAmount() - cnt);
        mapper.update(stock);
    }
}

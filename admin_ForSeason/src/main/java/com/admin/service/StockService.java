package com.admin.service;

import com.admin.dto.Stock;
import com.admin.frame.MyService;
import com.admin.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class StockService implements MyService<Integer, Stock> {

    @Autowired
    StockMapper mapper;

    @Override
    public void register(Stock stock) throws Exception {
        mapper.insert(stock);
    }

    @Override
    public void remove(Integer id) throws Exception {
        mapper.delete(id);
    }

    @Override
    public void modify(Stock stock) throws Exception {
        mapper.update(stock);
    }

    @Override
    public Stock get(Integer id) throws Exception {
        return mapper.select(id);
    }

    @Override
    public List<Stock> get() throws Exception {
        return mapper.selectAll();
    }

    public List<Stock> getItemTotal(String item_name) {
        return mapper.selectItemTotal(item_name);
    }

    public List<Stock> getChartList(String sm, String em) throws Exception {
        return mapper.selectChart(sm, em);
    }

    public List<Stock> getAdminMainList() {
        return mapper.selectAdminMain();
    }

    public List<Stock> getCateTotal(HashMap<String, Object> map) {
        return mapper.selectCateTotal(map);
    }
}

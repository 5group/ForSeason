package com.admin.service;

import com.admin.dto.Stock;
import com.admin.frame.MyService;
import com.admin.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Stock> getItemTotal(String item_name){
        return mapper.selectItemChart(item_name);
    }

    public List<Stock> getCateTotal(String cate_name){
        return mapper.selectCateChart(cate_name);
    }

    public List<Stock> getChartList(int index, String name, String sm, String em) throws Exception {
        switch (index){
            case 1:
                return getItemTotal(name);
            case 2:
                return getCateTotal(name);
            default:
                return mapper.selectChart(sm, em);
        }
    }

    public List<Stock> getAdminMainList(){
        return mapper.selectAdminMain();
    }
}

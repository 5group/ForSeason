package com.shop.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dto.Color;
import com.shop.dto.Size;
import com.shop.dto.Stock;
import com.shop.frame.MyService;
import com.shop.mapper.StockMapper;

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
    
    public List<Color> getStockColor(Integer item_no) throws Exception{
    	return mapper.getStockColor(item_no);
    }
    
    public List<Size> getStockSize(Integer item_no) throws Exception{
    	return mapper.getStockSize(item_no);
    }
    
    public int getStockNo(HashMap<String, Integer> map) throws Exception{
		return mapper.getStockNo(map);
	}

    public List<Size> getStock(Integer item_no, Integer color_no) throws Exception{  //현재 재고 리스트로 보내기
    	return mapper.selectStock(item_no, color_no);
    }

    public Stock getColorSizeName(Integer stock_no) throws Exception {
        return mapper.selectSizeColor(stock_no);
    }
}

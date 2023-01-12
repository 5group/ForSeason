package com.shop.service;

import com.shop.dto.Review;
import com.shop.frame.MyService;
import com.shop.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements MyService<Integer, Review> {
    @Autowired
    ReviewMapper mapper;

    @Override
    public void register(Review review) throws Exception {
        mapper.insert(review);
    }

    @Override
    public void remove(Integer id) throws Exception {
        mapper.delete(id);
    }

    @Override
    public void modify(Review review) throws Exception {
        mapper.update(review);
    }

    @Override
    public Review get(Integer id) throws Exception {
        return mapper.select(id);
    }

    @Override
    public List<Review> get() throws Exception {
        return mapper.selectall();
    }
}

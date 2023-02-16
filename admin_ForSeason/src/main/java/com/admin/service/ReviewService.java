package com.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dto.Review;
import com.admin.frame.MyService;
import com.admin.mapper.ReviewMapper;

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
        return mapper.selectAll();
    }

    public List<Review> revselect() throws Exception {
        return mapper.revselect();
    }

    public Review revnoselect(int id) throws Exception {
        return mapper.revnoselect(id);
    }


}

package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dto.Review;
import com.shop.frame.MyService;
import com.shop.mapper.ReviewMapper;

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

    public Review revnoselect(Integer id) throws Exception {
        return mapper.revnoselect(id);
    }

    public List<Review> SelectSortUdateTests(int id) throws Exception {
        return mapper.SelectSortUdateTests(id);
    }

    public List<Review> SelectallSortUdateTests(int id) throws Exception {
        return mapper.SelectallSortUdateTests(id);
    }

    public List<Review> SelectallSortScoreDescTests(int id) throws Exception {
        return mapper.SelectallSortScoreDescTests(id);
    }

    public List<Review> SelectallSortScoreAscTests(int id) throws Exception {
        return mapper.SelectallSortScoreAscTests(id);
    }

    public List<Review> userselect(int id) throws Exception {
        return mapper.userselect(id);
    }

    public List<Review> get_list(int user_no) throws Exception {
        return mapper.selectlist(user_no);
    }


}

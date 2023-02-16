package com.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.shop.dto.Qna;
import com.shop.frame.MyMapper;

@Mapper
@Repository
public interface QnaMapper extends MyMapper<Integer, Qna> {

    List<Qna> userselect(int id);

    Qna qnaselect(int id);


}

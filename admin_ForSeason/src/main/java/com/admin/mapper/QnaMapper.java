package com.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.admin.dto.Qna;
import com.admin.frame.MyMapper;

@Mapper
@Repository
public interface QnaMapper extends MyMapper<Integer, Qna> {

    List<Qna> userselect();

    Qna qnaselect(int id);

}

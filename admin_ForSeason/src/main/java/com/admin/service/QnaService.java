package com.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.admin.dto.Qna;
import com.admin.frame.MyService;
import com.admin.mapper.QnaMapper;

@Service
public class QnaService implements MyService<Integer, Qna> {
    @Autowired
    QnaMapper mapper;

    @Override
    public void register(Qna v) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void remove(Integer k) throws Exception {
        mapper.delete(k);
    }

    @Override
    public void modify(Qna v) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public Qna get(Integer k) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Qna> get() throws Exception {
        return mapper.selectAll();

    }

    public Qna qnaselect(int id) throws Exception {
        return mapper.qnaselect(id);

    }

    public List<Qna> userselect() throws Exception {
        return mapper.userselect();
    }
}

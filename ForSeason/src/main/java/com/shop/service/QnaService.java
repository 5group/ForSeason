package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dto.Qna;
import com.shop.frame.MyService;
import com.shop.mapper.QnaMapper;

@Service
public class QnaService implements MyService<Integer, Qna> {
    @Autowired
    QnaMapper mapper;
    
	@Override
	public void register(Qna qna) throws Exception {
		mapper.insert(qna);
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
		// TODO Auto-generated method stub
		return null;
	}

    
  public List<Qna> userselect(int id) throws Exception {
		return mapper.userselect(id);
	}
  
  public Qna qnaselect(int id) throws Exception{
	  	return mapper.qnaselect(id);
	  
  }
}

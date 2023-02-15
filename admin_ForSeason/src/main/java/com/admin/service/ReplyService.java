package com.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dto.Reply;
import com.admin.frame.MyService;
import com.admin.mapper.ReplyMapper;

@Service
public class ReplyService implements MyService<Integer, Reply> {
	@Autowired
	ReplyMapper mapper;

	

	@Override
	public void modify(Reply v) throws Exception {
		mapper.update(v);
		
	}

	@Override
	public void register(Reply v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reply get(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reply> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}

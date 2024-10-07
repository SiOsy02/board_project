package com.exam.myapp.reply.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.myapp.reply.service.ReplyDao;
import com.exam.myapp.reply.service.ReplyService;
import com.exam.myapp.reply.service.ReplyVo;

import lombok.extern.slf4j.Slf4j;

@Service 
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyDao replyDao;

	@Override
	public int add(ReplyVo vo) {
		return replyDao.add(vo);
	}

	@Override
	public List<ReplyVo> findAll(int repBbsNo) {
		return replyDao.findAll(repBbsNo);
	}

	@Override
	public int del(ReplyVo vo) {
		return replyDao.del(vo);
	}

}





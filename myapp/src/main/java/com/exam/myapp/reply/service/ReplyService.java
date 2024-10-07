package com.exam.myapp.reply.service;

import java.util.List;

public interface ReplyService {

	List<ReplyVo> findAll(int repBbsNo);

	int add(ReplyVo vo);

	int del(ReplyVo vo);

}
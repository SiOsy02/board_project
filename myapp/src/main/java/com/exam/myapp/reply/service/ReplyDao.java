package com.exam.myapp.reply.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyDao {

	List<ReplyVo> findAll(int repBbsNo);

	int add(ReplyVo vo);

	int del(ReplyVo vo);

}
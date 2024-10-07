package com.exam.myapp.attach.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttachDao {

	int add(AttachVo vo);

	AttachVo findById(int attNo);

	int del(int id);

	List<AttachVo> findAllByBbsNo(int id);

}
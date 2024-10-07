package com.exam.myapp.bbs.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.exam.myapp.comm.PageVo;
import com.exam.myapp.comm.SearchVo;

@Mapper
public interface BbsDao {

	List<BbsVo> findAll(SearchVo searchVo);
	
	int countAll(SearchVo searchVo);

	BbsVo findById(int id);
	
	int add(BbsVo vo);

	int edit(BbsVo vo);

	int del(BbsVo vo);

}
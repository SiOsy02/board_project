package com.exam.myapp.member.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

	List<MemberVo> findAll();

	MemberVo findById(String id);
	
	int add(MemberVo vo);

	int edit(MemberVo vo);

	int del(String id);

}
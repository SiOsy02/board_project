package com.exam.myapp.member.service;

import java.util.List;

public interface MemberService {

	List<MemberVo> findAll();

	MemberVo findById(String id);
	
	int add(MemberVo vo);

	int edit(MemberVo vo);

	int del(String id);

}
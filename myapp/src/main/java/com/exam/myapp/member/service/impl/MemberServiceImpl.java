package com.exam.myapp.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.myapp.member.service.MemberDao;
import com.exam.myapp.member.service.MemberService;
import com.exam.myapp.member.service.MemberVo;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public List<MemberVo> findAll() {
		return memberDao.findAll();
	}

	@Override
	public MemberVo findById(String id) {
		return memberDao.findById(id);
	}

	@Override
	public int add(MemberVo vo) {
		return memberDao.add(vo);
	}

	@Override
	public int edit(MemberVo vo) {
		return memberDao.edit(vo);
	}

	@Override
	public int del(String id) {
		return memberDao.del(id);
	}

}

package com.exam.myapp.bbs.service;

import java.io.File;
import java.util.List;

import com.exam.myapp.attach.service.AttachVo;
import com.exam.myapp.comm.SearchVo;

public interface BbsService {

	List<BbsVo> findAll(SearchVo searchVo);
	
	int countAll(SearchVo searchVo);

	BbsVo findById(int id);
	
	int add(BbsVo vo);

	int edit(BbsVo vo);

	int del(BbsVo vo);

	AttachVo findAttach(int attNo);

	File getAttachFile(String attNewName);

}
package com.exam.myapp.bbs.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.exam.myapp.attach.service.AttachVo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BbsVo {
	private Integer bbsNo;
	private String bbsTitle;
	private String bbsContent;
	private String bbsWriter;
	private Date bbsRegDate;
	private int bbsCount;
	
	private List<AttachVo> attList;
	
	//multipart/form-data에 포함된 파일을 받는 경우, MultipartFile 타입 사용
	//파라미터이름이 동일한 파라미터값이 여러개인 경우 배열 또는 List 사용
	private List<MultipartFile> upfileList; 
}

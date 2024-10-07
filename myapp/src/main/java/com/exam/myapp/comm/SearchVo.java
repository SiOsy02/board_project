package com.exam.myapp.comm;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchVo extends PageVo {
	
	private String searchKey;
	
	private String searchValue;
	
}

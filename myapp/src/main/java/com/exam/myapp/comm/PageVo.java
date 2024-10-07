package com.exam.myapp.comm;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PageVo {

	private int currentPageNo = 1; // 현재 페이지 번호
	private int recordCountPerPage = 10; // 한 페이지당 게시되는 게시물 건 수
	private int pageSize = 5; // 페이지 링크 리스트에 게시되는 페이지 수
	private int totalRecordCount; // 전체 게시물 건 수

	// 총 페이지 개수
	public int getTotalPageCount() { 
		return ((getTotalRecordCount() - 1) / getRecordCountPerPage()) + 1;
	}

	//  페이지 링크 리스트의 첫번째 페이지 번호
	public int getFirstPageNoOnPageList() {
		return ((getCurrentPageNo() - 1) / getPageSize()) * getPageSize() + 1;
	}

	//  페이지 링크 리스트의 마지막 페이지 번호
	public int getLastPageNoOnPageList() {
		int lastPageNoOnPageList = getFirstPageNoOnPageList() + getPageSize() - 1;
		if (lastPageNoOnPageList > getTotalPageCount()) {
			lastPageNoOnPageList = getTotalPageCount();
		}
		return lastPageNoOnPageList;
	}

	// 조회(SELECT)할 때, 몇번째 게시글부터 가져올 것인지
	public int getFirstRecordIndex() {
		return (getCurrentPageNo() - 1) * getRecordCountPerPage();
	}

	// 조회(SELECT)할 때, 몇번째 게시글까지 가져올 것인지
	public int getLastRecordIndex() {
		return getCurrentPageNo() * getRecordCountPerPage();
//		return getFirstRecordIndex() + getRecordCountPerPage() - 1;
	}
}

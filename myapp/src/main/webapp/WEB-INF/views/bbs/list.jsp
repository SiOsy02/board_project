<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    

<div class="container-sm">

<div class="row g-2">
<div class="mb-3"></div>
<h2 class="col mb-4">게시글 목록</h2>
<a href='${pageContext.request.contextPath}/bbs/add.do' class="btn btn-outline-primary mb-4"> 글쓰기 </a>
</div>

<table class="container-sm table">
<thead>
	<tr><th scope="col">번호</th><th scope="col">제목</th><th scope="col">작성자</th><th scope="col">작성일</th></tr>
</thead>
<tbody>
<c:forEach var="vo" items="${bbsList}" varStatus="stat"  >
	<tr>
	<td>${searchVo.totalRecordCount - stat.index - (searchVo.recordCountPerPage * (searchVo.currentPageNo - 1) ) } </td>
	<td>
		<a href="#" onclick="editBbs(${vo.bbsNo});" >
		<c:out value="${vo.bbsTitle}"/> 
		</a>
	</td>
	<td>
		<c:out value="${vo.bbsWriter}" />
	</td>
	<td>
		<fmt:formatDate value="${vo.bbsRegDate}" pattern="yyyy/MM/dd HH:mm:ss" />
	</td>
	</tr>
</c:forEach>
</tbody>
</table>
</div>


<script type="text/javascript">
	function editBbs(bbsNo) {
		document.querySelector('[name="currentPageNo"]').value = ${searchVo.currentPageNo};
		document.querySelector('[name="bbsNo"]').value = bbsNo;
		document.querySelector('#searchForm').action = '${pageContext.request.contextPath}/bbs/edit.do';
		document.querySelector('#searchForm').submit();
	}
</script>

<form id="searchForm" class="container-sm" action="${pageContext.request.contextPath}/bbs/list.do">
	<input type="hidden" name="bbsNo" >
	<select name="searchKey" >
		<option value="title"> 제목 </option>
		<option value="content"> 내용 </option>
		<option value="total"> 제목+내용 </option>
	</select>
	<script type="text/javascript">
		if ( '${searchVo.searchKey}' )
			document.querySelector('[name="searchKey"]').value = '${searchVo.searchKey}';
	</script>
	<input type="text" name="searchValue" value="${searchVo.searchValue}" placeholder="검색어 입력" >
	<input type="submit" value="검색" >
	<input type="hidden" name="currentPageNo" value="1" >
</form>

<script type="text/javascript">
	function goPage(pageNo) {
		document.querySelector('[name="currentPageNo"]').value = pageNo;
		document.querySelector('#searchForm').submit();
	}
</script>

<div class="container-sm">

<c:if test="${searchVo.firstPageNoOnPageList>1}">
	<a href="#" onclick="goPage(${searchVo.firstPageNoOnPageList-1});" >[이전]</a>
</c:if>

<c:forEach var="no" begin="${searchVo.firstPageNoOnPageList}" end="${searchVo.lastPageNoOnPageList}" step="1" >
	<c:if test="${no!=searchVo.currentPageNo}">
		<a href="#" onclick="goPage(${no});">[${no}]</a>
	</c:if>
	<c:if test="${no==searchVo.currentPageNo}">
		[${no}]
	</c:if>
</c:forEach>

<c:if test="${searchVo.lastPageNoOnPageList < searchVo.totalPageCount}">
	<a href="#" onclick="goPage(${searchVo.lastPageNoOnPageList+1});" >[다음]</a>
</c:if>

</div>





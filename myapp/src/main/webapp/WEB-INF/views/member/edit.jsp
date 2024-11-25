<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    

<div class="container-sm"> 
	<div class="mb-3"></div>
	<h1>회원 정보 변경</h1>
		<form action='${pageContext.request.contextPath}/member/edit.do' method='post' >

			<input class="form-control" type='text' id="memId" name='memId' value="${memberVo.memId}" >
			<br>
			<label class="form-label" for="memPass"> 비밀번호 </label>   
			<input class="form-control" type='password' id="memPass" name='memPass' value="${memberVo.memPass}" >
			<br>
			<label class="form-label" for="memName"> 이름 </label> 
			<input class="form-control" type='text' id="memName" name='memName' value="${memberVo.memName}" >
			<br>
			<label class="form-label" for="memPoint"> 포인트 </label>    
			<input class="form-control" type='number' id="memPoint" name='memPoint' value="${memberVo.memPoint}" >
			<br>
			<input type='submit' class="btn btn-primary" value="저장">                             
			<a href="${pageContext.request.contextPath}/member/del.do?memId=${fn:escapeXml(memVo.memId)}">
				<button type="button" class="btn btn-dark">삭제</button>
			</a>
		</form>  

</div>
                                             




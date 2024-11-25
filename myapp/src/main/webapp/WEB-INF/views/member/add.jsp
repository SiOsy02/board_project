<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<div class="container-sm">

	<div class="mb-3"></div>
	<h1>회원 추가</h1>
	
	<form action='${pageContext.request.contextPath}/member/add.do' method='post' >
	
		<label class="form-label" for="memId"> 아이디 </label> 
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
		<input type='submit' class="btn btn-primary">  
		                           
	</form>                                               

</div>
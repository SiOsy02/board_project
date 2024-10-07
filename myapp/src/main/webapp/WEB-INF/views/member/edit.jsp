<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    

<h1>회원 정보 변경</h1>
<form:form modelAttribute="memVo" action="${pageContext.request.contextPath}/member/edit.do" method="post">
아이디 : <form:input path="memId" readonly="true" /> 
	<form:errors path="memId" /> <br>
이름 : <form:input path="memName" /> 
	<form:errors path="memName" /> <br>
포인트 : <form:input type="number"  path="memPoint" />
	<form:errors path="memPoint" /> <br>
<input type='submit' value="저장"> 
</form:form>

<%-- <form action='${pageContext.request.contextPath}/member/edit.do' method='post' > --%>
<%-- 아이디 : <input type='text' name='memId' value="${fn:escapeXml(memVo.memId)}" readonly ><br>      --%>
<%-- 비밀번호 : <input type='password' name='memPass' value="${memVo.memPass}" ><br> --%>
<%-- 이름 : <input type='text' name='memName' value="${fn:escapeXml(memVo.memName)}" ><br>      --%>
<%-- 포인트 : <input type='number' name='memPoint' value="${memVo.memPoint}" ><br> --%>
<!-- <input type='submit' value="저장">                              -->
<%-- </form>   --%>

<a href="${pageContext.request.contextPath}/member/del.do?memId=${fn:escapeXml(memVo.memId)}" ><button type="button">삭제</button></a>
                                             




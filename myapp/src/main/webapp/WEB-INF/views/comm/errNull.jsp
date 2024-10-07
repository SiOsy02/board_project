<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오류페이지</title>
</head>
<body>
	<h1>넌 내게 null을 줬어</h1>
<!-- 에러페이지에서는 exception 기본객체를 통해서, 발생한 예외 객체 사용 가능 -->
<%-- 	<h3><%=exception.getMessage()%></h3> --%>
	<h3>${pageContext.exception.message}</h3>
	<img alt="" src="${pageContext.request.contextPath}/img/null.jpg" >
</body>
</html>







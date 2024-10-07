<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<h1>회원 추가</h1>
<!-- "memberVo"라는 이름으로 모델에 저장되어 있는 객체와 현재 form을 연결 -->
<form:form modelAttribute="memberVo" action='${pageContext.request.contextPath}/member/add.do' method='post'  >
<!-- 	path 속성값으로 id, name 속성값을 설정하고, 모델객체의 해당 속성값으로 value 속성을 설정  -->
	아이디 : <form:input path="memId" />
<!-- 	모델 객체의 path에 지정한 속성과 관련된 에러 메시지를 출력  -->
		<form:errors path="memId" /> <br>  
	비밀번호 : <form:password path="memPass" /> 
		<form:errors path="memPass" /> <br>
	이름 : <form:input path="memName" /> 
		<form:errors path="memName" /> <br>
	포인트 : <form:input type="number" path="memPoint" />
		<form:errors path="memPoint" /> <br>	
	<input type="submit" > 
</form:form>

<form action='${pageContext.request.contextPath}/member/add.do' method='post' >
아이디 : <input type='text' id="memId" name='memId' value="${memberVo.memId}" ><br>     
비밀번호 : <input type='password' id="memPass" name='memPass' value="${memberVo.memPass}" ><br>
이름 : <input type='text' id="memName" name='memName' value="${memberVo.memName}" ><br>     
포인트 : <input type='number' id="memPoint" name='memPoint' value="${memberVo.memPoint}" ><br>
<input type='submit' >                             
</form>                                               

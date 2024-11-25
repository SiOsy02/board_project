<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    

<div class="container-sm">


	<div class="mb-3"></div>
	<h1>회원목록</h1>
	<a href='${pageContext.request.contextPath}/member/add.do' class="btn btn-primary" >회원추가</a>
	<br>
	<table class="container-sm table">
	<thead>
		<tr><th scope="col">아이디</th><th scope="col">이름</th></tr>
	</thead>
	<tbody>
	<c:forEach var="vo" items="${memList}">
		<tr>
		<td>
			<c:url value="/member/edit.do" var="editUrl" >
				<c:param name="memId" value="${vo.memId}" />
			</c:url>
			<a href="${editUrl}" >
		<%-- 	<a href="<c:url value="/member/edit.do"><c:param name="memId" value="${vo.memId}" /></c:url>" > --%>
		<%-- 	<a href="${pageContext.request.contextPath}/member/edit.do?memId=<c:out value="${vo.memId}" />" > --%>
		<%-- 	<a href="${pageContext.request.contextPath}/member/edit.do?memId=${fn:escapeXml(vo.memId)}" > --%>
			<c:out value="${vo.memId}"/> 
			</a>
		</td>
		<td>
			<%-- 	${fn:escapeXml(vo.memName)} --%>
			<c:out value="${vo.memName}" />
		</td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
</div>





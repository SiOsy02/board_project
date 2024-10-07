<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" 
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
  
    <a class="navbar-brand" href="#">MENU</a>
    
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
    	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			<c:if test="${loginUser==null}">
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/login.do">로그인</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/add.do">회원가입</a></li>
			</c:if>
			<c:if test="${loginUser!=null}">
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/bbs/list.do">게시판</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/list.do">회원관리</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
			</c:if>
		</ul>
    </div>
  </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous">
</script>
</body>
</html>
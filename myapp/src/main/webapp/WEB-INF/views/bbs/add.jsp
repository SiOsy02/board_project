<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container-sm mb-3">
	<div class="mb-3"></div>
	<h1 class="mb-3">게시글 쓰기</h1>
	<form action='${pageContext.request.contextPath}/bbs/add.do' enctype="multipart/form-data" method='post'>

		<label class="form-label" for="bbsTitle"> 제목 </label> 
		<input type='text' name="bbsTitle" class="form-control"> 
		
		<br> 
		
		<label class="form-label" for="bbsContent"> 내용 </label>
		<textarea class="form-control" name="bbsContent" rows="10" cols="50"></textarea>

		<br> 
		
		<label class="form-label" for="bbsContent"> 첨부파일1 </label>
		<input class="form-control" type="file" name="upfileList"> <br>
		
		<label class="form-label" for="bbsContent"> 첨부파일2 </label>
		<input class="form-control" type="file" name="upfileList"> <br> 
		
		<input class="btn btn-primary" type='submit' value="저장">
	</form>
</div>
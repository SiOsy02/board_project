<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h1>게시글 쓰기</h1>
<form action='${pageContext.request.contextPath}/bbs/add.do' enctype="multipart/form-data"  method='post' >
제목 : <input type='text' name="bbsTitle" ><br>     
내용 : <textarea name="bbsContent" rows="10" cols="50"></textarea> <br>
첨부파일1 : <input type="file" name="upfileList" > <br>
첨부파일2 : <input type="file" name="upfileList" > <br>

<input type='submit' value="저장" >
</form> 
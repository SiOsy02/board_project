<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    

<h1>게시글 정보 변경</h1>
<form id="editForm" action='${pageContext.request.contextPath}/bbs/edit.do' method='post' >
<input type="hidden" name="bbsNo" value="${bbsVo.bbsNo}" />
제목 : <input type='text' name="bbsTitle" value="${fn:escapeXml(bbsVo.bbsTitle)}" ><br>     
내용 : <textarea name="bbsContent" rows="10" cols="50">${fn:escapeXml(bbsVo.bbsContent)}</textarea> <br>
작성자 : <c:out value="${bbsVo.bbsWriter}" /> <br> 
<c:forEach var="attVo" items="${bbsVo.attList}" >
	첨부파일 : <a href="${pageContext.request.contextPath}/bbs/down.do?attNo=${attVo.attNo}" ><c:out value="${attVo.attOrgName}" /></a> <br>  
</c:forEach>

<c:if test="${bbsVo.bbsWriter == loginUser.memId}">
<input type='submit' value="저장">                             
<button type="button" onclick="delBbs();" >삭제</button>
<script type="text/javascript">
	function delBbs() {
		document.querySelector('#editForm').action = '${pageContext.request.contextPath}/bbs/del.do';
		document.querySelector('#editForm').submit();
	}
</script>
</c:if>

<input type="hidden" name="searchKey" value="${searchVo.searchKey}" >
<input type="hidden" name="searchValue" value="${searchVo.searchValue}" >
<input type="hidden" name="currentPageNo" value="${searchVo.currentPageNo}" >

</form>  

<hr>
<textarea id="repContent" rows="5" cols="50"></textarea><br>
<button id="repAddBtn" type="button">저장</button>

<hr>
<div id="replyList"></div>

<template id="replyTemp">
	<div class="writer"></div>
	<div class="date"></div>
	<div class="content"></div>
	<button type="button" class="del" >삭제</button>
	<hr>
</template>

<script type="text/javascript">
	var repTemp = document.querySelector('#replyTemp');
	var repListDiv = document.querySelector('#replyList');
	var rcta = document.querySelector('#repContent');
	
	document.querySelector('#repAddBtn').onclick = function() {
		fetch('${pageContext.request.contextPath}/reply/add.do', {
			method: 'POST', //요청방식
			headers: { 'Accept':'application/json' }, //요청헤더
			body: new URLSearchParams({ repContent: rcta.value, repBbsNo: ${bbsVo.bbsNo} })
	// 		body: 'repContent='+rcta.value+'&repBbsNo=${bbsVo.bbsNo}' //요청메시지본문내용
		})
		.then(resp=>resp.json())
		.then(function(data){ //data : 응답메시지의 내용(JSON)을 자바스크립트 객체로 변환한 값
			console.log(data); // { count: 1 }
			if (data.count>0){
				refreshList();
				alert('댓글저장성공');
				rcta.value = '';
			}else {
				alert('댓글저장실패');
			}
		})
// 		.then(function(resp) { //요청에 대한 응답을 성공적으로 받았을때 실행할 함수
// 			console.log(resp); //서버로부터 받은 응답 메시지를 담은 객체가 인자로 전달
// 			resp.json() //응답 내용을 JSON으로 해석하여 자바스크립트 객체로 변환
// 			.then(function(data) {
// 				console.log(data); 
// 			});
// 		}) 
		.catch(function(err) { //요청 처리가 실패한 경우 실행할 함수
			console.log(err); //발생한 오류 정보가 인자로 전달
			alert('댓글저장실패');
		}); 
	}; 
	
	function delReply(ev) {
		var no = ev.target.getAttribute('data-no');  //클릭한 버튼의 data-no 속성값 읽기
		var ok = confirm( no + '번 댓글 정말 삭제?');
		if ( !ok ) return;
		
		fetch('${pageContext.request.contextPath}/reply/del.do?repNo=' + no , {
			method: 'GET', //요청방식
			headers: { 'Accept':'application/json' }, //요청헤더
	// 		body: new URLSearchParams({ repBbsNo: ${bbsVo.bbsNo} })
		})
		.then(resp=>resp.json())
		.then(function(data){ //data : 응답메시지의 내용(JSON)을 자바스크립트 객체로 변환한 값
			console.log(data); 
			if (data.count>0){
				refreshList();
				alert('댓글삭제성공');
			}else {
				alert('댓글삭제실패');
			}
		})
		.catch(function(err) { //요청 처리가 실패한 경우 실행할 함수
			console.log(err); //발생한 오류 정보가 인자로 전달
			alert('댓글삭제실패');
		}); 
		
	};
	
	function refreshList() {
		
		fetch('${pageContext.request.contextPath}/reply/list.do?repBbsNo=${bbsVo.bbsNo}', {
			method: 'GET', //요청방식
			headers: { 'Accept':'application/json' }, //요청헤더
	// 		body: new URLSearchParams({ repBbsNo: ${bbsVo.bbsNo} })
		})
		.then(resp=>resp.json())
		.then(function(data){ //data : 응답메시지의 내용(JSON)을 자바스크립트 객체로 변환한 값
			console.log(data); // [ {}, {}, {}, ... ]
			
// 			var repListHtml = '';
// 			for (var i = 0; i < data.length; i++) {
// 				var v = data[i];
// 				repListHtml += '<div>'+ v.repWriter + '</div>';
// 				repListHtml += '<div>'+ v.repDate + '</div>';
// 				repListHtml += '<div>'+ v.repContent + '</div>';
// 				repListHtml += '<hr>';
// 			}
// 			repListDiv.innerHTML = repListHtml; // repListDiv==<div></div>

			repListDiv.innerHTML = ''; 
// 			for (var i = 0; i < data.length; i++) {
// 				var v = data[i];
// 				var d1 = document.createElement('div'); //<div></div>
// 				d1.textContent = v.repWriter; //<div>v.repWriter</div>
// 				var d2 = document.createElement('div'); //<div></div>
// 				d2.textContent = v.repDate; //<div>v.repDate</div>
// 				var d3 = document.createElement('div'); //<div></div>
// 				d3.textContent = v.repContent; //<div>v.repContent</div>
// 				var h1 = document.createElement('hr'); //<hr></hr> 
// 				repListDiv.append(d1,d2,d3,h1); 
// 			}

			for (var i = 0; i < data.length; i++) {
				var v = data[i];
				var rt = repTemp.content.cloneNode(true);
				rt.querySelector('.writer').textContent = v.repWriter;
				rt.querySelector('.date').textContent = v.repDate;
				rt.querySelector('.content').textContent = v.repContent;
				if ( v.repWriter == '${loginUser.memId}' ){
					rt.querySelector('.del').setAttribute('data-no', v.repNo );
					rt.querySelector('.del').onclick = delReply;
				}else {
					rt.querySelector('.del').remove();
				}
				
					
				repListDiv.append(rt); 
			}
			
		})
		.catch(function(err) { //요청 처리가 실패한 경우 실행할 함수
			console.log(err); //발생한 오류 정보가 인자로 전달
			alert('댓글목록조회실패');
		}); 
	}
	
	refreshList();
	
</script>



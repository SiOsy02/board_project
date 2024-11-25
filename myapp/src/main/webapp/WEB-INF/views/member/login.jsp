<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div>

	<fieldset class="container-sm notosanskr ">
	
		<legend  class="text-center"> 로그인 </legend>
		
			<form class="container mb-3" action="${pageContext.request.contextPath}/member/login.do" method="post">
			
				<div class="mb-3">
				<label class="form-label" for="memId" > 아이디 </label>
				<input class="form-control mb-6" type="text" name="memId"/>
				</div>
				
				<div class="mb-3">
				<label class="form-label" for="memPass" > 비밀번호  </label>
				<input class="form-control mb-6" type="password" name="memPass"/>
				</div>
				
				<input class="btn btn-primary" type="submit" value="로그인">
				
			</form>
			
	</fieldset>
	
</div>

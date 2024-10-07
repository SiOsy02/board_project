<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div>
	<fieldset id="container">
		<legend> <h2> 로그인 </h2> </legend>
			<form class="loginFrm" action="${pageContext.request.contextPath}/member/login.do" method="post">
				<div>
				<label class="label" for="memId" > 아이디 </label>
				<input type="text" name="memId"/>
				</div>
				<div>
				<label class="label" for="memPass" > 비밀번호  </label>
				<input type="password" name="memPass"/>
				</div>
				<input class="submit" type="submit" value="로그인">
			</form>
	</fieldset>
</div>

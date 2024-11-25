package com.exam.myapp.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.exam.myapp.member.service.MemberVo;


public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception { 
		//인자로 요청객체,응답객체,이후실행될핸들러(컨트롤러,인터셉터) 전달
		// 핸들러(컨트롤러) 실행 전에 실행되는 메서드
		// 반환값을 통해서, 이후 실행될 핸들러(컨트롤러,인터셉터)의 실행 여부를 결정 가능
		//	System.out.println("LoginInterceptor:preHandle...");
		
		HttpSession session = request.getSession();
		MemberVo mvo = (MemberVo) session.getAttribute("loginUser");
		if (mvo==null) { //로그인하지 않은 사용자의 요청
			response.sendRedirect( request.getContextPath() + "/member/login.do" );
			return false; //컨트롤러 실행 방지
		}
		
		return true; //컨트롤러 실행
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 핸들러(컨트롤러) 실행 후, 뷰(JSP) 실행 전에 실행되는 메서드
		// 컨트롤러가 반환한 모델과 뷰 정보를 참조 및 변경 가능
//		System.out.println("LoginInterceptor:postHandle...");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 뷰(JSP) 실행 후 (응답 전송 완료 후)에 실행되는 메서드
		// 요청처리시 사용한 리소스의 정리 및 반납 작업 수행 가능
//		System.out.println("LoginInterceptor:afterCompletion...");
	}
	
}

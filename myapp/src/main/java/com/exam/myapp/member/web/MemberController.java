package com.exam.myapp.member.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exam.myapp.member.service.MemberService;
import com.exam.myapp.member.service.MemberVo;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(path="/member/list.do", method = RequestMethod.GET)
	public String list(Model model) {
		List<MemberVo> list = memberService.findAll();
		model.addAttribute("memList", list); 
		return "member/list";
	}

	@RequestMapping(path = "/member/add.do", method = RequestMethod.GET)
	public String addform(MemberVo vo) { //form:form 태그 오류 방지용 모델 객체
		return "member/add";
	}
	
//	@Autowired
//	private MyValidator myValidator;
//	
//	@InitBinder //현재 컨트롤러의 메서드들이 실행되기 전에 항상 한번씩 실행
//	public void init(WebDataBinder binder) {
//		//인자로 모델 객체에 파라미터값을 저장(바인드)해주는 바인더 객체 전달
//		binder.addValidators( myValidator ); //바인딩 할 때 사용할 유효성 검사용 검증기 추가 
//	}
	
	@RequestMapping(path="/member/add.do",method = RequestMethod.POST)
	public String add(MemberVo vo, BindingResult result) {
		int num = memberService.add(vo); 
		System.out.println(num + "명의 회원정보 추가");
		return "redirect:/member/list.do";
	}
	
	@RequestMapping(path="/member/edit.do", method = RequestMethod.GET)
	public String editform( String memId, Model model ) {
		MemberVo vo = memberService.findById( memId );
		model.addAttribute("memVo", vo);
		return "member/edit";
	}
	
	@RequestMapping(path = "/member/edit.do", method = RequestMethod.POST)
	public String edit(@ModelAttribute("memVo") MemberVo vo, BindingResult result) {
		int num = memberService.edit(vo); 
		System.out.println(num + "명의 회원정보 변경");
		return "redirect:/member/list.do";
	}
	
	@RequestMapping(path = "/member/del.do", method = RequestMethod.GET)
	public String del(String memId) {
		int num = memberService.del( memId );
		System.out.println(num + "명의 회원정보 삭제");
		return "redirect:/member/list.do";
	}

	@RequestMapping(path = "/member/login.do", method = RequestMethod.GET)
	public String loginform() {
		return "member/login";
	}
	
	@RequestMapping(path = "/member/login.do", method = RequestMethod.POST)
	public String login(MemberVo vo, HttpSession session) {
		MemberVo mvo = memberService.findById(vo.getMemId()); 
		if ( mvo != null
			 && vo.getMemId().equals( mvo.getMemId() ) 
			 && vo.getMemPass().equals( mvo.getMemPass() )	
		) { // 로그인 성공
			session.setAttribute("loginUser", mvo);
			return "redirect:/bbs/list.do";
		}
		// 로그인 실패
		return "redirect:/member/login.do";
	}
	
	@RequestMapping(path = "/member/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/member/login.do";
	}
	
}



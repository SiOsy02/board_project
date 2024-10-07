package com.exam.myapp.reply.web;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.exam.myapp.member.service.MemberVo;
import com.exam.myapp.reply.service.ReplyService;
import com.exam.myapp.reply.service.ReplyVo;

@Controller 
@RequestMapping("/reply")
public class ReplyController {
	@Autowired
	private ReplyService replyService;

	@PostMapping("/add.do") 
	@ResponseBody //이 메서드의 반환값을 응답 메시지의 본문내용(body)로 전송
	//HttpMessageConverter를 사용하여 반환값을 응답메시지 내용으로 변환
	//Accept 요청헤더와 컨트롤러 반환값의 타입에 따라서 
	//어떤 HttpMessageConverter를 사용할지 결정
	public HashMap<String, Object> add(ReplyVo vo, @SessionAttribute("loginUser") MemberVo mvo) {
		vo.setRepWriter( mvo.getMemId() ); //댓글작성자를 로그인한 사용자로 설정
		int num = replyService.add(vo);
		
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("count", num);
		return map; // '{ "count": 1 }'
	}
	
	@GetMapping("/list.do") @ResponseBody
	public List<ReplyVo> list(int repBbsNo) {
		List<ReplyVo> list = replyService.findAll(repBbsNo);
		return list;
	}
	
	@GetMapping("/del.do") @ResponseBody
	public HashMap<String, Object> del(ReplyVo vo, @SessionAttribute("loginUser") MemberVo mvo) {
		vo.setRepWriter( mvo.getMemId() ); //로그인한 사용자 아이디를 작성자 필드에 저장
				
		int num = replyService.del(vo);
		
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("count", num);
		return map; // '{ "count": 1 }'
	}
	
}

// JSON은 자바스크립트 객체 표현과 동일하지만, 2가지 차이점 존재
// 1. JSON에서 문자열은 쌍따옴표만 사용
// 2. JSON에서 객체의 속성이름은 문자열로 표현


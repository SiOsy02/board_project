package com.exam.myapp.bbs.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exam.myapp.attach.service.AttachVo;
import com.exam.myapp.bbs.service.BbsService;
import com.exam.myapp.bbs.service.BbsVo;
import com.exam.myapp.comm.SearchVo;
import com.exam.myapp.member.service.MemberVo;

@Controller
@RequestMapping("/bbs")
public class BbsController  {
	@Autowired
	private BbsService bbsService;
	

	@GetMapping("/list.do")
	public String list(Model model, SearchVo searchVo) {
		searchVo.setTotalRecordCount( bbsService.countAll(searchVo) );
		List<BbsVo> list = bbsService.findAll(searchVo);
		model.addAttribute("bbsList", list); 
		return "bbs/list";
	}

	@GetMapping("/add.do")
	public String addform() {
		return "bbs/add";
	}
	
	@PostMapping("/add.do")
	public String add(BbsVo vo, HttpSession session) {
		
		MemberVo mvo = (MemberVo) session.getAttribute("loginUser");
		vo.setBbsWriter( mvo.getMemId() );
		
		int num = bbsService.add(vo); 
		
		System.out.println(num + "개의 게시글 추가");
		
		return "redirect:/bbs/list.do";
	}
	
	
	@GetMapping("/edit.do")
	public String editform( int bbsNo, Model model, SearchVo searchVo) {
		BbsVo vo = bbsService.findById( bbsNo );
		model.addAttribute("bbsVo", vo);
		return "bbs/edit";
	}
	
	@PostMapping("/edit.do")
	public String edit(BbsVo vo
			, @SessionAttribute("loginUser") MemberVo mvo
			, SearchVo searchVo
			, RedirectAttributes attrs //리디렉트할때 추가할 파라미터를 저장하는 객체
			) {
		vo.setBbsWriter( mvo.getMemId() );
		int num = bbsService.edit(vo); 
		System.out.println(num + "개의 게시글 정보 변경");
//		return "redirect:/bbs/list.do?currentPageNo="+searchVo.getCurrentPageNo()
//			+ "&searchKey=" + searchVo.getSearchKey() 
//			+ "&searchValue=" + searchVo.getSearchValue();
		//RedirectAttributes 객체에 리디렉트시 추가할 파라미터 이름과 값을 추가
//		attrs.addAttribute("currentPageNo", searchVo.getCurrentPageNo());
//		attrs.addAttribute("searchKey", searchVo.getSearchKey());
//		attrs.addAttribute("searchValue", searchVo.getSearchValue());
		//파라미터가 브라우저 주소에 노출되는 것을 방지하려면 FlashAttribute 사용
		//지정한 이름의 @ModelAttribute("searchVo") 매개변수에게 값을 전달
		attrs.addFlashAttribute("searchVo", searchVo); 
		return "redirect:/bbs/list.do";
	}
	
	@PostMapping("/del.do")
	public String del(BbsVo vo
			, @SessionAttribute("loginUser") MemberVo mvo
			, SearchVo searchVo
			, RedirectAttributes attrs
			) {
		vo.setBbsWriter( mvo.getMemId() );
		int num = bbsService.del( vo );
		System.out.println(num + "개의 게시글 삭제");
		attrs.addFlashAttribute("searchVo", searchVo); 
		return "redirect:/bbs/list.do";
	}
	
	//매개변수로 HttpServletResponse 또는 OuputStream 객체를 받고,
	//반환타입이 void인 경우, 스프링은 컨트롤러가 응답을 직접 전송(처리)했다고 판단하고,
	//컨트롤러 실행 후 요청 처리를 종료 (뷰(JSP)를 실행하지 않음)
	@GetMapping("/down.do")
	public void download(int attNo, HttpServletResponse resp) throws FileNotFoundException, IOException {
		AttachVo vo = bbsService.findAttach(attNo); //지정한 번호의 첨부파일 정보를 조회
		
//		File f = new File("C:/Temp/upload", vo.getAttNewName() );
		File f = bbsService.getAttachFile(vo.getAttNewName());
		
		//어떤 형식의 파일이든지 무조건 다운로드 되도록 설정
//		resp.setHeader("Content-Type", "application/octet-stream");
		resp.setContentType( MediaType.APPLICATION_OCTET_STREAM_VALUE );

		//다운로드한 파일을 저장할 때 사용할 파일명(이름) 설정
		//한글 또는 특수문자가 포함된 파일명은 인코딩 작업 필요
//		String fname = URLEncoder.encode(vo.getAttOrgName(), "UTF-8").replace("+", "%20");
//		resp.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fname);
		String cdv = ContentDisposition
			.attachment()
			.filename(vo.getAttOrgName(), StandardCharsets.UTF_8)
			.build().toString(); //스프링5부터 지원하는 Content-Disposition 헤더값 생성  
		resp.setHeader( HttpHeaders.CONTENT_DISPOSITION, cdv);
		
		//첨부파일의 내용을 읽어서, 응답객체의 내용에 쓰기
		FileCopyUtils.copy( new FileInputStream(f), resp.getOutputStream() );
	}
	
}






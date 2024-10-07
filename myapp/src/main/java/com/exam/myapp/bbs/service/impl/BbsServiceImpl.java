package com.exam.myapp.bbs.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.exam.myapp.attach.service.AttachDao;
import com.exam.myapp.attach.service.AttachVo;
import com.exam.myapp.bbs.service.BbsDao;
import com.exam.myapp.bbs.service.BbsService;
import com.exam.myapp.bbs.service.BbsVo;
import com.exam.myapp.comm.SearchVo;

import lombok.extern.slf4j.Slf4j;


@Slf4j //private static final Logger log = LoggerFactory.getLogger(현재클래스); 자동 생성
@Service 
public class BbsServiceImpl implements BbsService {
//	private static final Logger log = LoggerFactory.getLogger(BbsServiceImpl.class);
	
	@Autowired
	private BbsDao bbsDao;
	@Autowired
	private AttachDao attachDao;
	
	//값 자체를 주입하기 위해서는 @Value(값) 사용
	//스프링에 등록된 프로퍼티 값을 주입하기 위해서는 ${프로퍼티키} 사용
	@Value("${bbs.upload}")
	private String uploadDir; //게시글 첨부파일 저장 경로
	
	@PostConstruct //스프링 등록시, 객체 생성 후 의존성 주입이 완료된 후 자동 실행
	public void init() {
		new File(uploadDir).mkdirs(); //디렉토리 생성
	}
	
	@Override
	public List<BbsVo> findAll(SearchVo searchVo) {
		return bbsDao.findAll(searchVo);
	}
	
	@Override
	public int countAll(SearchVo searchVo) {
		return bbsDao.countAll(searchVo);
	}

	@Override
	public BbsVo findById(int id) {
		return bbsDao.findById(id);
	}

	@Override 
	@Transactional //이 메서드를 하나의 트랜잭션으로 정의
	//이 메서드 내에서 오류가 발생하는 경우, 메서드 실행 전으로 데이터베이스 상태를 롤백 
	public int add(BbsVo vo) {
		int num = bbsDao.add(vo);
		System.out.println(vo.getBbsNo());
		
		List<MultipartFile> upfileList = vo.getUpfileList();
		if (upfileList!=null) {
			for (MultipartFile mf : upfileList) {
				if ( mf==null || mf.getSize() <= 0 ) continue; //파일이 없거나 크기가 0이하면 저장하지 않음
				
				String newFileName; 
				File nf;
				do {
					newFileName = UUID.randomUUID().toString(); //중복확률이 매우 낮은 임의의 문자열 생성
					nf = new File(uploadDir, newFileName);
				} while (nf.exists()); //이미 존재하는 파일인 경우 이름을 다시 생성
				
				log.debug("게시판 첨부파일 저장 : {} => {}", mf.getOriginalFilename(), newFileName);
				try {
					FileCopyUtils.copy(mf.getBytes(), nf);
					AttachVo avo = new AttachVo();
					avo.setAttOrgName( mf.getOriginalFilename() ); //첨부파일의 원래이름
					avo.setAttNewName(newFileName); //첨부파일의 서버저장이름
					avo.setAttBbsNo( vo.getBbsNo() ); //첨부파일이 속한 게시글 번호
					attachDao.add( avo );
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e); //첨부파일 저장중 오류발생시 롤백되도록
				}
			}
		}
		return num;
	}

	@Override
	public int edit(BbsVo vo) {
		return bbsDao.edit(vo);
	}

	@Override @Transactional
	public int del(BbsVo vo) {
		//삭제할 게시글에 속한 첨부파일 정보 조회 (파일을 디스크에서 삭제하기 위하여)
		List<AttachVo> attList = attachDao.findAllByBbsNo(vo.getBbsNo());
		
		//삭제할 게시글에 속한 첨부파일 정보를 (첨부파일 테이블에서) 삭제
		int num = attachDao.del(vo.getBbsNo()); //인자로 전달한 게시글번호에 속한 첨부파일 삭제
		
		//게시글 삭제
		num = bbsDao.del(vo);
		if (num==0) throw new RuntimeException("삭제 권한 없음");
		
		//서버 하드디스크에서 첨부파일 삭제
		for (AttachVo avo : attList) { 
			new File(uploadDir, avo.getAttNewName()).delete();
		}
		
		return num;
	}

	@Override
	public AttachVo findAttach(int attNo) {
		return attachDao.findById(attNo);
	}

	@Override
	public File getAttachFile(String attNewName) {
		return new File(uploadDir, attNewName);
	}

}





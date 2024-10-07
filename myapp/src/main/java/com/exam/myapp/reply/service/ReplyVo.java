package com.exam.myapp.reply.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReplyVo {
  private int repNo; //NUMBER(10,0), -- 댓글번호
  private String repContent; //CLOB, -- 댓글내용
  private String repWriter; //VARCHAR2(50), -- 작성자아이디
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
  private Date repDate; //DATE DEFAULT SYSDATE, --등록일시
  private int repBbsNo; //NUMBER(10,0),  -- 게시글번호
}

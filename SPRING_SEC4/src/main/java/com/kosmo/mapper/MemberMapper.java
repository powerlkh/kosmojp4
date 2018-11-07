package com.kosmo.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.kosmo.member.MemberVO;

//SQL> desc member;
//이름                                      널?      유형
//----------------------------------------- -------- ----------------------------
//MSEQ                                      NOT NULL NUMBER(38)
//MID                                                VARCHAR2(20)
//MPW                                                VARCHAR2(20)
//MNAME                                  	            VARCHAR2(20)
//MGUBUN                                             CHAR(1)
//REGDATE                                            DATE


//create table member (
//	MSEQ                                     NUMBER primary key,
//	MID                                                VARCHAR2(20),
//	MPW                                                VARCHAR2(20),
//	MNAME                                  	            VARCHAR2(20),
//	MGUBUN                                             CHAR(1),
//	REGDATE                                            DATE default sysdate
//);
//
//create sequence member_seq start with 1 increment by 1;
//insert into member values(member_seq.nextval, 'admin', '111','관리자','a', sysdate);
//insert into member values(member_seq.nextval, 'lkh', '111','경씨','u', sysdate);
//commit;
//select * from member;
//

@Repository("memberMapper")
public interface MemberMapper {
	public MemberVO memberSearchByID(String mid);
	public int memberDelete(int mid);
	public int memberInsert(MemberVO memberVO);
	public ArrayList<MemberVO> memberList();
}

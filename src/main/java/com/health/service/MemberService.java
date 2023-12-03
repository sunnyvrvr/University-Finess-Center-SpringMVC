package com.health.service;

import com.health.domain.MemberVO;

public interface MemberService {
	public MemberVO signup(MemberVO member); //회원가입
	public boolean idDupChk(String loginId); //아이디중복확인
	public MemberVO login(String loginId, String pwd); //로그인
	public boolean studentIdDupChk(String studentId); //학번중복확인
	public MemberVO selectById(String logindId); //마이페이지
}

package com.health.mapper;

import java.util.List;

import com.health.domain.MemberVO;

public interface MemberMapper {
	public int insert(MemberVO member);//회원가입
	public List<MemberVO> findAll(); //회원전체확인
	public MemberVO findOne(String loginId); //회원확인- 로그인, 마이페이지
	public MemberVO findOneStudent(String studentId); //회원확인- 로그인, 마이페이지
	public int delete(String loginId); //회원탈퇴
}

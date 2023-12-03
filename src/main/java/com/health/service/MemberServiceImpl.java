package com.health.service;

import java.lang.module.FindException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.domain.MemberVO;
import com.health.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {
	
	@Setter(onMethod_=@Autowired)
	private MemberMapper mapper;

	@Override
	public MemberVO signup(MemberVO member) {
		mapper.insert(member);
		log.info("회원가입:"+member);
		MemberVO m = mapper.findOne(member.getLoginId());
		return m;
	}

	@Override
	public boolean idDupChk(String loginId) {
		MemberVO m=mapper.findOne(loginId);
		if(m != null) {
			return false; 
		}
		return true;
	}

	@Override
	public boolean studentIdDupChk(String studentId) {
		MemberVO m=mapper.findOneStudent(studentId);
		if(m != null) {
			return false;
		}
		return true;
	}

	@Override
	public MemberVO login(String loginId, String pwd) throws FindException {
		MemberVO m=mapper.findOne(loginId);
		log.info("로그인:"+m);
		
		if(m == null) {
			throw new FindException("회원이 존재하지 않습니다.");
		}
		if(pwd.equals(m.getPwd())){
			return m; 					
		} else{
	        throw new FindException("비밀번호 불일치");		
		}
	}
	

	@Override
	public MemberVO selectById(String logindId) {
		MemberVO m=mapper.findOne(logindId);
		log.info("회원찾기:"+m);
		mapper.findOne(logindId);
		return m;
	}

}

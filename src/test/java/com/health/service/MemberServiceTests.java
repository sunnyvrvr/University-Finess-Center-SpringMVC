package com.health.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.health.domain.MemberVO;
import com.health.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberServiceTests {

	@Setter(onMethod_=@Autowired)
	private MemberService service;
	
	@Test
	public void testSingup() {
		MemberVO member = new MemberVO();
		//회원가입
		System.out.println("-------회원가입-------");	
		member.setLoginId("id18");
		member.setPwd("1234");
		member.setStudentId("25388");
		member.setMStatus("재학생");
		member.setName("이름");
		member.setPhone("1234");
		member.setEmail("123@naver.com");
		member.setAccountBank("토스");		
		member.setAccountNo("123");
		
		service.signup(member);
		System.out.println("-------회원가입 결과확인-----");	
		log.info(service.selectById("id18"));
	}
	
	@Test
	public void testLogin() {
		MemberVO member = new MemberVO();
		
		//로그인
		System.out.println("로그인");
		log.info(service.login("id1","1234"));

		//회원정보가져오기 
		System.out.println("회원정보가져오기");
		log.info(service.selectById("id2"));
	}
}

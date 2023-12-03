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
		//ȸ������
		System.out.println("-------ȸ������-------");	
		member.setLoginId("id18");
		member.setPwd("1234");
		member.setStudentId("25388");
		member.setMStatus("���л�");
		member.setName("�̸�");
		member.setPhone("1234");
		member.setEmail("123@naver.com");
		member.setAccountBank("�佺");		
		member.setAccountNo("123");
		
		service.signup(member);
		System.out.println("-------ȸ������ ���Ȯ��-----");	
		log.info(service.selectById("id18"));
	}
	
	@Test
	public void testLogin() {
		MemberVO member = new MemberVO();
		
		//�α���
		System.out.println("�α���");
		log.info(service.login("id1","1234"));

		//ȸ�������������� 
		System.out.println("ȸ��������������");
		log.info(service.selectById("id2"));
	}
}

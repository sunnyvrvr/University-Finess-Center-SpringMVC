package com.health.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.health.domain.MemberVO;
import com.health.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberMapperTests {

	@Setter(onMethod_=@Autowired)
	private MemberMapper mapper;
	
	@Test
	public void testFindOne() {
		MemberVO member = mapper.findOne("id1");
		System.out.println("ȸ��Ȯ��");
		log.info("ȸ�� �Ѹ�Ȯ��:"+member);
	}
	
	@Test
	public void testFindAll() {
		List<MemberVO> memberlist = mapper.findAll();
		System.out.println("ȸ�� ��üȮ��");
		log.info("ȸ�� ��üȮ��:"+memberlist);
	}	
	

	@Test
	public void testInsert() {
		MemberVO member = new MemberVO();
		System.out.println("------------ȸ�� ����------------");
		member.setLoginId("id11");
		member.setPwd("1234");
		member.setStudentId("2134");
		member.setMStatus("���");
		member.setName("�̸�");
		member.setPhone("1234");
		member.setEmail("123@naver.com");
		member.setAccountBank("�佺");		
		member.setAccountNo("123");
		mapper.insert(member);
		System.out.println("------------����ȸ��Ȯ��-------------");		
		member = mapper.findOne("id10");
		log.info("������ ȸ�� �Ѹ�Ȯ��:"+member);
	}
	
	@Test
	public void testDelete() {
		MemberVO member = new MemberVO();
		
		int count = mapper.delete("id10");
		log.info("Ż��ȸ��Ȯ��"+ count);
	}
	
}

package com.health.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.health.domain.MemberVO;
import com.health.domain.ProgramVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ProgramMapperTests {
	
	@Setter(onMethod_=@Autowired)
	private ProgramMapper mapper;

	@Test
	public void testFindAll() {		
		List<ProgramVO> programList = mapper.findAll();
		System.out.println("전체목록");
		log.info("전체프로그램 목록"+programList);
	}
	
	@Test
	public void testFindOne() {
		ProgramVO program = mapper.findOne("G001");
		System.out.println("--------------선택 프로그램--------------");
		log.info("선택프로그램"+program);
	}
	
	@Test
	public void testFindOneProgram() {
		ProgramVO programpage = mapper.findOneProgram("H001");
		System.out.println("--------------프로그램 상세-------------");
		log.info("프로그램 상세"+programpage);
	}
	
	@Test 
	public void testFindCategory() {
		List<ProgramVO> programCategorylist = mapper.findCategory("gxprogram");
		System.out.println("------카테고리목록------");
		log.info("프로그램 카테고리로 조회"+programCategorylist);
	
	}
}

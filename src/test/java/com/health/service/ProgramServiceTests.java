package com.health.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.health.domain.ProgramVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ProgramServiceTests {

	@Setter(onMethod_=@Autowired)
	private ProgramService service;
	
	@Test
	public void testselectByProgramNo() {
		ProgramVO program = new ProgramVO();
		System.out.println("테스트 전");
		log.info(service.selectByProgramNo("G001"));
	}
	
	@Test
	public void testselectDetailByProgramno() {
		ProgramVO program = new ProgramVO();
		System.out.println("테스트 전");
		log.info(service.selectDetailByProgramNo("G001"));

	}
}

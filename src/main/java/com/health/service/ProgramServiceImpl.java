package com.health.service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.domain.ProgramVO;
import com.health.mapper.ProgramMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ProgramServiceImpl implements ProgramService {

	@Setter(onMethod_=@Autowired)
	private ProgramMapper mapper;
	
	//전체 프로그램 확인
	@Override
	public List<ProgramVO> selectProgram() {
		List<ProgramVO> programlist = new ArrayList<ProgramVO>();
		log.info("프로그램 전체 조회"+programlist);
		return programlist;
	}

	//프로그램 번호로 확인
	@Override
	public ProgramVO selectByProgramNo(String programNo) {
		ProgramVO p = mapper.findOne(programNo);
		log.info("프로그램 번호로 조회"+p);
		return p;
	}
	//프로그램 상세
	@Override
	public ProgramVO selectDetailByProgramNo(String programNo) {
		ProgramVO p = mapper.findOneProgram(programNo);
		log.info("프로그램 번호로 상세조회"+p);
		return p;
	}
	//프로그램 카테고리로 확인
	@Override
	public List<ProgramVO> selectByCategory(String programCategory) {
		List<ProgramVO> programCategorylist = mapper.findCategory(programCategory);
		log.info("프로그램 카테고리로 조회"+programCategorylist);
		return programCategorylist;
	}
	
	//카트 프로그램 확인
	@Override
	public List<ProgramVO> selectCartProgram(String programNo) {
		List<ProgramVO> clist = new ArrayList<ProgramVO>();
		log.info("프로그램 카트 리스트 조회"+clist);
		return clist;
	}

}

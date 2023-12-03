package com.health.mapper;

import java.util.List;
import java.util.Map;

import com.health.domain.ProgramVO;

public interface ProgramMapper {
//	public int insert(ProgramVO program);
	public List<ProgramVO> findAll();
	public ProgramVO findOne(String programno);	//프로그램 번호로 확인
	public List<ProgramVO> findCategory(String programCategory);	//프로그램 카테고리로 확인
//	public ProgramVO update(String programno);	
//	public int delete(String programno);
	public ProgramVO findOneProgram(String programno); //상세
}

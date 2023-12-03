package com.health.service;

import java.util.List;
import java.util.Map;

import com.health.domain.ProgramVO;

public interface ProgramService {
	//public ProgramVO signup(ProgramVO program); //프로그램등록
	public List<ProgramVO> selectProgram(); //전체 프로그램 확인
	public List<ProgramVO> selectCartProgram(String programNo); //카트 프로그램확인
	public ProgramVO selectByProgramNo(String programNo); //프로그램 번호로 확인	
	public List<ProgramVO> selectByCategory(String programCategory);//프로그램 카테고리로 확인
	public ProgramVO selectDetailByProgramNo(String programNo); //프로그램 상세
	//public ProgramVO update(ProgramVO program); //프로그램 수정
	//public int delete(ProgramVO program); //프로그램 삭제
}

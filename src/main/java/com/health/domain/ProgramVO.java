package com.health.domain;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Data @Setter @Component
public class ProgramVO {
	String programNo;   //프로그램번호 
	String programName;  //강좌명
	String programCategory; //프로그램카테고리 
	String center;		//센터
	String place;		//장소
	int    maxCapacity; //정원
	String programMonth;//수강년월
	String programTime;	//시간
	String programDetail; //강좌상세
	String programImage; //강좌 이미지	
	int programPrice; //강좌가격
}

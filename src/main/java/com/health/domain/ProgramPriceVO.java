package com.health.domain;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Data @Setter @Component
public class ProgramPriceVO {
	String programNo; //프로그램명
	int stuPrice;  //재학생가격
	int alumPrice; //졸업생가격
}

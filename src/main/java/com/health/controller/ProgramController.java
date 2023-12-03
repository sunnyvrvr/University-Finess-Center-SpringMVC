package com.health.controller;

import java.lang.module.FindException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.health.domain.MemberVO;
import com.health.domain.ProgramVO;
import com.health.service.ProgramService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class ProgramController {
	
	@Setter(onMethod_=@Autowired)
	private ProgramService service;
	
	@GetMapping("/health")
	public String health(Model model, HttpSession session, MemberVO member) {
		log.warn("session member:" + session.getAttribute("member"));	
		return "health";
	}
	
	@GetMapping(value="/healthdata", produces= {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
	public @ResponseBody String healthdata(Model model, ProgramVO program,String programCategory,HttpServletResponse response, HttpSession session) {
		response.setContentType("application/json; charset=UTF-8");
		String programJson ="";		
		try {
			List<ProgramVO> programCategorylist = service.selectByCategory("health");
			Gson plist = new Gson();
			programJson =plist.toJson(programCategorylist);
			log.info("헬스프로그램목록"+ programJson);
     
		} catch (FindException e) {			
			e.printStackTrace();			
		}	
		return programJson;
	}

	
	@GetMapping("/gxprogram")
	public String gxprogram(Model model, HttpServletResponse response){	
		return "gxprogram";
	}	
	
	@GetMapping(value="/gxprogramdata", produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public @ResponseBody String gxprogramdata(Model model, ProgramVO program, HttpSession session, String programCategory,HttpServletResponse response) {
		String programJson ="";		
		log.info("gx프로그램목록"+ programJson);
		
		try {
			List<ProgramVO> programCategorylist = service.selectByCategory("gxprogram");
			Gson plist = new Gson();
			programJson =plist.toJson(programCategorylist);
			log.warn("programCategorylist:" + programJson);
  
		} catch (FindException e) {			
			e.printStackTrace();			
		}	
		return programJson;
	}
	
	@GetMapping("/program")
	public String program(@RequestParam("programNo") String programNo, 
			Model model, HttpSession session, HttpServletResponse response, HttpServletRequest request, ProgramVO program) {
		
		session.getAttribute("programNo");
		// 모델에 programNo 저장
		log.warn("param:" + programNo);
	    model.addAttribute("programNo", service.selectByProgramNo(programNo));
		log.warn("프로그램 페이지로 넘어갈때 session member:" + session.getAttribute("member"));	
		return "program";
	}
	
	@GetMapping(value="/programdata", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public @ResponseBody String programdata(Model model,  HttpSession session, String programNo, ProgramVO program, MemberVO member) {
		String programJson ="";	

		try {
			session.getAttribute("programNo");
			ProgramVO p  = service.selectDetailByProgramNo(programNo);
			
			Gson pdetail = new Gson();
			programJson = pdetail.toJson(p);
			log.warn("-----------------programdata의 programNo:------------------" + programNo);
			log.warn("프로그램 상세"+programJson);
		}catch(FindException e) {
			e.printStackTrace();
		}
		return programJson;
	}
	
	
	@GetMapping("/cart")
	public String cart(Model model, HttpSession session, MemberVO member) {
		log.warn("session member:" + session.getAttribute("member"));	
		return "cart";
	}

	
	@GetMapping(value="/cartdata", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public @ResponseBody String cartGet(Model model, HttpSession session,  String programNo, HttpServletResponse response, HttpServletRequest request) {
		String cartJson= "";
		log.warn("cart:" + programNo);

		Cookie[] Cookies = request.getCookies();
		if(Cookies == null) {
			System.out.println("장바구니 비었음"); 
		}
		
		try {
			List<String> cartcookies = Arrays.asList(programNo.replace("[", "").replace("]", "").replace("\"","").split(","));
			log.info("리스트 cartcookies "+cartcookies); //쿠키 출력
			System.out.println("------쿠키번호 하나씩 출력------");
			cartcookies.forEach(p->System.out.println(p)); //쿠키에 들어간 프로그램번호 출력
			
			//List<ProgramVO> clist = new ArrayList<>(); 
			//출력되는 문자열 clist - 빈 문자열			
			//반복문 변경				
			List<ProgramVO> programclist = new ArrayList<>(); 
			//출력 리스트 - programclist로 변경
			for(String p:cartcookies) {
				programclist.add(service.selectDetailByProgramNo(p.trim()));
				//clist.addAll(programclist);//
			}
			log.warn("쿠키들어간 프로그램번호 출력"+programclist);
		
			//log.warn("쿠키들어간 프로그램번호의 정보 리스트"+clist); //쿠키에 들어간 프로그램번호 리스트

			Gson pcart = new Gson();
			cartJson = pcart.toJson(programclist);
			log.warn("member:" + session.getAttribute("member"));		
		}catch(FindException e) {
			e.printStackTrace();
		}
		return cartJson;
	}
	
	
	
	@GetMapping("/locker")
	public String locker(Model model){		
		return "locker";
	}	
	
	
	@GetMapping("/myhistory") 
	public String myhistory(Model model){		
		return "myhistory";
	}	
}

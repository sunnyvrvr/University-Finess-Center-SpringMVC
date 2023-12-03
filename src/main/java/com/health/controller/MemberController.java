package com.health.controller;


import java.lang.module.FindException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.health.domain.MemberVO;
import com.health.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Log4j
@Controller
public class MemberController {

	@Setter(onMethod_=@Autowired)
	private MemberService service;

	@GetMapping("/signup")
	public String signup(Model model){		
		return "signup";
	}

	@PostMapping("/signup")
	public @ResponseBody String signupPost
	(Model model, MemberVO member, HttpSession session) {
		System.out.println(member);
		String signupJson = "";
		try {
			MemberVO m = service.signup(member);
			model.addAttribute(m);
			//service.signup(member);
			session.setAttribute("member", m);
			log.info("회원가입:" + m);
			signupJson = "success";
		} catch (Exception e) {
			e.printStackTrace();            
		}		
		return signupJson;
	}
	
	@GetMapping("/iddupchk")
	public String iddupchk(Model model){
		return "iddupchk";
	}

    @PostMapping("/iddupchk")
    public @ResponseBody String idDupChk(Model model,@RequestParam("idcheck") String loginId) {
        System.out.println(loginId);
        boolean idDuplicate = true;
		String idJson = "";

		try {
			idDuplicate = service.idDupChk(loginId);
			if (idDuplicate == false) {
				System.out.println("중복아이디");
				idJson = ""; 
			} else if(idDuplicate == true) {
				System.out.println("아이디 사용가능");           	
				idJson = "available"; 
			}            
		} catch (FindException e) {
			e.printStackTrace();            
		}
		return idJson;
    }
    
    @GetMapping("/studentiddupchk")
    public String studentIdDupChk(Model model) {
    	return "studentiddupchk";
    }
    
    @PostMapping("/studentiddupchk")
    public @ResponseBody String studentIdDupChkPost(Model model, @RequestParam("studentidcheck") String studentId) {
    	System.out.println(studentId);
    	boolean studentDuplicate = true;
		String studentJson = "";
		
		try {
			studentDuplicate = service.studentIdDupChk(studentId);
			if(studentDuplicate == false) {
				System.out.println("학번 중복");
				studentJson = "";
			} else if(studentDuplicate == true) {
				System.out.println("학번 확인");
				studentJson = "available";
			}
		} catch (FindException e){
			e.printStackTrace();  	
		}
		return studentJson;
    }
    
	@GetMapping("/login")
	public String login(Model model){
		return "login";
	}
	
	@PostMapping("/login")
	public @ResponseBody String loginPost
	(Model model, MemberVO member, HttpSession session, String loginId, String pwd, RedirectAttributes rttr) {
		String memberJson = "";
		log.warn("로그인:" + member);
			
		try {
			MemberVO m = service.login(loginId, pwd);
			model.addAttribute(m);	
			session.setAttribute("member", m);
			log.warn("loginedId:" + loginId);
			Gson student = new Gson();
			memberJson =student.toJson(m);
	
		} catch (FindException e) {			
			e.printStackTrace();
	        rttr.addFlashAttribute("errorMsg", "로그인 실패");			
		}	
		return memberJson;
	}
	
	@GetMapping("/logout")
	public String logout(Model model){
		return "logout";
	}
	
	
	@PostMapping("/logout")
	public void logout(Model model, MemberVO member, HttpSession session, RedirectAttributes rttr){
		MemberVO m = (MemberVO) session.getAttribute("member");
		session.removeAttribute("member");
		session.invalidate();
	}
	
	@GetMapping("/mypage")
	public String mypage(Model model){
		return "mypage";
	}
	

	
	@ExceptionHandler(FindException.class)
	public ResponseEntity handleServerException(Exception ex) {
		  return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
	}

}

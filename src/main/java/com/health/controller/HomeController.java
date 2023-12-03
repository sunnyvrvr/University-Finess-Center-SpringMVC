package com.health.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.health.domain.MemberVO;

import lombok.extern.log4j.Log4j;


@Log4j
@Controller
public class HomeController {
		
	@GetMapping("/index")
	public String home(Locale locale, Model model, HttpSession session, MemberVO member) {
		log.warn("session member:" + session.getAttribute("member"));				
		return "index";
	}	
	
	@GetMapping("/notice")
	public String notice(Model model) {
		return "notice";
	}
	
}






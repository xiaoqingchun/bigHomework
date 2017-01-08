package com.xiaoqingchun.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("/login")
	public String logIn(){
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logOut(HttpSession session){
		session.invalidate();
		return "redirect:login";
	}
	
	@RequestMapping("/public")
	public String pul(){
		return "public";
	}
	
}

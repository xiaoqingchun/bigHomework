package com.xiaoqingchun.web.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoqingchun.service.ContentService;
import com.xiaoqingchun.service.PersonService;
import com.xiaoqingchun.service.TrxService;
import com.xiaoqingchun.model.common.Product;
import com.xiaoqingchun.model.common.User;

@Controller
@RequestMapping(value = "/api")
public class ApiController {

	@Autowired
	private PersonService personService;
	@Autowired
	private ContentService contentService;
	@Autowired
	private TrxService trxService;


	@RequestMapping(value = "/login")
	public String userLogin(@Param("userName") String userName, @Param("password") String password, Model map,
			HttpSession session) {
		if (personService.login(userName, password)) {
			session.setAttribute("user", personService.getUser(userName));
			map.addAttribute("code", 200);
			map.addAttribute("message", "comlpeted");
			map.addAttribute("result", true);
			return "json";
		} else {
			map.addAttribute("code", 201);
			map.addAttribute("message", "UserName or Password Error,please try again.");
			map.addAttribute("result", false);
			return "json";
		}
	}

	@RequestMapping(value = "/delete")
	public String productDelete(@Param("id") Integer id, Model map, HttpSession session) {
		if (session.getAttribute("user") != null) {
			if (contentService.deleteByPrimaryKey(id)) {
				map.addAttribute("code", 200);
				map.addAttribute("message", "comlpeted");
				map.addAttribute("result", true);
			} else {
				map.addAttribute("code", 201);
				map.addAttribute("message", "failed");
				map.addAttribute("result", false);
			}
			return "json";
		} else {
			map.addAttribute("code", 201);
			map.addAttribute("message", "please login !");
			map.addAttribute("result", false);
			return "json";
		}
	}

	@RequestMapping(value = "/buy")
	public String productBuy(@Param("id") Integer id, Model map, HttpSession session) {
		if (session.getAttribute("user") != null) {
			Product content = contentService.getProduct(id);
			long buytime = new Date().getTime();
			trxService.buy(id, ((User) session.getAttribute("user")).getId(), content.getPrice(), buytime);
			map.addAttribute("code", 200);
			map.addAttribute("message", "comlpeted");
			map.addAttribute("result", true);
			return "json";
		} else {
			map.addAttribute("code", 201);
			map.addAttribute("message", "please login !");
			map.addAttribute("result", false);
			return "json";
		}
	}

}

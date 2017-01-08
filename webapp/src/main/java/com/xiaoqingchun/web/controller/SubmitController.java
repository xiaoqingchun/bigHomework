package com.xiaoqingchun.web.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiaoqingchun.model.common.Product;
import com.xiaoqingchun.service.ContentService;

@Controller
public class SubmitController {

	@Autowired
	private ContentService contentService;

	@RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
	public String pubSubmit(Product product, Model map, HttpSession session) {
		if (session.getAttribute("user") != null) {
			try {
				map.addAttribute("product", contentService.insertProduct(product));
			} catch (Exception e) {
				e.printStackTrace();
				map.addAttribute("code", 400);
				map.addAttribute("message", "erorr");
				map.addAttribute("result", false);
			}
			map.addAttribute("code", 200);
			map.addAttribute("message", "comlpeted");
			map.addAttribute("result", true);
			return "publicSubmit";
		} else {
			map.addAttribute("code", 201);
			map.addAttribute("message", "please login !");
			map.addAttribute("result", false);
			return "json";
		}
	}

	@RequestMapping("/editSubmit")
	public String editSubmit(Product product, Model map, HttpSession session) {
		if (session.getAttribute("user") != null) {
			try {
				map.addAttribute("product", contentService.updateProduct(product));
			} catch (Exception e) {
				e.printStackTrace();
				map.addAttribute("code", 400);
				map.addAttribute("message", "erorr");
				map.addAttribute("result", false);
			}
			map.addAttribute("code", 200);
			map.addAttribute("message", "comlpeted");
			map.addAttribute("result", true);
			return "editSubmit";
		} else {
			map.addAttribute("code", 201);
			map.addAttribute("message", "please login !");
			map.addAttribute("result", false);
			return "json";
		}
	}

	@RequestMapping("/edit")
	public String edit(@Param("id") Integer id, Model map, HttpSession session) {
		if (session.getAttribute("user") != null) {
			try {
				map.addAttribute("product", contentService.getProduct(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "edit";
		} else {
			map.addAttribute("code", 201);
			map.addAttribute("message", "please login !");
			map.addAttribute("result", false);
			return "json";
		}
	}

}

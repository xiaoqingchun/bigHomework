package com.xiaoqingchun.web.controller;

import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoqingchun.service.ContentService;
import com.xiaoqingchun.service.TrxService;
import com.xiaoqingchun.model.common.ProductList;
import com.xiaoqingchun.model.common.User;


@Controller
@Component
public class ShowController {
	@Autowired
	private ContentService contentService;
	@Autowired
	private TrxService trxService;

	@RequestMapping("/")
	public String showIndex(Model map, HttpSession session) {
		try {
			if (session.getAttribute("user") != null) {
				LinkedList<ProductList> productList   = (LinkedList<ProductList>) contentService.getProductList(((User) session.getAttribute("user")).getId());
				map.addAttribute("productList", productList);
			} else {
				LinkedList<ProductList> productList  = (LinkedList<ProductList>) contentService.getAllProductList();
				map.addAttribute("productList", productList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

	@RequestMapping("/show")
	public String showGoodsList(@Param("id")Integer id, Model map, HttpSession session) {
		try {
			if (session.getAttribute("user") != null) {
				map.addAttribute("product",contentService.getProduct(id));
			} else {
				map.addAttribute("product", contentService.getProduct(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";

	}

	@RequestMapping("/account")
	public String showAccount(Model map, HttpSession session) {
		map.addAttribute("buyList", trxService.getBuyList(((User) session.getAttribute("user")).getId()));
		return "account";
	}

}

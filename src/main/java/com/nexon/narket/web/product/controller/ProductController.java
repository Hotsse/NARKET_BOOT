package com.nexon.narket.web.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexon.narket.core.base.BaseController;

@Controller("webProductController")
@RequestMapping(value = "/web/product")
public class ProductController extends BaseController {
	
	@GetMapping("")
	public String index() throws Exception {
		return "redirect:/web/product/list";
	}
	
	@GetMapping("/list")
	public String list(ModelMap model) throws Exception {		
		return "list";
	}
	
	@GetMapping("/write")
	public String write() throws Exception {
		return "write";
	}
}

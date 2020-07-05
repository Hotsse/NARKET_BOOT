package com.nexon.narket.web.myhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexon.narket.core.base.BaseController;

@Controller("webMyhomeController")
@RequestMapping(value = "/web/myhome")
public class MyhomeController extends BaseController {
	
	@GetMapping("")
	public String index() throws Exception {
		return "myhome/index";
	}
	
	@GetMapping("/sell")
	public String sell() throws Exception {		
		return "myhome/sell";
	}
	
	@GetMapping("/buy")
	public String buy() throws Exception {		
		return "myhome/buy";
	}
	
	@GetMapping("/scrap")
	public String scrap() throws Exception {		
		return "myhome/scrap";
	}

}

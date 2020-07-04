package com.nexon.narket.web.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexon.narket.core.base.BaseController;

@Controller
@RequestMapping(value={"/","/web"})
public class HomeController extends BaseController {
	
	@GetMapping("")
	public String index() throws Exception {
		return "redirect:/web/product";
	}

}

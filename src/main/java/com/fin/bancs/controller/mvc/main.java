package com.fin.bancs.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bank")
public class main {
	
	@GetMapping("/main")
	public String mainPage() {
		return "mainpage/tiles";
	}
}

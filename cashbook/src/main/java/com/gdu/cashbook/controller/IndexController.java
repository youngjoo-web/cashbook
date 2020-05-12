package com.gdu.cashbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("/index")//인덱스 화면으로 이동 주소
	public String index() {
		return "index";
	}
}

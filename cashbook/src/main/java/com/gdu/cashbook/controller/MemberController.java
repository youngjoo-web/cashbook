package com.gdu.cashbook.controller;

import java.lang.reflect.Member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
	@GetMapping("/addMember")
	public String addMember() {
		return "addMember";
	}
	@PostMapping("/addMember")
	public String addMember(Member member) {//Command 객체
		System.out.println(member);
		return "redirect:/index";
	}
}

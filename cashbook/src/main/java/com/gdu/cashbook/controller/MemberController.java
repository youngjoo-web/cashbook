package com.gdu.cashbook.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gdu.cashbook.service.MemberService;
import com.gdu.cashbook.vo.Member;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/addMember")
	public String addMember() {//회원가입 입력폼으로 이동
		return "addMember";
	}
	@PostMapping("/addMember")
	public String addMember(Member member) {//입력폼에서 받아온 데이터(post) ----->데이터베이스로 이동
		System.out.println(member);
		memberService.addMember(member);
		return "redirect:/index";
	}
}

package com.gdu.cashbook.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gdu.cashbook.service.MemberService;
import com.gdu.cashbook.vo.LoginMember;
import com.gdu.cashbook.vo.Member;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/login")//login Form으로 이동
	public String login() {
		return "login";
	}
	@PostMapping("/login")//login Action 실행
	public String login(LoginMember loginMember, HttpSession session) {
		System.out.println(loginMember);//받아온값 출력
		LoginMember returnLoginMember = memberService.login(loginMember);//쿼리실행
		System.out.println("returnLoginMember:"+returnLoginMember);//쿼리결과 출력
		if(returnLoginMember == null) {//로그인실패(쿼리결과물 없음)
			return "redirect:/login";
		}else {//로그인성공(쿼리결과 있음)
			session.setAttribute("loginMember", returnLoginMember);//세션값에 저장
			return "redirect:/";
		}	
	}
	@GetMapping("/logout")//세션값 초기화
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
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

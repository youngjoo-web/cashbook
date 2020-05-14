package com.gdu.cashbook.controller;



import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.cashbook.service.MemberService;
import com.gdu.cashbook.vo.LoginMember;
import com.gdu.cashbook.vo.Member;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/memberInfo")
	public String memberInfo(HttpSession session, Model model) {
		if(session.getAttribute("loginMember") ==null) {
			return "redirect:/";
		}
		Member member = memberService.getMemberOne((LoginMember)(session.getAttribute("loginMember")));
		System.out.println(member);
		model.addAttribute("member", member);
		return "memberInfo";
	}
	@PostMapping("/checkMemberId")//아이디 중복확인
	public String checkMemberId(Model model, HttpSession session ,@RequestParam("memberIdCheck") String memberIdCheck) {
		if(session.getAttribute("loginMember") !=null) {
			return "redirect:/";
		}
		String confirmMemberId = memberService.checkMemberId(memberIdCheck);//입력한 아이디와 일치하는 데이터 저장
		if(confirmMemberId != null) {//일치하는데이터가 있음
			model.addAttribute("msg", "사용중인아이디입니다.");
		}else {//일치하는데이터가 없음
			model.addAttribute("memberIdCheck", memberIdCheck);
		}
		return "addMember";
	}
	
	@GetMapping("/login")//login Form으로 이동
	public String login(HttpSession session) {
		// 로그인중일때
		if(session.getAttribute("loginMember") !=null) {
			return "redirect:/";
		}
		//로그인되어있지 않을때
		return "login";
	}
	@PostMapping("/login")//login Action 실행
	public String login(LoginMember loginMember, Model model, HttpSession session) {
		System.out.println(loginMember);//받아온값 출력
		LoginMember returnLoginMember = memberService.login(loginMember);//쿼리실행
		System.out.println("returnLoginMember:"+returnLoginMember);//쿼리결과 출력
		if(returnLoginMember == null) {//로그인실패(쿼리결과물 없음)
			model.addAttribute("msg", "아이디와 비밀번호를 확인하시오");
			return "login";
		}else {//로그인성공(쿼리결과 있음)
			session.setAttribute("loginMember", returnLoginMember);//세션값에 저장
			return "redirect:/home";
		}	
	}
	@GetMapping("/logout")//세션값 초기화
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	@GetMapping("/addMember")
	public String addMember(HttpSession session) {//회원가입 입력폼으로 이동
		if(session.getAttribute("loginMember") !=null) {
			return "redirect:/";
		}
		return "addMember";
	}
	@PostMapping("/addMember")
	public String addMember(Member member) {//입력폼에서 받아온 데이터(post) ----->데이터베이스로 이동
		System.out.println(member);
		memberService.addMember(member);
		return "redirect:/index";
	}
}

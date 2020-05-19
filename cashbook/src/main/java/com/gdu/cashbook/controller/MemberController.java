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
import com.gdu.cashbook.vo.MemberForm;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	/*
	 * remove member
	 * - 비밀번호 입력창
	 * - 입력받은 비밀번호와 세션값의 아이디로 일치하는행 삭제
	 * - 삭제 실행후 로그아웃
	 * - 인덱스로 이동
	 * update member
	 * - 비밀번호 입력창
	 * - 세션값의 아이디와 입력한 비밀번호로 일치하는 데이터 호출
	 * - 호출된데이터 있으면 그 행에 포함된 데이터 호출
	 * - 수정폼 출력
	 * - 수정완려
	 * */
	@PostMapping("/findMemberPw")
	public String findMemberPw(HttpSession session, Model model, Member member) {
		int row = memberService.getMemberPw(member);
		String msg = "아이디와 메일을 확인하세요";
		if(row == 1) {
			msg = "비밀번호를 입력한 메일로 전송하였습니다";
		}
		model.addAttribute("msg", msg);
		return "memberPwView";
	}
	@GetMapping("/findMemberPw")
	public String findMemberPw(HttpSession session) {
		if(session.getAttribute("loginMember") != null) {
			return "redirect:/";
		}
		return "findMemberPw";
	}
	
	@GetMapping("/findMemberId")
	public String findMemberId(HttpSession session) {
		if(session.getAttribute("loginMember") != null) {
			return "redirect:/";
		}
		return "findMemberId";
	}
	@PostMapping("/findMemberId")
	public String findMemberId(HttpSession session, Model model, Member member) {
		if(session.getAttribute("loginMember") != null) {
			return "redirect:/";
		}
		String memberIdPart = memberService.getMemberIdByMember(member);
		System.out.println(memberIdPart+"<--memberIdPart");
		model.addAttribute("memberIdPart", memberIdPart);
		return "memberIdView";
	}
	
	@PostMapping("/modifyMember")
	public String modifyMember(MemberForm memberForm, HttpSession session) {
		if(session.getAttribute("loginMember") ==null) {
			return "redirect:/";
		}
		System.out.println(memberForm+"<----memberForm");
		//memberService.modifyMember(memberForm);
		return "redirect:/memberInfo";
	}
	
	@GetMapping("/modifyMember")
	public String modifyMember(HttpSession session, Model model) {
		if(session.getAttribute("loginMember") ==null) {
			return "redirect:/";
		}
		Member member = memberService.getMemberOne((LoginMember)(session.getAttribute("loginMember")));
		model.addAttribute("member", member);
		return "modifyMember";
	}
	@PostMapping("/removeMember")
	public String removeMember(LoginMember loginMember, HttpSession session) {
		if(session.getAttribute("loginMember") ==null) {
			return "redirect:/";
		}
		System.out.println("remove : "+loginMember);
		memberService.removeMember(loginMember);
		session.invalidate();
		return "index";
	}
	@GetMapping("/removeMember")
	public String removeMember(HttpSession session) {
		if(session.getAttribute("loginMember") ==null) {
			return "redirect:/";
		}
		return "removeMember";
	}
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
	public String addMember(MemberForm memberForm, HttpSession session) {//입력폼에서 받아온 데이터(post) ----->데이터베이스로 이동
		if(session.getAttribute("loginMember") !=null) {
			return "redirect:/";
		}
		System.out.println("memberForm"+memberForm);
		//System.out.println(member);
		memberService.addMember(memberForm);
		/*
		 * memberService <---- memberForm
		 * service : member폴더에 파일 저장
		 * */
		return "redirect:/index";
	}
}

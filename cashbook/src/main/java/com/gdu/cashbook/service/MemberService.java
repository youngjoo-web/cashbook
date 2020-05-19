package com.gdu.cashbook.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gdu.cashbook.mapper.MemberMapper;
import com.gdu.cashbook.mapper.MemberidMapper;
import com.gdu.cashbook.vo.LoginMember;
import com.gdu.cashbook.vo.Member;
import com.gdu.cashbook.vo.MemberForm;
import com.gdu.cashbook.vo.Memberid;

@Service
@Transactional
public class MemberService {
	
	@Autowired private MemberMapper memberMapper;
	@Autowired private MemberidMapper memberidMapper;
	@Value("D:\\byjava\\byjWork\\cashbook\\cashbook\\src\\main\\resources\\static\\upload") 
	private String path;
	public int getMemberPw(Member member) { // id&email
		// pw추가
		UUID uuid = UUID.randomUUID(); // 랜덤문자열 생성 라이브러리(API)
		String memberPw = uuid.toString().substring(0, 8);
		member.setMemberPw(memberPw);
		int row = memberMapper.updateMemberPw(member);
		if(row == 1) {
			System.out.println(memberPw+"<--update memberPw");
			// 메일로 update성공한 랜덤 pw를 전송
			// 메일객체 new JavaMailSender();
		}
		return row;
	}
	
	public String getMemberIdByMember(Member member) {
		return memberMapper.selectMemberIdByMember(member);
	}
	
	public void removeMember(LoginMember loginMember) {
		// 1.
		Memberid memberid = new Memberid();
		memberid.setMemberId(loginMember.getMemberId());
		memberidMapper.insertMemberid(memberid);
		
		// 2. 
		Member member = memberMapper.selectMemberOne(loginMember);
		String memberPic = member.getMemberPic();
		File file = new File(path +"\\"+ memberPic);
		if(memberPic.equals("default.jpg") ) {
			memberMapper.deleteMember(loginMember);
		}else {
			memberMapper.deleteMember(loginMember);
			try {
				file.delete();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public void modifyMember(MemberForm memberForm) {
		/*
		 * 멤버수정service
		 * 1. memberForm 받아오기
		 * 2. mapper.selectMemberOne 으로 기존의 파일이름 불러오기
		 * 3. 기존의 파일이름이 default.jpg 가 아니면 파일삭제
		 * 4. memberForm의 값으로 데이터 수정하는 쿼리문작성
		 * 5. memberInfo페이지로 이동
		 * */
		System.out.println(memberForm+"memberService");
		String memberId = memberForm.getMemberId();
		LoginMember loginMember = new LoginMember();
		loginMember.setMemberId(memberId);
		Member member = memberMapper.selectMemberOne(loginMember);
		if(member.getMemberPic().equals("default.jpg")){
			System.out.println("기존사진없음");
		}else {
			File file = new File(path +"//"+member.getMemberPic());
			try {
				file.delete();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		File file = null;
		String memberPic = null;
		MultipartFile mf =null;
		try {
		mf = memberForm.getMemberPic();
		String originName = mf.getOriginalFilename();
		System.out.println(originName+"<----originName");
		int lastDot = originName.lastIndexOf(".");
		String extension = originName.substring(lastDot);
		memberPic = memberForm.getMemberId()+extension;
		member.setMemberPic(memberPic);
		
		file = new File(path+"\\"+memberPic);
		mf.transferTo(file);
		}catch(Exception e) {
		e.printStackTrace();
		member.setMemberPic("default.jpg");
		}
		member.setMemberAddr(memberForm.getMemberAddr());
		member.setMemberEmail(memberForm.getMemberEmail());
		member.setMemberId(memberForm.getMemberId());
		member.setMemberPhone(memberForm.getMemberPhone());
		member.setMemberPw(memberForm.getMemberPw());
		member.setMemberName(memberForm.getMemberName());
		memberMapper.updateMember(member);
	}
	public int addMember(MemberForm memberForm) {
		Member member = new Member();
		File file = null;
		String memberPic = null;
		MultipartFile mf =null;
		try {
		mf = memberForm.getMemberPic();
		String originName = mf.getOriginalFilename();
		System.out.println(originName+"<----originName");
		int lastDot = originName.lastIndexOf(".");
		String extension = originName.substring(lastDot);
		memberPic = memberForm.getMemberId()+extension;
		member.setMemberPic(memberPic);
		
		file = new File(path+"\\"+memberPic);
		mf.transferTo(file);
		}catch(Exception e) {
		e.printStackTrace();
		member.setMemberPic("default.jpg");
		}
		member.setMemberAddr(memberForm.getMemberAddr());
		member.setMemberEmail(memberForm.getMemberEmail());
		member.setMemberId(memberForm.getMemberId());
		member.setMemberPhone(memberForm.getMemberPhone());
		member.setMemberPw(memberForm.getMemberPw());
		member.setMemberName(memberForm.getMemberName());
		
		
		System.out.println(member+"<- memberSerivce.addmember:member");
		int row = memberMapper.insertMember(member);
		
		
			
			//Exception
			// 1. 예외처리를 해야만 문법적오류 없는 예외
			// 2. 예외처리 할필요 없는 예외 ex)RuntimeException
		//return memberMapper.insertMember(member);
		return row;
	}
	
	public Member getMemberOne(LoginMember loginMember) {
		return memberMapper.selectMemberOne(loginMember);
	}
	
	public String checkMemberId(String memberIdCheck) {
		return memberMapper.selectMemberId(memberIdCheck); // null, member_id
	}
	
	public LoginMember login(LoginMember loginMember) {
		return memberMapper.selectLoginMember(loginMember);
	}
}

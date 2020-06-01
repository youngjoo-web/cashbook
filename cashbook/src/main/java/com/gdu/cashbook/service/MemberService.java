package com.gdu.cashbook.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	@Value("C:\\Users\\gd\\git\\cashbook\\cashbook\\cashbook\\src\\main\\resources\\static\\upload") 
	private String path;
	public int modifyPw(String memberId, String memberPw1, String memberPw2) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("memberPw1", memberPw1);
		map.put("memberPw2", memberPw2);
		return memberMapper.updateMemberPw(map);
	}
	public int getMemberCount() {
		return memberMapper.selectMemberCount();
	}
	public List<Member> getMemberList(int currentPage,int rowPerPage){
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		return memberMapper.selectMemberList(map);
	}
	public int getMemberPw(Member member) { // id&email
		// pw추가
		UUID uuid = UUID.randomUUID(); // 랜덤문자열 생성 라이브러리(API)
		String memberPw = uuid.toString().substring(0, 8);
		member.setMemberPw(memberPw);
		int row = 1;
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
		// 1. memberid 테이블에 추가
		Memberid memberid = new Memberid();
		memberid.setMemberId(loginMember.getMemberId());
		memberidMapper.insertMemberid(memberid);
		
		// 2. memberPic 삭제
		Member member = memberMapper.selectMemberOne(loginMember);
		String memberPic = member.getMemberPic();
		File file = new File(path +"\\"+ memberPic);
		if(memberPic.equals("default.jpg") ) {	
		}else {	
			try {
				file.delete();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		memberMapper.deleteCashByMember(loginMember);
		memberMapper.deleteCommentByMember(loginMember);
		memberMapper.deleteMember(loginMember);
		
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
		String memberPw = memberForm.getMemberPw();
		LoginMember loginMember = new LoginMember();
		loginMember.setMemberId(memberId);
		loginMember.setMemberPw(memberPw);
		Member member = memberMapper.selectMemberOne(loginMember);
		
		File file = null;
		String memberPic = null;
		MultipartFile mf =null;
		
		mf = memberForm.getMemberPic();
		String originName = mf.getOriginalFilename();
		
		System.out.println(originName+"<----originName");
		if(originName.equals("")) {
			System.out.println("사진선택안함");
		}else {
			if(member.getMemberPic().equals("default.jpg")){
				System.out.println(member.getMemberPic()+"기존이미지");
			}else {
				File update = new File(path +"//"+member.getMemberPic());
				try {
					update.delete();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			int lastDot = originName.lastIndexOf(".");
			String extension = originName.substring(lastDot);
			System.out.println(extension+"확장자이름");
			memberPic = memberForm.getMemberId()+extension;
			System.out.println(memberPic+"저장될프로필사진이름");
			member.setMemberPic(memberPic);
			
			file = new File(path+"\\"+memberPic);
			try {
				mf.transferTo(file);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

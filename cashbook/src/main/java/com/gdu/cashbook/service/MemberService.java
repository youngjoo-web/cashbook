package com.gdu.cashbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.cashbook.mapper.MemberMapper;
import com.gdu.cashbook.vo.LoginMember;
import com.gdu.cashbook.vo.Member;

@Service
@Transactional
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;//private으로 mapper선언
	
	public Member getMemberOne(LoginMember loginMember) {
		return memberMapper.selectMemberOne(loginMember);
	}
	public String checkMemberId(String memberIdCheck) {//회원가입시 아이디중복체크
		return memberMapper.selectMemberId(memberIdCheck);
	}
	public LoginMember login(LoginMember loginMember) {//로그인시 아이디 비밀번호 일치여부확인
		return memberMapper.selectLoginMember(loginMember);
	}
	public int addMember(Member member) {
		return memberMapper.insertMember(member);//mapper호출후 데이터 삽입
	}
}

package com.gdu.cashbook.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.cashbook.vo.LoginMember;
import com.gdu.cashbook.vo.Member;

@Mapper
public interface MemberMapper {
	public Member selectMemberOne(LoginMember loginMember);
	public String selectMemberId(String memberIdCheck);
	public int insertMember(Member member);//xml파일 호출해서 데이터 삽입
	public LoginMember selectLoginMember(LoginMember loginMember);
}

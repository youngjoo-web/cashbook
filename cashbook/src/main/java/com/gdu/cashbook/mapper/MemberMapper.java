
package com.gdu.cashbook.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.cashbook.vo.LoginMember;
import com.gdu.cashbook.vo.Member;

@Mapper
public interface MemberMapper {

	public int updateMemberPw(Member member);

	public String selectMemberIdByMember(Member member);
	
	public int deleteMember(LoginMember loginMember);

	public Member selectMemberOne(LoginMember loginMember);

	public String selectMemberId(String memberIdCheck);
	
	public LoginMember selectLoginMember(LoginMember loginMember);

	public int insertMember(Member member);

}

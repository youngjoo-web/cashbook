
package com.gdu.cashbook.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.cashbook.vo.LoginMember;
import com.gdu.cashbook.vo.Member;

@Mapper
public interface MemberMapper {
	public List<Member> selectMemberList (Map<String, Object> map);
	/*비밀번호변경
	updateMemberPw
	- 받아오는값 : 기존비밀번호(memberPw1), 바뀔 비밀번호(memberPw2), 비밀번호바꿀회원 아이디(memberId)
	- 실행 : 기존비밀번호 암호화 - 회원아이디와 암호화한 비밀번호 일치하는 데이터 호출 - 바뀔 비밀번호를 암호화 - 기존비밀번호를 바뀔비밀번호로 변경
	*/
	public int updateMemberPw(Map<String, Object> map);
	/*회원정보 변경
	updateMember
	- 받아오는값 : memberClass
	- 실행 : 입력받은 멤버아이디와 암호화한 비밀번호가 일치하는 튜플 호출 - 입력한값들로 호출된 튜플의 데이터 변경
	*/
	public int updateMember(Member member);
	/*아이디 찾기
	selectMemberIdByMember
	- 받아오는값 : 회원이름, 전화번호, 이메일
	- 실행 : 받아온데이터 (이름, 전화번호, 이메일)과 일치하는 튜플 호출 - 호출된 튜플에 회원아이디 데이터를 첫번째글자부터3번째글자까지 출력 - 그뒤에'****'출력
	*/
	public String selectMemberIdByMember(Member member);
	/*회원탈퇴
	deleteMember
	- 받아오는값 : 회원아이디와 비밀번호
	- 실행 : 회원아이디와 비밀번호가 일치하는 튜플 삭제*/
	public int deleteMember(LoginMember loginMember);
	/*회원탈퇴
	deleteMemberBook
	- 받아오는값 : 회원아이디와 비밀번호
	- 실행 : 회원아이디와 비밀번호가 일치하는 튜플 삭제*/
	public int deleteMemberBook(LoginMember loginMember);
	
	/*회원탈퇴
	deleteMemberCash
	- 받아오는값 : 회원아이디와 비밀번호
	- 실행 : 회원아이디와 비밀번호가 일치하는 튜플 삭제*/
	public int deleteMemberCash(LoginMember loginMember);
	
	/*회원탈퇴
	deleteMemberComment
	- 받아오는값 : 회원아이디와 비밀번호
	- 실행 : 회원아이디와 비밀번호가 일치하는 튜플 삭제 */
	public int deleteMemberComment(LoginMember loginMember);
	
	/*회원정보불러오기
	- 받아오는값 : 회원아이디
	- 실행 : 받아온 회원아이디에 일치하는 튜플 출력*/
	public Member selectMemberOne(LoginMember loginMember);
	/*회원아이디 중복검사
	- 받아오는값 : 회원가입할 아이디
	- 실행 : 멤버테이블의 회원아이디 목록과 탈퇴한회원아이디목록을 결합 - 가입할 회원아이디와 일치하는 튜플 출력 - 출력된 데이터가 있을시 회원가입불가*/
	public String selectMemberId(String memberIdCheck);
	/*로그인
	- 받아오는값 : 로그인시도하는 회원아이디와 비밀번호
	- 실행 : 받아온 아이디와 비밀번호가 일치하는 튜플의 회원아이디 출력 - 출력된 데이터 있을시 로그인성공*/
	public LoginMember selectLoginMember(LoginMember loginMember);
	/*회원가입
	- 받아오는값 : memberClass
	- 실행 : 받아온값들을 member테이블에 추가*/
	public int insertMember(Member member);

}

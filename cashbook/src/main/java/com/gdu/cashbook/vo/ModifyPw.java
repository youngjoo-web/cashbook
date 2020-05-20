package com.gdu.cashbook.vo;

public class ModifyPw {
	private String memberPw1;
	private String memberPw2;
	@Override
	public String toString() {
		return "ModifyPw [memberPw1=" + memberPw1 + ", memberPw2=" + memberPw2 + "]";
	}
	public String getMemberPw1() {
		return memberPw1;
	}
	public void setMemberPw1(String memberPw1) {
		this.memberPw1 = memberPw1;
	}
	public String getMemberPw2() {
		return memberPw2;
	}
	public void setMemberPw2(String memberPw2) {
		this.memberPw2 = memberPw2;
	}
}

package com.gdu.cashbook.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.cashbook.vo.Member;

@Mapper
public interface MemberMapper {
	public int insertMember(Member member);//xml파일 호출해서 데이터 삽입
}

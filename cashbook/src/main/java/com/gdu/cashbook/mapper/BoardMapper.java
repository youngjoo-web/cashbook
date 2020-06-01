package com.gdu.cashbook.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.cashbook.vo.Board;
import com.gdu.cashbook.vo.LoginMember;

@Mapper
public interface BoardMapper {
	public int deleteCommentByBoard(Board board);
	
	public int deleteBoard(Board board);
	
	public int updateBoard(Board board);
	
	public List<Board> selectBoardList(Map<String, Object> map);
	
	public int selectBoardCount();
	
	public int insertBoard(Board board);
	
	public Board selectBoardOne(Board board);
}

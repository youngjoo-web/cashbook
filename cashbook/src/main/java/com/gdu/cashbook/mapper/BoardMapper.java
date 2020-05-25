package com.gdu.cashbook.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.cashbook.vo.Board;

@Mapper
public interface BoardMapper {
	public int selectBoardCount();
	public List<Board> selectBoardList(Map<String, Object> map);
	
	public Board selectBoard(Board board);
	
	public int deleteBoard(Board board);
	
	public int updateBoard(Board board);
	
	public int deleteCommentByBoard(Board board);
	
	public int insertBoard(Board board);
}

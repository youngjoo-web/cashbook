package com.gdu.cashbook.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.gdu.cashbook.mapper.BoardMapper;
import com.gdu.cashbook.vo.Board;

public class BoardService {
	@Autowired private BoardMapper boardMapper;
	public int getBoardCount() {
		return boardMapper.selectBoardCount();
	}
	public int addBoard(Board board) {
		return boardMapper.insertBoard(board);
	}
	public List<Board> getBoardList(int beginRow, int rowPerPage){
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		List<Board> list = boardMapper.selectBoardList(map); 
		return list;
	}
	public Board getBoard(int boardNo) {
		Board board = new Board();
		board.setBoardNo(boardNo);
		return boardMapper.selectBoard(board);
	}
	public int modifyBoard(Board board) {
		return boardMapper.updateBoard(board);
	}
	public void remobeBoard(Board board) {
		boardMapper.deleteCommentByBoard(board);
		boardMapper.deleteBoard(board);
	}
}

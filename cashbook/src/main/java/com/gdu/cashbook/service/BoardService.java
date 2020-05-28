package com.gdu.cashbook.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.cashbook.mapper.BoardMapper;
import com.gdu.cashbook.mapper.MemberMapper;
import com.gdu.cashbook.vo.Board;
import com.gdu.cashbook.vo.LoginMember;
import com.gdu.cashbook.vo.Member;

@Service
@Transactional
public class BoardService {
	@Autowired private MemberMapper memberMapper;
	@Autowired private BoardMapper boardMapper;
	
	public List<Board> getBoardList(int currentPage, int rowPerPage){
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		return boardMapper.selectBoardList(map);
	}
	public int getBoardCount() {
		return boardMapper.selectBoardCount();
	}
	public int modifyBoard(Board board) {
		return boardMapper.updateBoard(board);
	}
	public int addBoard(Board board) {
		LoginMember loginMember = new LoginMember();
		loginMember.setMemberId(board.getMemberId());
		Member member = memberMapper.selectMemberOne(loginMember);
		board.setMemberName(member.getMemberName());
		System.out.println(board);
		return boardMapper.insertBoard(board);
	}
	public void removeBoard(int boardNo) {
		Board board = new Board();
		board.setBoardNo(boardNo);
		boardMapper.deleteCommentByBoard(board);
		boardMapper.deleteBoard(board);
	}
	public Board getBoardOne(int boardNo) {
		Board board = new Board();
		board.setBoardNo(boardNo);
		
		return boardMapper.selectBoardOne(board);
	}
}

package com.gdu.cashbook.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.cashbook.mapper.CommentMapper;
import com.gdu.cashbook.mapper.MemberMapper;
import com.gdu.cashbook.vo.Comment;
import com.gdu.cashbook.vo.LoginMember;
import com.gdu.cashbook.vo.Member;


@Service
@Transactional
public class CommentService {
	
	@Autowired private MemberMapper memberMapper;
	@Autowired private CommentMapper commentMapper;
	
	public List<Comment> getCommentList(int boardNo, int currentPage, int rowPerPage){
		int beginRow = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("boardNo", boardNo);
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		return commentMapper.selectCommentList(map);
	}
	public int addComment(Comment comment) {
		LoginMember loginMember = new LoginMember();
		loginMember.setMemberId(comment.getMemberId());
		Member member = memberMapper.selectMemberOne(loginMember);
		comment.setMemberName(member.getMemberName());
		return commentMapper.insertComment(comment);
	}
	public int removeComment(int commentNo) {
		return commentMapper.deleteComment(commentNo);
	}
	public int modifyComment(Comment comment) {
		return commentMapper.updateComment(comment);
	}
	public Comment getComment(int commentNo) {
		return commentMapper.selectComment(commentNo);
	}
}

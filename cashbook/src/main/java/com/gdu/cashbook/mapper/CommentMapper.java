package com.gdu.cashbook.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.cashbook.vo.Comment;
import com.gdu.cashbook.vo.LoginMember;

@Mapper
public interface CommentMapper {
	//map(boardNo, beginRow, rowPerPage)
	public List<Comment> selectCommentList(Map<String, Object> map);
	
	public int insertComment(Comment comment);
	
	public int updateComment(Comment comment);
	
	public int deleteComment(int commentNo);
	
	
	
	public Comment selectComment(int commentNo);
}

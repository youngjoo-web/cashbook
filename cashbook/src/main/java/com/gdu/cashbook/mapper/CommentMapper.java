package com.gdu.cashbook.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.cashbook.vo.Board;
import com.gdu.cashbook.vo.Comment;

@Mapper
public interface CommentMapper {
	public int selectCommentCount(Board board);
	
	public List<Comment> selectCommentList(Map<String, Object> map);
	
	public int deleteComment(Comment comment);
	
	public int insertComment(Comment comment);
	
	public int updateComment(Comment comment);
}

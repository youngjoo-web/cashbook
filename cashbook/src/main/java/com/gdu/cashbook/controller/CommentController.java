package com.gdu.cashbook.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.cashbook.service.BoardService;
import com.gdu.cashbook.service.CommentService;
import com.gdu.cashbook.vo.Board;
import com.gdu.cashbook.vo.Comment;
import com.gdu.cashbook.vo.CommentForm;
import com.gdu.cashbook.vo.LoginMember;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private BoardService boardService;
	@GetMapping("/addComment")
	public String addComment(HttpSession session, Comment comment,
			Model model,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1")int currentPage) {
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/";
		}
		System.out.println(comment);
		Board board = boardService.getBoardOne(comment.getBoardNo());
		System.out.println(board);
		model.addAttribute("board", board);
		int rowPerPage=10;
		
		
		
		//addComment
		LoginMember loginMember = (LoginMember)session.getAttribute("loginMember");
		comment.setMemberId(loginMember.getMemberId());
		System.out.println(comment);
		commentService.addComment(comment);
		//commentList
				List<Comment>commentList = commentService.getCommentList(comment.getBoardNo(), currentPage, rowPerPage);
				model.addAttribute("commentList", commentList);
		return "boardOneList";
	}
	@GetMapping("/removeComment")
	public String removeComment(HttpSession session,
			Model model,
			@RequestParam(value = "commentNo", required = false, defaultValue = "0")int commentNo) {
		Comment comment = commentService.getComment(commentNo);
		Board board = boardService.getBoardOne(comment.getBoardNo());
		commentService.removeComment(commentNo);
		model.addAttribute("board", board);
		int rowPerPage=10;
		
		//commentList
		List<Comment>commentList = commentService.getCommentList(board.getBoardNo(), 1, rowPerPage);
		model.addAttribute("commentList", commentList);
		
		
		return "boardOneList";
	}
	@PostMapping("/modifyComment")
	public String modifyComment(HttpSession session,
			Model model,
			Comment comment) {
		
		Board board = boardService.getBoardOne(comment.getBoardNo());
		commentService.modifyComment(comment);
		model.addAttribute("board", board);
		int rowPerPage=10;
		
		//commentList
		List<Comment>commentList = commentService.getCommentList(board.getBoardNo(), 1, rowPerPage);
		model.addAttribute("commentList", commentList);
		return "boardOneList";
	}
	@GetMapping("/modifyComment")
	public String modifyComment(HttpSession session,
			Model model,
			@RequestParam(value = "commentNo", required = false, defaultValue = "0")int commentNo) {
		Comment comment = commentService.getComment(commentNo);
		Board board = boardService.getBoardOne(comment.getBoardNo());
		model.addAttribute("board", board);
		
		//commentList
		int rowPerPage=10;
		List<Comment>commentList = commentService.getCommentList(board.getBoardNo(), 1, rowPerPage);
		model.addAttribute("commentList", commentList);
		
		//commentNo for modify
		model.addAttribute("comment", comment);
		
		return "modifyComment";
	}
}

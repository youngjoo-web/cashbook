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
import com.gdu.cashbook.service.CashService;
import com.gdu.cashbook.service.CommentService;
import com.gdu.cashbook.vo.Board;
import com.gdu.cashbook.vo.Comment;
import com.gdu.cashbook.vo.LoginMember;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private CommentService commentService;
	@GetMapping("/modifyBoard")
	public String modifyBoard(HttpSession session,
			Model model,
			@RequestParam(value = "boardNo", required = false, defaultValue = "0") int boardNo) {
		
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/";
		}
		LoginMember loginMember = (LoginMember)session.getAttribute("loginMember");
		if(loginMember.getMemberLevel()!=0) {
			return "redirect:/";
		}
		loginMember = (LoginMember)session.getAttribute("loginMember");
		if(loginMember.getMemberLevel()!=0) {
			return "redirect:/";
		}
		Board board = boardService.getBoardOne(boardNo);
		System.out.println(board);
		model.addAttribute("board", board);
		
		
		
		return "modifyBoard";
	}
	@PostMapping("/modifyBoard")
	public String modifyBoard(HttpSession session, Model model, Board board) {
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/";
		}
		System.out.println(board);
		boardService.modifyBoard(board);
		model.addAttribute("board", board);
		int rowPerPage=10;
		
		//commentList
		List<Comment>commentList = commentService.getCommentList(board.getBoardNo(), 1, rowPerPage);
		model.addAttribute("commentList", commentList);
		
		return "boardOneList";
	}
	@GetMapping("/addBoard")
	public String addBoard(HttpSession session) {
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/";
		}
		LoginMember loginMember = (LoginMember)session.getAttribute("loginMember");
		if(loginMember.getMemberLevel()!=0) {
			return "redirect:/";
		}
		return "addBoard";
		
	}
	@PostMapping("/addBoard")
	public String addBoard(HttpSession session, Board board) {
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/";
		}
		LoginMember loginMember = (LoginMember)session.getAttribute("loginMember");
		if(loginMember.getMemberLevel()!=0) {
			return "redirect:/";
		}
		
		board.setMemberId(loginMember.getMemberId());
		boardService.addBoard(board);
		System.out.println(board);
		return "redirect:/getBoardList";
	}
	@GetMapping("/getBoardList")
	public String getBoardList(HttpSession session,
			Model model,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/";
		}
		
		int rowPerPage = 10;
		
		
		
		int totalCount = boardService.getBoardCount();
		int lastPage=totalCount/rowPerPage;
		if(totalCount%rowPerPage!=0) {
			lastPage += 1;
		}
		if(totalCount == 0) {
			lastPage = 1;
		}
		
		List<Board>boardList = boardService.getBoardList(currentPage, rowPerPage);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("lastPage",lastPage);
		return "boardList";
		
	}
	@GetMapping("/removeBoard")
	public String removeBoard(HttpSession session,@RequestParam(value = "boardNo", required = false, defaultValue = "0")int boardNo) {
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/";
		}
		LoginMember loginMember = (LoginMember)session.getAttribute("loginMember");
		if(loginMember.getMemberLevel()!=0) {
			return "redirect:/";
		}
		boardService.removeBoard(boardNo);
		return "redirect:/getBoardList";
	}
	@GetMapping("/getBoardOne")
	public String getBoardOne(HttpSession session,Model model,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1")int currentPage, 
			int boardNo) {
		System.out.println(currentPage);
		System.out.println(boardNo);
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/";
		}
		//boardOne
		Board board = boardService.getBoardOne(boardNo);
		System.out.println(board);
		model.addAttribute("board", board);
		int rowPerPage=10;
		
		//commentList
		List<Comment>commentList = commentService.getCommentList(boardNo, currentPage, rowPerPage);
		model.addAttribute("commentList", commentList);
		model.addAttribute("currentPage", currentPage);
		//lastPage
		return "boardOneList";
		//return "redirect:/getBoardList";
	}
}

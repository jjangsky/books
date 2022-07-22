package com.bukkeubook.book.secretary.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bukkeubook.book.secretary.model.dto.join.BoardAndEmpAndBoardCateDTO;
import com.bukkeubook.book.secretary.model.service.BoardService;

@Controller
@RequestMapping("/secretary")
public class BoardController {
	
	private final BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	/* 총무부 전사게시판 전체 조회 */
	@GetMapping("/board")
	public ModelAndView boardManageList(ModelAndView mv) {
		
		
		List<BoardAndEmpAndBoardCateDTO> boardList = boardService.findBoardList();
		System.out.println(boardList);
		
		mv.addObject("boardList", boardList);
		mv.setViewName("/secretary/board");
		return mv;
	}
	
	/* 총무부 전사게시판 상세 조회 */
	@GetMapping("/boardDetail")
	public ModelAndView secBoardDetail(HttpServletRequest request, String no, ModelAndView mv) {
		
		int boardNo = Integer.valueOf(request.getParameter("no"));
		BoardAndEmpAndBoardCateDTO board = boardService.findBoardDetail(boardNo);
		
		System.out.println(board);
		mv.addObject("board", board);
		mv.setViewName("/secretary/boardDetail");
		return mv;
		
	}
	
	/* 총무부 전사게시판 수정 화면 이동 */
	@GetMapping("/boardUpdate")
	public ModelAndView moveUpdateBoard(ModelAndView mv, HttpServletRequest request, String no) {
		
		int boardNo = Integer.parseInt(request.getParameter("no"));
		BoardAndEmpAndBoardCateDTO board = boardService.findBoardDetail(boardNo);
		mv.addObject("board", board);
		mv.setViewName("/secretary/boardUpdate");
		
		return mv;
	}
	/* 총무부 전사게시판 수정 */
	@PostMapping("modifyBoard")
	public ModelAndView modifyBoardContent(ModelAndView mv, RedirectAttributes rttr, BoardAndEmpAndBoardCateDTO board ) {
		
		System.out.println(board);
		
		boardService.modifyBoardContent(board);
		
		rttr.addFlashAttribute("successMessage", "정상적으로 처리되었습니다.");
		mv.setViewName("redirect:/secretary/board");
		
		
		return mv;
	}
}

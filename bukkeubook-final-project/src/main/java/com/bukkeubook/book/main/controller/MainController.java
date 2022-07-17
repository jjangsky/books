package com.bukkeubook.book.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bukkeubook.book.main.model.service.MainService;
import com.bukkeubook.book.secretary.model.dto.join.BoardAndCateDTO;

@Controller
public class MainController {
	
	private final MainService mainService;
	
	@Autowired
	public MainController(MainService mainService) {
		this.mainService = mainService;
	}
	
	/* 최근 전사게시판 우선순위 5개 조회 */
	@GetMapping("/")
	public ModelAndView main(ModelAndView mv) {
		
		List<BoardAndCateDTO> boardList = mainService.findBoardList();
		System.out.println(boardList);
		
		mv.addObject("boardList", boardList);
		mv.setViewName("/main");
		
		
		return mv;
	}
}

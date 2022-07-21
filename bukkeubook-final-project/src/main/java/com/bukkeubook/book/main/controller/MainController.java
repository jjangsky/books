package com.bukkeubook.book.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bukkeubook.book.main.model.service.MainService;
import com.bukkeubook.book.mypage.model.dto.CalendarDTO;
import com.bukkeubook.book.mypage.model.service.MypageService;
import com.bukkeubook.book.secretary.model.dto.join.BoardAndCateDTO;

@Controller
public class MainController {
	
	private final MainService mainService;
	private final MypageService mypageService;
	
	@Autowired
	public MainController(MainService mainService, MypageService mypageService) {
		this.mainService = mainService;
		this.mypageService = mypageService;
	}
	

	@PostMapping("/main")
	public ModelAndView main(ModelAndView mv) {
		int memberCode = 5;
		
		/* 최근 전사게시판 우선순위 5개 조회 */
		List<BoardAndCateDTO> boardList = mainService.findBoardList();
		System.out.println(boardList);
		
		/* 주간 캘린더 일정 전체 조회 (마이페이지 Service 재활용)*/
		List<CalendarDTO> calendar = mypageService.findMyCalendar(memberCode);
		System.out.println(calendar);
		
		
		mv.addObject("boardList", boardList);
		mv.addObject("calendar", calendar);
		mv.setViewName("/main");
		return mv;
	}
	
	@GetMapping("/")
	public String login() {
		return "/member/login";
	};
	
	@GetMapping("/main")
	public ModelAndView main2(ModelAndView mv) {
		int memberCode = 5;
		
		/* 최근 전사게시판 우선순위 5개 조회 */
		List<BoardAndCateDTO> boardList = mainService.findBoardList();
		System.out.println(boardList);
		
		/* 주간 캘린더 일정 전체 조회 (마이페이지 Service 재활용)*/
		List<CalendarDTO> calendar = mypageService.findMyCalendar(memberCode);
		System.out.println(calendar);
		
		
		mv.addObject("boardList", boardList);
		mv.addObject("calendar", calendar);
		mv.setViewName("/main");
		return mv;
	}
}

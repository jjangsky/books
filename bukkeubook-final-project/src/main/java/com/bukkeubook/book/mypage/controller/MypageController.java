package com.bukkeubook.book.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bukkeubook.book.manage.model.dto.AppVacationDTO;
import com.bukkeubook.book.mypage.model.service.MypageService;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	private final MypageService mypageService;
	
	@Autowired
	public MypageController( MypageService mypageService) {
		this. mypageService = mypageService;
	}
	
	@GetMapping("/calendar")
	public void Calendar() {}
	
	@GetMapping("/myAnnual")
	public ModelAndView findMyAnnualList(ModelAndView mv) {
		
		List<AppVacationDTO> vacationList =  mypageService.findMyAnnualList();
		
		mv.addObject("vacationList", vacationList);
		mv.setViewName("/myAnnual");
		
		return mv;
	}
	

	

}

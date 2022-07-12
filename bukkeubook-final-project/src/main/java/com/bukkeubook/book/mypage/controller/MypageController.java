package com.bukkeubook.book.mypage.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bukkeubook.book.manage.model.dto.AppVacationDTO;
import com.bukkeubook.book.manage.model.dto.DayOffDTO;
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
		
		int memberCode = 5;
		String approve = "승인";
		
		List<AppVacationDTO> vacationList =  mypageService.findMyAnnualList(memberCode, approve);
		System.out.println(vacationList);
		
		List<DayOffDTO> dayoffList = mypageService.findMyDayOffList(memberCode);
		System.out.println(dayoffList);
		
		mv.addObject("dayoffList", dayoffList);
		mv.addObject("vacationList", vacationList);
		mv.setViewName("mypage/myAnnual");
		
		return mv;
	}
	

	

}

package com.bukkeubook.book.mypage.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bukkeubook.book.manage.model.dto.AppVacationDTO;
import com.bukkeubook.book.manage.model.dto.DayOffDTO;
import com.bukkeubook.book.mypage.model.dto.CalendarDTO;
import com.bukkeubook.book.mypage.model.service.MypageService;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	private final MypageService mypageService;
	
	@Autowired
	public MypageController( MypageService mypageService) {
		this. mypageService = mypageService;
	}
	
	
	/* 캘린더 전체 조회 */
	@GetMapping("/calendar")
	public ModelAndView findMyCalendar(ModelAndView mv) {
		
		int memberCode = 5;
		List<CalendarDTO> calendar = mypageService.findMyCalendar(memberCode);
		System.out.println(calendar);
		
		mv.addObject("calendar", calendar);
		mv.setViewName("mypage/calendar");
		
		return mv;
	}
	
	/* 캘린더 등록 페이지 이동 */
	@GetMapping("/registCalendar")
	public void registCalendar() {}
	
	/* 캘린더 일정 등록 */
	@PostMapping("/insertCal")
	public ModelAndView insertMyCalendar(ModelAndView mv, CalendarDTO newCalendar, RedirectAttributes rttr, Locale locale) {
		
		int memberInfo = 5;
		newCalendar.setEmpNo(memberInfo);
		System.out.println(newCalendar);
		
		
		mypageService.registNewCalendar(newCalendar);
		
		rttr.addFlashAttribute("registSuccessMessage", "일정을 성공적으로 등록하셨습니다.");
		mv.setViewName("redirect:/mypage/calendar");
		
		return mv;
	}
	
	
	/* 마이페이지 연차 조회 */
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

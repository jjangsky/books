package com.bukkeubook.book.mypage.controller;


import java.text.SimpleDateFormat;
import java.util .Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bukkeubook.book.manage.model.dto.AttendDTO;
import com.bukkeubook.book.mypage.model.service.AttendService;

@Controller
@RequestMapping("/attend")
public class AttendController {
	
	private final AttendService attendService;
	
	@Autowired
	public AttendController(AttendService attendService) {
		this.attendService = attendService;
	}
	
	/* 근태 조회 페이지 이동 */
	@GetMapping("/findPage")
	public ModelAndView findMyAttend(ModelAndView mv) {
		
		int memberCode = 5;
		List<AttendDTO> attend = attendService.findMyAttend(memberCode);
		System.out.println(attend);
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String format = sdf.format(date);
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡcurrent Time : "+format);
		
		mv.addObject("attend", attend);
		mv.addObject("currentDate", format);
		mv.setViewName("mypage/myAttend");
		
		return mv;
	}
	
	/* 출근 등록 버튼 */
	@PostMapping("/checkIn")
	public ModelAndView registCheckIn(ModelAndView mv, RedirectAttributes rttr, Locale locale){
		
		
		Date today = new Date();
		long time = today.getTime();
		java.sql.Date startDate = new java.sql.Date(time);
		

		int memberInfo = 5;
		
		AttendDTO attend = new AttendDTO();
		
		attend.setAttDate(startDate);
		attend.setAttStart(startDate);
		attend.setEmpNo(memberInfo);
		

		 attendService.registCheckIn(attend);
		 
		 rttr.addFlashAttribute("successMessage", "출근 등록이 정상적으로 처리 되었습니다.");
		 mv.setViewName("redirect:/");
		
		return mv;
	}
	
	/* 퇴근 등록 버튼 */
	@PostMapping("/checkOut")
	public ModelAndView updateCheckOut(ModelAndView mv, RedirectAttributes rttr, Locale locale) {
		
		AttendDTO attend = new AttendDTO();
		
		Date today = new Date();
		long time = today.getTime();
		java.sql.Date startDate = new java.sql.Date(time);
		
		attend.setAttDate(startDate);
		
		System.out.println(attend);
		
		attendService.modifyCheckOut(attend);
		
		rttr.addFlashAttribute("successMessage", "퇴근 등록이 정상적으로 처리 되었습니다.");
		mv.setViewName("redirect:/");
		
		return mv;
		
	}
	
}

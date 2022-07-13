package com.bukkeubook.book.mypage.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/findPage")
	public ModelAndView findMyAttend(ModelAndView mv) {
		
		int memberCode = 5;
		List<AttendDTO> attend = attendService.findMyAttend(memberCode);
		System.out.println(attend);
		
		mv.addObject("attend", attend);
		mv.setViewName("mypage/myAttend");
		
		return mv;
	}
	
	@PostMapping("/checkIn")
	public ModelAndView registCheckIn(ModelAndView mv, RedirectAttributes rttr, Locale locale, String startDate, String startStart){
		
		System.out.println("Controller                           " +startDate);			
		
		
		java.sql.Date startDay = java.sql.Date.valueOf(startDate);
		java.sql.Date startTime = java.sql.Date.valueOf(startStart);
		
		System.out.println("type :    " + startDate);
		int memberInfo = 5;
		
		AttendDTO attend = new AttendDTO();
		
		attend.setAttDate(startDay);
		attend.setAttStart(startTime);
		attend.setEmpNo(memberInfo);
		
//		System.out.println("class확인해보자:" + (attend.getAttDate()).getClass());
	
		

		 attendService.registCheckIn(attend);
		 
		 rttr.addFlashAttribute("registSuccessMessage", "일정을 성공적으로 등록하셨습니다.");
		 mv.setViewName("redirect:/mypage/calendar");
		
		

		
		return mv;
	}
	
}

package com.bukkeubook.book.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bukkeubook.book.manage.model.dto.joinDTO.AppVacationAndEmpDTO;
import com.bukkeubook.book.manage.model.service.EmpAnnualService;

@Controller
@RequestMapping("/empAnnual")
public class EmpAnnualController {
	
	/* 다른 객체 바꿔치기를 방지 및 서비스 연결 */
	private final EmpAnnualService empAnnualService;
	
	/* 의존성 자동 주입 */
	@Autowired
	public EmpAnnualController(EmpAnnualService empAnnualService) {
		this.empAnnualService = empAnnualService;
	}
	
	@GetMapping("empAnnualList")
	public String  main() { 
		return "manage/empAnnual/empAnnualList";
	}
	
	@GetMapping("empAttendanceList")
	public String  main1() {
		return "manage/empAnnual/empAttendanceList";
	}
	
	/* 휴가 신청 조회 */
	@GetMapping("/restSelect")
	public ModelAndView findRestList(ModelAndView mv) {
		System.out.println("여긴오니");
		List<AppVacationAndEmpDTO> restList = empAnnualService.findRestList();
		
		mv.addObject("restList", restList);
		mv.setViewName("manage/empAnnual/restSelect");
		
		return mv;
	}

}



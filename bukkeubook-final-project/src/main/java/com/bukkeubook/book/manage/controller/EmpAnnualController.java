package com.bukkeubook.book.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bukkeubook.book.manage.model.dto.joinDTO.AppVacationAndEmpDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.CancleVacAndAppVacDTO;
import com.bukkeubook.book.manage.model.service.EmpAnnualService;

@Controller
@RequestMapping("/empAnnual")
public class EmpAnnualController {
	
	private final EmpAnnualService empAnnualService;
	
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
//		System.out.println("여긴오니");
		
		List<AppVacationAndEmpDTO> restList = empAnnualService.findRestList();
		
		mv.addObject("restList", restList);
		mv.setViewName("manage/empAnnual/restSelect");
		
		return mv;
	}
	
	/* 휴가 신청 상세 내역 조회 */
	@GetMapping("/restDetailSelect")
	public ModelAndView restDetail (HttpServletRequest request, ModelAndView mv) {
		
		int vacNo = Integer.valueOf(request.getParameter("vacNo"));
		
		AppVacationAndEmpDTO appvacAndEmp = empAnnualService.restDetailSelect(vacNo);
		
		mv.addObject("appvacAndEmp", appvacAndEmp);
		mv.setViewName("manage/empAnnual/restDetailSelect");
		
		return mv;
	}
	
	/* 휴가 취소 신청 조회 */
	@GetMapping("/cancleVacSelect")
	public ModelAndView findCancleVacList(ModelAndView mv) {
		System.out.println("여긴 오니?");
		
		List<CancleVacAndAppVacDTO> cancleRestList = empAnnualService.findCancleRestList();
		
		mv.addObject("cancleRestList", cancleRestList);
		mv.setViewName("manage/empAnnual/cancleVacSelect");
		
		return mv;
	}

}



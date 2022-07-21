package com.bukkeubook.book.manage.controller;

import java.sql.Date;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bukkeubook.book.manage.model.dto.AttendDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.AttendAndEmpDTO;
import com.bukkeubook.book.manage.model.service.AttendManageService;


@Controller
@RequestMapping("check")
public class AttendManageController {
	
	private final AttendManageService attendManageService;
	
	@Autowired
	public AttendManageController(AttendManageService attendManageService) {
		this.attendManageService = attendManageService;
	}
	
	@GetMapping("/findCheck")
	public ModelAndView findAttendListManage(HttpServletRequest request, ModelAndView mv, Date attStart, Date attEnd) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;
		
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
//		java.sql.Date startDate = java.sql.Date.valueOf(request.getParameter("attStart"));
//		java.sql.Date endDate = java.sql.Date.valueOf(request.getParameter("attEnd"));
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		int totalCount = attendManageService.selectTotalCount(searchCondition, searchValue, attStart, attEnd);
		
		
		
		
		
		List<AttendAndEmpDTO> attendList = attendManageService.findAllAttendList();
		System.out.println(attendList);

		System.out.println(totalCount);
		System.out.println("검색 조건" + searchCondition);
		System.out.println("검색 값" + searchValue);
		System.out.println("시작 날짜" + attStart);
		System.out.println("종료 날짜" + attEnd);
		
		mv.addObject("attendList", attendList);
		mv.setViewName("/manage/empAnnual/empAttendanceList");
		return mv;
	}
	
	


}

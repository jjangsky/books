package com.bukkeubook.book.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bukkeubook.book.manage.model.dto.DeptDTO;
import com.bukkeubook.book.manage.model.service.DeptService;

@Controller
@RequestMapping("/dept")
public class DeptController {
	
	private final DeptService deptService;
	
	@Autowired
	public DeptController(DeptService deptService) {
		this.deptService = deptService;
	}
	
	@GetMapping("/select")
	public ModelAndView selectDept(HttpServletRequest request, ModelAndView mv) {
		
		List<DeptDTO> deptList = deptService.selectDeptList();
		
		mv.addObject("deptList", deptList);
		mv.setViewName("books/manage/dept/departmentSelect");
		
		return mv;
	}
}

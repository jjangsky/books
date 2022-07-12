package com.bukkeubook.book.manage.controller;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bukkeubook.book.manage.model.dto.joinDTO.EmpAndDeptDTO;
import com.bukkeubook.book.manage.model.entity.EmpAndDept;
import com.bukkeubook.book.manage.model.service.EmpService;


@Controller
@RequestMapping("manage/") 
public class EmployeeController {

private final EmpService empService;
	
	@Autowired
	public EmployeeController(EmpService empService) {
		this.empService = empService;
	}
	
	/* 사원조회 */
	@GetMapping("empList")
	public ModelAndView findEmpList(ModelAndView mv) {  //ModelAndView 뷰 리졸버의 역할 _리턴할 페이지 설정 , 보내는객체

		List<EmpAndDeptDTO> empList = empService.findEmpList();
		System.out.println("empList" + empList);
		mv.addObject("empList", empList);  //보내는 객체 설정
		mv.setViewName("manage/employee/empList"); //리턴할 페이지 설정
		
		return mv;
	}
	
	/* 퇴사 사원 조회 (퇴사여부 Y) */
	@GetMapping("leaveEmpList")
	public ModelAndView findLeaveEmpList(ModelAndView mv) {  

		String empEndYn = "Y";
		List<EmpAndDeptDTO> leaveEmpList = empService.findLeaveEmpList(empEndYn);
		System.out.println("leaveEmpList" + leaveEmpList);
		mv.addObject("leaveEmpList", leaveEmpList);  //보내는 객체 설정
		mv.setViewName("manage/employee/leaveEmpList"); //리턴할 페이지 설정
		
		return mv;
	}
	
	@GetMapping("personnelSelect")
	public String perconnelList() {
		return "manage/employee/personnelSelect";
	}
	
	
}
 

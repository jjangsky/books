package com.bukkeubook.book.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("manage/*")
public class EmployeeController {

	@GetMapping("empList")
	public String  main() {
		return "manage/employee/empList";
	}
	
	@GetMapping("leaveEmpList")
	public String  main1() {
		return "manage/employee/leaveEmpList";
	}
	
	@GetMapping("personnelSelect")
	public String perconnelList() {
		return "manage/employee/personnelSelect";
	}
	
	
	
	
	
	
}
 
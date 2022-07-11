package com.bukkeubook.book.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmpAnnualController {
	
	@GetMapping("empAnnualList")
	public String  main() {
		return "manage/empAnnual/empAnnualList";
	}
	
	@GetMapping("empAttendanceList")
	public String  main1() {
		return "manage/empAnnual/empAttendanceList";
	}
	
	@GetMapping("restSelect")
	public String restSelect() {
		return "manage/empAnnual/restSelect";
	}
	
	@GetMapping("restCancleSelect")
	public String restCancleSelect() {
		return "manage/empAnnual/restCancleSelect";
	}

}



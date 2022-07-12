package com.bukkeubook.book.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmpPayController {
	
	@GetMapping("empPayList")
	public String  main() {
		return "manage/empPay/empPayList";
	}

}

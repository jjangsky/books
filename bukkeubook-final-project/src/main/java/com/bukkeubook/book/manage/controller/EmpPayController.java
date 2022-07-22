package com.bukkeubook.book.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmpPayController {
	
	/* 급여 조회 */
	@GetMapping("empPayList")
	public String main() {
		return "manage/empPay/empPayList";
	}
	
	/* 퇴직금 조회 */
	@GetMapping("sevPaySelect")
	public String sevPayList() {
		return "manage/empPay/sevPaySelect";
	}
	
	/* 퇴직금 상세페이지 */
	@GetMapping("severancePayDetail")
	public String severancePayDetail() {
		return "";
	}
}

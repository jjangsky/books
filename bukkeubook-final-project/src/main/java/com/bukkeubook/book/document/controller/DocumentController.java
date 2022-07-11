package com.bukkeubook.book.document.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("document/*")
public class DocumentController {

	@GetMapping("insert")
	public String toDocWrite() {
		return "/document/insert";
	}
	
	@GetMapping("docList")
	public String toDocList() {
		return "/document/docList";
	}
	
	@GetMapping("docTempList")
	public String totempDocList() {
		return "/document/docTempList";
	}
}

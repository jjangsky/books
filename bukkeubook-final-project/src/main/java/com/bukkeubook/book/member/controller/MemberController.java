package com.bukkeubook.book.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {
	
	@GetMapping("/common/denied")
	public ModelAndView denied(ModelAndView mv, RedirectAttributes rttr) {
		
		rttr.addFlashAttribute("accessDeniedMessage", "성공");
		mv.setViewName("/common/denied");
		return mv;
	}
	
	@GetMapping("/member/loginFail")
	public void loginFail(HttpServletRequest request, ModelAndView mv, RedirectAttributes rttr) {
		String message = request.getParameter("exception");
		System.out.println(message);
		rttr.addFlashAttribute("loginFailMessage", message);
		mv.setViewName("/member/loginFail");
	}
}

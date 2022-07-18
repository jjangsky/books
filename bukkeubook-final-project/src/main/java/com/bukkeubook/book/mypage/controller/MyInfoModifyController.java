package com.bukkeubook.book.mypage.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bukkeubook.book.manage.model.dto.EmpDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.EmpAndDeptDTO;
import com.bukkeubook.book.mypage.model.service.MyInfoModifyService;


@Controller
@RequestMapping("/myInfo")
public class MyInfoModifyController {
	
	private final MyInfoModifyService myInfoModifyService;
	
	@Autowired
	public MyInfoModifyController(MyInfoModifyService myInfoModifyService) {
		this.myInfoModifyService = myInfoModifyService; 
	}
	
	
	/* 마이페이지 개인 정보 수정 화면이동 */
	@GetMapping("/updatePage")
	public ModelAndView findMyInfo(ModelAndView mv) {
		
		int memberCode = 5;
		EmpAndDeptDTO myInfo = myInfoModifyService.findMyInfo(memberCode);
		System.out.println(myInfo);
		
		mv.addObject("myInfo", myInfo);
		mv.setViewName("mypage/mypageInfoModify");
		
		return mv;
	}
	
	/* 마이페이지 개인 정보 수정하기 */
	@PostMapping("/modifyInfo")
	public ModelAndView modifyMyInfo(ModelAndView mv, EmpDTO emp, RedirectAttributes rttr, Locale locale) {
		
		int memberCode = 5;
		System.out.println(emp);
		myInfoModifyService.modifyInfoEmp(memberCode, emp);

		rttr.addFlashAttribute("successMessage", "회원정보를 정상적으로 수정하였습니다.");
		mv.setViewName("redirect:/");
		
		return mv;
	}
	

}

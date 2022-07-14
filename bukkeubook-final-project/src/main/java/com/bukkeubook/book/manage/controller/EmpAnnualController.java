package com.bukkeubook.book.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bukkeubook.book.books.model.dto.BookDTO;
import com.bukkeubook.book.common.paging.Pagenation;
import com.bukkeubook.book.common.paging.SelectCriteria;
import com.bukkeubook.book.manage.model.dto.joinDTO.AppVacationAndEmpDTO;
import com.bukkeubook.book.manage.model.service.EmpAnnualService;

@Controller
@RequestMapping("/empAnnual")
public class EmpAnnualController {
	
	/* 다른 객체 바꿔치기를 방지 및 서비스 연결 */
	private final EmpAnnualService empAnnualService;
	
	/* 의존성 자동 주입 */
	@Autowired
	public EmpAnnualController(EmpAnnualService empAnnualService) {
		this.empAnnualService = empAnnualService;
	}

	/* 휴가 신청 조회 */
	@GetMapping("/restSelect")
	public ModelAndView searchPage(HttpServletRequest request,ModelAndView mv) {
		
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = empAnnualService.selectTotalCount(searchCondition, searchValue);

		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 4;		//얘도 파라미터로 전달받아도 된다.

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 2;

		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
		SelectCriteria selectCriteria = null;
		if(searchValue != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		
		System.out.println(selectCriteria);

		List<AppVacationAndEmpDTO> restList = empAnnualService.findRestList(selectCriteria);
		
		for(AppVacationAndEmpDTO rest : restList) {
			System.out.println(rest);
		} 
		
		mv.addObject("restList", restList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("manage/empAnnual/restSelect");
		
		return mv;
	}
	
	/* 휴가 신청 상세 내역 조회 */
	@GetMapping("/restDetailSelect")
	public ModelAndView restDetail (HttpServletRequest request, ModelAndView mv) {
		System.out.println("너도 오니?");
		
		int vacNo = Integer.valueOf(request.getParameter("vacNo"));
		
		AppVacationAndEmpDTO appvacAndEmp = empAnnualService.restDetailSelect(vacNo);
		
		mv.addObject("appvacAndEmp", appvacAndEmp);
		mv.setViewName("manage/empAnnual/restDetailSelect");
		
		return mv;
	}

}



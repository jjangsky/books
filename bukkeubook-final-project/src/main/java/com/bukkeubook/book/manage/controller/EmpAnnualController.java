package com.bukkeubook.book.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	@GetMapping("empAnnualList")
	public String  main() { 
		return "manage/empAnnual/empAnnualList";
	}
	
	@GetMapping("empAttendanceList")
	public String  main1() {
		return "manage/empAnnual/empAttendanceList";
	}
	
	/* 휴가 신청 조회 */
	@GetMapping("/restSelect")
	public ModelAndView findRestList(HttpServletRequest request, ModelAndView mv) {
		System.out.println("여긴오니");
		
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
		System.out.println("ddddddddddddddddddddddddddd"+searchCondition);
		int totalCount = empAnnualService.selectTotalCount(searchCondition, searchValue);

		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 3;		// 여기 바꾸세여@@@@@@@@@@@@@@@@@@

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;

		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
		SelectCriteria selectCriteria = null;
		if(searchValue != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		System.out.println(selectCriteria);
		
		List<AppVacationAndEmpDTO> restList = empAnnualService.findRestList(selectCriteria);
		
		mv.addObject("restList", restList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("manage/empAnnual/restSelect");
		
		return mv;
	}

}



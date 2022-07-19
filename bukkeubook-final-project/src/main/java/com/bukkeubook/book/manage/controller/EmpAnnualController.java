package com.bukkeubook.book.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bukkeubook.book.books.model.dto.BookDTO;
import com.bukkeubook.book.common.paging.Pagenation;
import com.bukkeubook.book.common.paging.SelectCriteria;
import com.bukkeubook.book.manage.model.dto.AppVacationDTO;
import com.bukkeubook.book.manage.model.dto.DayOffDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.AppVacationAndEmpDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.CancelVacationAndAppVacationDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.DayOffAndEmpAndDeptDTO;
import com.bukkeubook.book.manage.model.entity.AppVacation;
import com.bukkeubook.book.manage.model.entity.DayOff;
import com.bukkeubook.book.manage.model.service.EmpAnnualService;
import com.bukkeubook.book.manage.model.service.EmpDayOffService;
// 휴가 신청
@Controller
@RequestMapping("/empAnnual")
public class EmpAnnualController {
   
   /* 다른 객체 바꿔치기를 방지 및 서비스 연결 */
   private final EmpAnnualService empAnnualService;
   private final EmpDayOffService empDayOffService;
   
   /* 의존성 자동 주입 */
   @Autowired
   public EmpAnnualController(EmpAnnualService empAnnualService, EmpDayOffService empDayOffService) {
      this.empAnnualService = empAnnualService;
      this.empDayOffService = empDayOffService;
   }
   
   /* 휴가 신청 조회 */
   @GetMapping("/restSelect")
   public ModelAndView findRestList(HttpServletRequest request, ModelAndView mv) {
//      System.out.println("여긴오니");
      
      String currentPage = request.getParameter("currentPage");
      int pageNo = 1;
      
      if(currentPage != null && !"".equals(currentPage)) {
         pageNo = Integer.parseInt(currentPage);
      }

      String searchCondition = request.getParameter("searchCondition");
      String searchValue = request.getParameter("searchValue");
//      System.out.println("출력 확인 : " + searchCondition);
      int totalCount = empAnnualService.selectTotalCount(searchCondition, searchValue);

      /* 한 페이지에 보여 줄 게시물 수 */
      int limit = 3;

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
   
	/* 휴가 신청 상세 내역 조회 */
	@GetMapping("/restDetailSelect")
	public ModelAndView restDetail (HttpServletRequest request, ModelAndView mv) {
		
		int vacNo = Integer.valueOf(request.getParameter("vacNo"));
		
		AppVacationAndEmpDTO appvacAndEmp = empAnnualService.restDetailSelect(vacNo);
		
		mv.addObject("appvacAndEmp", appvacAndEmp);
		mv.setViewName("manage/empAnnual/restDetailSelect");
		
		return mv;
	}

	/* 승인 클릭시 연차 횟수 차감 트랜잭션 */
	// 이 경우 연결되는 Service는 DayOffService에서 처리...
	@GetMapping("/empDayOffList")
	public ModelAndView updateDayOffInfo(DayOffDTO dayOffDTO, int empNo, ModelAndView mv) {
		
		List<DayOffDTO> dayOffList = empDayOffService.findDayOffByNo(empNo);
		
		mv.addObject("dayOffList", dayOffList);
		mv.setViewName("/empAnnual/empDayOffDetail");
		
		System.out.println("나오니?" + dayOffList);
		System.out.println("나오니?" + dayOffList);
		System.out.println("나오니?" + dayOffList);
		System.out.println("나오니?" + dayOffList);
		System.out.println("나오니?" + dayOffList);
		System.out.println("나오니?" + dayOffList);
		System.out.println("나오니?" + dayOffList);
		System.out.println("나오니?" + dayOffList);
		System.out.println("나오니?" + dayOffList);
		
		return mv;
	}
	
//	@Transactional
//	@PostMapping("/empDayOffList")
//	public ModelAndView modifyDayOffInfo(RedirectAttributes rttr, DayOffDTO dayOffDTO, ModelAndView mv, @ModelAttribute AppVacationDTO appVacationDTO) {
//		
//		empDayOffService.modifyDayOffInfo(dayOffDTO, appVacationDTO);
//		
//		mv.setViewName("redirect:/empAnnual/empDayOffList");
//		return mv;
//		
//	};


	/* 휴가 취소 신청 조회 */
	@GetMapping("/restCancelSelect")
	public ModelAndView findCancelVacList (HttpServletRequest request, ModelAndView mv) {
//		  System.out.println("여기는 휴가 취소 리스트 컨트롤러");
		
	      String currentPage = request.getParameter("currentPage");
	      int pageNo = 1;
	      
	      if(currentPage != null && !"".equals(currentPage)) {
	         pageNo = Integer.parseInt(currentPage);
	      }

	      String searchCondition = request.getParameter("searchCondition");
	      String searchValue = request.getParameter("searchValue");
//	      System.out.println("출력 확인 구문 : " + searchCondition);
	      
	      int totalCount = empAnnualService.selectCancelTotalCount(searchCondition, searchValue);

	      /* 한 페이지에 보여 줄 게시물 수 */
	      int limit = 8;

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
	      
	      List<CancelVacationAndAppVacationDTO> cancelVacList = empAnnualService.findCancelRestList(selectCriteria);
	      
	      for(CancelVacationAndAppVacationDTO cancelVac : cancelVacList) {
	    	  System.out.println(cancelVac);
	      } 
	      
	      mv.addObject("cancelVacList", cancelVacList);
	      mv.addObject("selectCriteria", selectCriteria);
	      mv.setViewName("manage/empAnnual/restCancelSelect");
	      
	      return mv;
	   }
	
	/* 휴가 취소 신청 상세 내역 조회 */
	@GetMapping("/restCancleDetailSelect")
	public ModelAndView restCancleDetail (HttpServletRequest request, ModelAndView mv) {
		
		int vacCancNo = Integer.valueOf(request.getParameter("vacCancNo"));
		
		CancelVacationAndAppVacationDTO cancelVacDetail = empAnnualService.restCancelDetailSelect(vacCancNo);
		
		mv.addObject("cancelVacDetail", cancelVacDetail);
		mv.setViewName("manage/empAnnual/restCancleDetailSelect");
		
		return mv;
	}
	
}

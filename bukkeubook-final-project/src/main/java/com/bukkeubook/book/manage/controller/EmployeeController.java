package com.bukkeubook.book.manage.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bukkeubook.book.books.model.dto.BookDTO;
import com.bukkeubook.book.common.paging.Pagenation;
import com.bukkeubook.book.common.paging.SelectCriteria;
import com.bukkeubook.book.manage.model.dto.DeptDTO;
import com.bukkeubook.book.manage.model.dto.EmpDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.EmpAndDeptDTO;
import com.bukkeubook.book.manage.model.entity.EmpAndDept;
import com.bukkeubook.book.manage.model.service.EmpService;


@Controller
@RequestMapping("/manage") 
public class EmployeeController {

private EmpService empService;
	
	@Autowired
	public EmployeeController(EmpService empService) {
		this.empService = empService;
	}
	
	/* 사원조회 , 페이징, 검색기능 */
	@GetMapping("/empList")
	public ModelAndView searchPage(HttpServletRequest request, ModelAndView mv) {  //ModelAndView 뷰 리졸버의 역할 _리턴할 페이지 설정 , 보내는객체
		System.out.println("ddddddddddddddddddddddddddddddddddddd");
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = empService.selectTotalCount(searchCondition, searchValue);

		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10;		//얘도 파라미터로 전달받아도 된다.

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

		List<EmpAndDeptDTO> empList = empService.searchEmpList(selectCriteria);
		
		for(EmpAndDeptDTO emp : empList) {
			System.out.println(emp);
		}
		
		mv.addObject("empList", empList);  //보내는 객체 설정
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("manage/employee/empList"); //리턴할 페이지 설정
		
		return mv;
	}
	
	/* 퇴사 사원 조회 (퇴사여부 Y) */
	@GetMapping("leaveEmpList")
	public ModelAndView findLeaveEmpList(ModelAndView mv) {  

		String empEndYn = "Y";
		List<EmpAndDeptDTO> leaveEmpList = empService.findLeaveEmpList(empEndYn);
		System.out.println("leaveEmpList" + leaveEmpList);
		mv.addObject("leaveEmpList", leaveEmpList);  //보내는 객체 설정
		mv.setViewName("manage/employee/leaveEmpList"); //리턴할 페이지 설정
		
		return mv;
	}
		
	/* 사원 상세조회 */
	@GetMapping("/oneEmp/{empNo}")
	public ModelAndView empDetail(ModelAndView mv, @PathVariable String empNo){
		
		int number = Integer.valueOf(empNo);
		
		System.out.println("컨트롤러에서       " + empNo);
		System.out.println("컨트롤러에서       " + number);
		
		EmpAndDeptDTO emp  = empService.searchEmpDetail(number);
		
		System.out.println("컨트롤러에서       ddddddddddddddddddddddddddddddddd" + emp);
		
		mv.addObject("emp", emp);
		mv.setViewName("manage/employee/empDetail");
		return mv;
	}
	
	/* 퇴사사원 상세조회 */
	@GetMapping("/oneLeaveEmp/{empNo}")
	public ModelAndView leaveEmpDetail(ModelAndView mv, @PathVariable String empNo){
		
		int number = Integer.valueOf(empNo);
		
		System.out.println("컨트롤러에서       " + empNo);
		System.out.println("컨트롤러에서       " + number);
		
		EmpAndDeptDTO emp  = empService.searchEmpDetail(number);
		
		System.out.println("컨트롤러에서       " + emp);
		
		mv.addObject("emp", emp);
		mv.setViewName("/manage/employee/leaveEmpDetail");
		return mv;
	}
	
	/* 신규사원등록 기능(insert) */
	@GetMapping("empInsert")
	public ModelAndView empInsertPage(ModelAndView mv) {
		
		mv.setViewName("/manage/employee/empInsert");
		
		return mv;
	}
	
	@PostMapping("insert")
	public ModelAndView insertEmp(EmpDTO empDTO, ModelAndView mv, RedirectAttributes rttr) {
		
		System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeee " + empDTO);
		
		String empAddress = "주소";
		empDTO.setEmpAddress(empAddress);	
		System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeee " + empDTO);
		
		empService.insertNewEmp(empDTO);
		
		rttr.addFlashAttribute("insertSuccessMessage", "성공"); //addFlashAttribute 한번만 보여주고 감
//		mv.setViewName("redirect:/");
		mv.setViewName("redirect:/manage/empList");
		return mv;
	};	
	
	/* 사원정보 수정 */
//	@GetMapping("empDetailUpdate")
//	public ModelAndView empUpdatePage(ModelAndView mv) {
//		
//		mv.setViewName("/manage/employee/empDetailUpdate");
//		
//		return mv;
//	}
	
	@GetMapping("detailUpdate/{empNo}")
//	public ModelAndView empUpdatePage(EmpDTO empDTO, int empNo, ModelAndView mv) {
	public ModelAndView empUpdatePage(ModelAndView mv,  @PathVariable String empNo) {
		
		int number = Integer.valueOf(empNo);
		
		List<EmpDTO> empList = empService.findEmpByEmpNo(number);
		
		mv.addObject("empList", empList);
		mv.setViewName("manage/employee/empDetailUpdate");
		
		return mv;

	}

	@PostMapping("empDetailUpdate")
	public ModelAndView modifyEmpInfo(EmpDTO empDTO, ModelAndView mv, RedirectAttributes rttr) {
		
		empService.modifyEmpInfo(empDTO);
		
		rttr.addFlashAttribute("updateSuccessMessage", "성공");
		mv.setViewName("redirect:/employee/empList");
		return mv;
	};
	
	@GetMapping("personnelSelect")
	public String perconnelList() {
		return "manage/employee/personnelSelect";
	}

}
 

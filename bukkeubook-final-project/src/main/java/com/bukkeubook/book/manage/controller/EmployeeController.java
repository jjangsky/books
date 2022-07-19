package com.bukkeubook.book.manage.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bukkeubook.book.common.paging.Pagenation;
import com.bukkeubook.book.common.paging.SelectCriteria;
import com.bukkeubook.book.manage.model.dto.joinDTO.EmpAndDeptDTO;
import com.bukkeubook.book.manage.model.entity.EmpAndDept;
import com.bukkeubook.book.manage.model.service.EmpService;
import com.bukkeubook.book.mypage.model.dto.ProfPhotoDTO;
import com.bukkeubook.book.mypage.model.dto.SignDTO;

@Controller
@RequestMapping("/manage") 
public class EmployeeController {

private final EmpService empService;
	
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
		mv.addObject("leaveEmpList", leaveEmpList);  	// 보내는 객체 설정
		mv.setViewName("manage/employee/leaveEmpList"); // 리턴할 페이지 설정
		
		return mv;
	}
		
	/* 사원 상세조회 */
	@GetMapping("/oneEmp/{empNo}")
	public ModelAndView empDetail(ModelAndView mv, @PathVariable String empNo){
		
		int number = Integer.valueOf(empNo);
		
		System.out.println("컨트롤러에서       " + empNo);
		System.out.println("컨트롤러에서       " + number);
		
		EmpAndDeptDTO emp  = empService.searchEmpDetail(number);
		
		System.out.println("컨트롤러에서       " + emp);
		
		mv.addObject("emp", emp);
		mv.setViewName("/manage/employee/empDetail");
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
	
	@GetMapping("personnelSelect")
	public String perconnelList() {
		return "manage/employee/personnelSelect";
	}
	
	/* 프로필 사진 등록 */
//	@PostMapping("/insert")
//	public ModelAndView registMyProfile(ModelAndView mv, HttpServletRequest request, @RequestParam("singleFile") MultipartFile singleFile, RedirectAttributes rttr) {
//		
//		int memberCode = 5;
//		
//		/* file타입으로 넘어온 파일을 저장하는 과정 진행 */
//		/* 1. 파일 저장 위치 설정 */
//		/* 1-1. root(webapp폴더 아래의 resources폴더) 경로 추출하기 */
//		String root = System.getProperty("user.dir");
//		System.out.println("root까지의 경로 : " + root);
//		
//		String filePath = root + "/src/main/resources/static/images/manage/employee";
//		
//		/* 1-2. employee 폴더 생성 */
//		File mkdir = new File(filePath);	// file.io 패키지로 import
//		if(!mkdir.exists()) {
//			mkdir.mkdirs();
//		}
		
		/* 2. 파일을 전달받아 파일명 변경 처리 */
//		String originFileName = singleFile.getOriginalFilename();
//		System.out.println("원본 이름 : " + originFileName);
//		String ext = originFileName.substring(originFileName.lastIndexOf("."));
//		String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
//		System.out.println("변경한 이름 : " + saveName);
//	
//		/* 3. 파일을 저장한다. */
//		try {
//			singleFile.transferTo(new File(filePath + "/" + saveName));
//			
//			/* DB에 업로드한 파일의 정보를 저장하는 비즈니스 로직 수행 */
//			
//			ProfPhotoDTO profile = new ProfPhotoDTO(); 
//			profile.setEmpNo(memberCode);
//			profile.setPhotoOrigName(originFileName);
//			profile.setPhotoSavedName(saveName);
//			profile.setPhotoSavedPath(filePath);
//			
//			empService.inputProfile(profile);
//			
//			rttr.addFlashAttribute("successMessage", "프로필 사진 변경을 성공하셨습니다.");
//			mv.setViewName("redirect:/manage/employee/empUpdate");
//			
//		} catch (IllegalStateException | IOException e) {
//			e.printStackTrace();
//			
//			/* 실패 시 파일 삭제 */
//			new File(filePath + "/" + saveName).delete();
//			rttr.addFlashAttribute("successMessage", "프로필 사진 변경을 실패하셨습니다.");
//			mv.setViewName("redirect:/main");
//		}
//		
//		return mv;
//	}
	
	/* 마이페이지 개인 정보 수정 화면이동 */
//	@GetMapping("/thumbnail")
//	public ModelAndView find(ModelAndView mv) {
//		
//		/* 개인정보 조회 */
//		int memberCode = 5;
//		EmpAndDeptDTO myInfo = myInfoModifyService.findMyInfo(memberCode);
//		System.out.println(myInfo);
//		
//		/* 프로필 사진 조회 */
//		List<ProfPhotoDTO> profile = myInfoModifyService.findMyProfile(memberCode);
//		System.out.println(profile);
//		
//		/* 현재 서명 조회 */
//		SignDTO mySign = myInfoModifyService.findMySign(memberCode);
//		System.out.println(mySign);
//		
//		mv.addObject("myInfo", myInfo);
//		mv.addObject("profile", profile);
//		mv.addObject("mySign", mySign);
//		mv.setViewName("mypage/mypageInfoModify");
//		
//		return mv;
//	}

}
 

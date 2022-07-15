package com.bukkeubook.book.document.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bukkeubook.book.document.model.dto.AppRootDTO;
import com.bukkeubook.book.document.model.dto.ApproverDTO;
import com.bukkeubook.book.document.model.dto.DeptDTO;
import com.bukkeubook.book.document.model.dto.DocumentAndEmpAndFormCateDTO;
import com.bukkeubook.book.document.model.dto.EmpDTO;
import com.bukkeubook.book.document.model.dto.FormCateDTO;
import com.bukkeubook.book.document.model.dto.SubmitDocumentDTO;
import com.bukkeubook.book.document.model.dto.TempStoreDocumentDTO;
import com.bukkeubook.book.document.model.entity.Approver;
import com.bukkeubook.book.document.model.entity.SubmitApprover;
import com.bukkeubook.book.document.model.service.DocService;

@Controller
@RequestMapping("document/*")
public class DocumentController {		// 전자결재 컨트롤러
	
	private DocService docService;
	
	@Autowired
	public DocumentController(DocService docService) {
		this.docService = docService;
	}

	/* 전자결재 작성 첫화면 - 양식 고르기 */
	@GetMapping("insert")
	public ModelAndView toDocWrite(ModelAndView mv) {
		
		List<FormCateDTO> formList = docService.findDocFormList();
		mv.addObject("formList", formList);
		mv.setViewName("document/insert");
		
		return mv;
	}
	
	/* 각 양식에따른 전자결재 화면 이동 */
	@GetMapping("insertTo/{formNo}")
	public ModelAndView toSelectByFormNo(ModelAndView mv, @PathVariable String formNo) {
		
//		System.out.println("fffffffffffffffffffffffffffffffffffff" + formNo);
		
		mv.setViewName("document/insert"+formNo);
		
		return mv;
		
	}
	
	/* 수신함 리스트 조회 */
	@GetMapping("docList")
	public ModelAndView toDocList(ModelAndView mv) {
		
		int empNo = 17;
		
		docService.findInboxAllList(empNo);
		
		mv.setViewName("/document/docList");
		
		return mv;
	}
	
	/* 임시저장 리스트 조회 */
	@GetMapping("docTempList")
	public ModelAndView toTempDocList(ModelAndView mv) {
		
		int tempEmpNo = 10;
		String docStatus = "임시저장";
		
		List<DocumentAndEmpAndFormCateDTO> tempDocList = docService.findTempDocList(tempEmpNo,docStatus);
		
//		System.out.println("ccccccccccccccc임시저장 리스트 조회cccccccccccccccc" + tempDocList);
		
		mv.addObject("tempDocList", tempDocList);
		mv.setViewName("/document/docTempList");
		
		return mv;
	}
	
	/* 결재라인 지정 ajax select Tag Option Dept */
	@GetMapping(value = {"dept"}, produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<DeptDTO> findDeptList(){
		
		List<DeptDTO> list = new ArrayList<>();
		
		list = docService.findDept();
		
//		System.out.println(list);
		
		return list;
	}
	
	/* 결재라인 지정 ajax select Tag Option Emp */
	@GetMapping(value = {"emp/{deptValue}"}, produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<EmpDTO> findEmpList(@PathVariable String deptValue){
		
		System.out.println("ttttttttttttttttttttttttttttttttttttttttttttttt" + deptValue);
		
		List<EmpDTO> list = new ArrayList<>();
		
		int dept = Integer.valueOf(deptValue);
		
		list = docService.findEmp(dept);
		
		System.out.println(list);
		
		return list;
	}
	
	/* 임시저장 */
	@PostMapping("tempStore")
	public ModelAndView tempSave(TempStoreDocumentDTO newDoc, RedirectAttributes rttr, ModelAndView mv) {
		
//		System.out.println("djhkfghdjsgkfdjdfjdffffffffffffffffffffffffffffffffffffffffff");
		String docStatus = "임시저장";
		
		newDoc.setDocStatus1(docStatus);
		System.out.println(newDoc);
		
		docService.insertNewtempDocument(newDoc);
		
		rttr.addFlashAttribute("insertSuccess", "임시저장을 성공하였습니다.");
		mv.setViewName("redirect:/document/docTempList");
		
		return mv;
	}
	
	/* 임시저장 수정 페이지 접속*/
	@GetMapping("tempInfo/{docNo}")
	public ModelAndView toUpdateTempDocPage(ModelAndView mv,@PathVariable String docNo) {
		
		int selectedDocNo = Integer.valueOf(docNo);
		int tempEmpNo = 10;
		String docStatus = "임시저장";
		System.out.println("여기컨트롤러에서 번호 잘받았니      " + selectedDocNo);
		
		DocumentAndEmpAndFormCateDTO oneTempDoc = docService.findOneTempDoc(selectedDocNo,tempEmpNo,docStatus);
		System.out.println("갔다왔니");
		System.out.println("갔다왔니");
		System.out.println(oneTempDoc);
		
		mv.addObject("oneTempDoc", oneTempDoc);

		if(oneTempDoc.getFormNo() == 1) {	// 기안서일 경우
			mv.setViewName("/document/detailTempDocDraft");
		} else {	//지결서일 경우
			mv.setViewName("/document/detailTempDocExpenditure");
		}
		
		return mv;
	}
	
	/* 임시저장 수정하기 */
	@PostMapping("tempUpdate")
	public ModelAndView updateTempDoc(ModelAndView mv, TempStoreDocumentDTO updateDoc, RedirectAttributes rttr) {
		
		System.out.println("수정할 아이 챙겨왔니    " + updateDoc);
		
		docService.updateTempDocument(updateDoc);
		
		rttr.addFlashAttribute("insertSuccess", "임시저장을 성공하였습니다.");
		mv.setViewName("redirect:/document/docTempList");
		
		return mv;
	}
	
	/* 임시저장 삭제하기 */
	@GetMapping("deleteTempDoc/{num}")
	public ModelAndView deleteTempDoc(ModelAndView mv, RedirectAttributes rttr, @PathVariable String num) {
		
		System.out.println("Controller                       " + num);
		
		int docNo = Integer.valueOf(num);
		
		System.out.println("for          Controller           " + docNo);
		
		docService.deleteTempDoc(docNo);
		
		rttr.addFlashAttribute("insertSuccess", "임시저장을 성공하였습니다.");
		mv.setViewName("redirect:/document/docTempList");
		
		return mv;
		
	}
	
	/* 새로작성한 기안서, 지결서 상신하기 */
	@PostMapping("submitReport")
	public ModelAndView draftSubmitReport(ModelAndView mv,SubmitDocumentDTO newDoc, RedirectAttributes rttr
										, @RequestParam("app1") String app1, @RequestParam String approver1
										, @RequestParam String approver2, @RequestParam String approver3, @RequestParam String submitTitle) {
		System.out.println("잘 가져 왔니");
		System.out.println(newDoc);
		System.out.println("number1          " + app1);
		System.out.println(approver1);
		System.out.println(approver2);
		System.out.println(approver3);
		System.out.println(submitTitle);
		String appStatus = "대기";
		String docStatus = "대기";
		/* 일단 문서 인서트 */
		newDoc.setDocTitle2(submitTitle);
		newDoc.setDocStatus2(docStatus);
		
		/* 다음은 결재경로 */
		AppRootDTO appRoot = new AppRootDTO();
		int step = Integer.valueOf(app1);
		appRoot.setStepNo(step);
		
		/* 결재자 인서트 */
		List<SubmitApprover> approverList = new ArrayList<>();
		
		/* 결재자가 한명일 때 */
		if(approver1 != "" && approver2 == "" && approver3 == "") {
			
			int appr = Integer.valueOf(approver1);
			ApproverDTO approver = new ApproverDTO();
			approver.setEmpNo(appr);
			approver.setAppStatus(appStatus);
			docService.insertNewDocOneAcc(newDoc,appRoot,approver);
			
		} else if(approver1 != "" && approver2 != "" && approver3 == "") {
			/* 결재자가 두명일 때 */
			int appr1 = Integer.valueOf(approver1);
			SubmitApprover appro = new SubmitApprover();
			appro.setEmpNo2(appr1);
			appro.setAppStatus2(appStatus);
			approverList.add(appro);
			
			int appr2 = Integer.valueOf(approver2);
			SubmitApprover appro2 = new SubmitApprover();
			appro2.setEmpNo2(appr2);
			appro2.setAppStatus2(appStatus);
			approverList.add(appro2);
			
			
			docService.insertNewDocThreeAcc(newDoc,appRoot,approverList);
			
		} else if (approver1 != "" && approver2 != "" && approver3 != "") {
			/* 결재자가 세명일 때 */
			int appr1 = Integer.valueOf(approver1);
			SubmitApprover appro = new SubmitApprover();
			appro.setEmpNo2(appr1);
			appro.setAppStatus2(appStatus);
			approverList.add(appro);
			
			int appr2 = Integer.valueOf(approver2);
			SubmitApprover appro2 = new SubmitApprover();
			appro2.setEmpNo2(appr2);
			appro2.setAppStatus2(appStatus);
			approverList.add(appro2);
			
			int appr3 = Integer.valueOf(approver3);
			SubmitApprover appro3 = new SubmitApprover();
			appro3.setEmpNo2(appr2);
			appro3.setAppStatus2(appStatus);
			approverList.add(appro3);
			
			docService.insertNewDocThreeAcc(newDoc,appRoot,approverList);
			
		}
		
		rttr.addFlashAttribute("insertSuccess", "임시저장을 성공하였습니다.");
		mv.setViewName("redirect:/document/docList");
		
		return mv;
	}
	
	/* 임시저장된 기안서, 지결서 상신하기 */
	@PostMapping("submitTempReport")
	public ModelAndView submitTempReport (ModelAndView mv,SubmitDocumentDTO tempDoc, RedirectAttributes rttr
										, @RequestParam("app1") String app1, @RequestParam String approver1
										, @RequestParam String approver2, @RequestParam String approver3, @RequestParam String submitTitle) {
		
		System.out.println("잘 가져 왔니");
		System.out.println(tempDoc);
		System.out.println("number1          " + app1);
		System.out.println(approver1);
		System.out.println(approver2);
		System.out.println(approver3);
		System.out.println(submitTitle);
		String appStatus = "대기";
		/* 일단 문서 인서트 */
		tempDoc.setDocTitle2(submitTitle);
		
		/* 다음은 결재경로 */
		AppRootDTO appRoot = new AppRootDTO();
		int step = Integer.valueOf(app1);
		appRoot.setStepNo(step);
		
		/* 결재자 인서트 */
		List<SubmitApprover> approverList = new ArrayList<>();
		
		/* 결재자가 한명일 때 */
		if(approver1 != "" && approver2 == "" && approver3 == "") {
			
			int appr = Integer.valueOf(approver1);
			ApproverDTO approver = new ApproverDTO();
			approver.setEmpNo(appr);
			approver.setAppStatus(appStatus);
			docService.submitTempDocOneAcc(tempDoc,appRoot,approver);
			
		} else if(approver1 != "" && approver2 != "" && approver3 == "") {
			/* 결재자가 두명일 때 */
			int appr1 = Integer.valueOf(approver1);
			SubmitApprover appro = new SubmitApprover();
			appro.setEmpNo2(appr1);
			appro.setAppStatus2(appStatus);
			
			int appr2 = Integer.valueOf(approver2);
			SubmitApprover appro2 = new SubmitApprover();
			appro2.setEmpNo2(appr2);
			appro2.setAppStatus2(appStatus);
			
			approverList.add(appro);
			approverList.add(appro2);
			
			docService.submitTempDocTwoAcc(tempDoc,appRoot,approverList);
			
		} else if (approver1 != "" && approver2 != "" && approver3 != "") {
			/* 결재자가 세명일 때 */
			int appr1 = Integer.valueOf(approver1);
			SubmitApprover appro = new SubmitApprover();
			appro.setEmpNo2(appr1);
			appro.setAppStatus2(appStatus);
			
			int appr2 = Integer.valueOf(approver2);
			SubmitApprover appro2 = new SubmitApprover();
			appro2.setEmpNo2(appr2);
			appro2.setAppStatus2(appStatus);
			
			int appr3 = Integer.valueOf(approver3);
			SubmitApprover appro3 = new SubmitApprover();
			appro3.setEmpNo2(appr2);
			appro3.setAppStatus2(appStatus);
			
			approverList.add(appro);
			approverList.add(appro2);
			approverList.add(appro3);
			
			docService.submitTempDocTwoAcc(tempDoc,appRoot,approverList);
		
		}
		
		rttr.addFlashAttribute("insertSuccess", "임시저장을 성공하였습니다.");
		mv.setViewName("redirect:/document/docList");
		
		return mv;
	}
}

package com.bukkeubook.book.document.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bukkeubook.book.document.model.dto.DeptDTO;
import com.bukkeubook.book.document.model.dto.DocumentAndEmpAndFormCateDTO;
import com.bukkeubook.book.document.model.dto.DocumentDTO;
import com.bukkeubook.book.document.model.dto.EmpDTO;
import com.bukkeubook.book.document.model.dto.FormCateDTO;
import com.bukkeubook.book.document.model.service.DocService;

@Controller
@RequestMapping("document/*")
public class DocumentController {		// 전자결재 컨트롤러
	
	private DocService docService;
	
	@Autowired
	public DocumentController(DocService docService) {
		this.docService = docService;
	}

	/* 전자결재 작성 첫화면 */
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
		
		System.out.println("fffffffffffffffffffffffffffffffffffff" + formNo);
		
		mv.setViewName("document/insert"+formNo);
		
		return mv;
		
	}
	
	/* 결재내역 리스트 조회 */
	@GetMapping("docList")
	public String toDocList() {
		return "/document/docList";
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
	public ModelAndView tempSave(DocumentDTO newDoc, RedirectAttributes rttr, ModelAndView mv) {
		
//		System.out.println("djhkfghdjsgkfdjdfjdffffffffffffffffffffffffffffffffffffffffff");
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
		mv.setViewName("/document/detailTempDoc");
		
		return mv;
	}
	
	/* 임시저장 수정하기 */
	@PostMapping("tempUpdate")
	public ModelAndView updateTempDoc(ModelAndView mv, DocumentDTO updateDoc, RedirectAttributes rttr) {
		
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
}

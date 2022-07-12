package com.bukkeubook.book.document.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bukkeubook.book.document.model.dto.DeptDTO;
import com.bukkeubook.book.document.model.dto.EmpDTO;
import com.bukkeubook.book.document.model.dto.FormCateDTO;
import com.bukkeubook.book.document.model.service.DocService;

@Controller
@RequestMapping("document/*")
public class DocumentController {
	
	private DocService docService;
	
	@Autowired
	public DocumentController(DocService docService) {
		this.docService = docService;
	}

	@GetMapping("insert")
	public ModelAndView toDocWrite(ModelAndView mv) {
		
		List<FormCateDTO> formList = docService.findDocFormList();
		mv.addObject("formList", formList);
		mv.setViewName("document/insert");
		
		return mv;
	}
	
	@GetMapping("insertTo/{formNo}")
	public ModelAndView toSelectByFormNo(ModelAndView mv, @PathVariable String formNo) {
		
		System.out.println("fffffffffffffffffffffffffffffffffffff" + formNo);
		
		mv.setViewName("document/insert"+formNo);
		
		return mv;
		
	}
	
	@GetMapping("docList")
	public String toDocList() {
		return "/document/docList";
	}
	
	@GetMapping("docTempList")
	public String totempDocList() {
		return "/document/docTempList";
	}
	
	@GetMapping(value = {"dept"}, produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<DeptDTO> findDeptList(){
		
		List<DeptDTO> list = new ArrayList<>();
		
		list = docService.findDept();
		
		System.out.println(list);
		
		return list;
	}
	
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
}

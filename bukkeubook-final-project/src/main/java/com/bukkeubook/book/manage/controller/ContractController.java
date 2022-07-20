package com.bukkeubook.book.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bukkeubook.book.manage.model.dto.joinDTO.EmpContAndEmpDTO;
import com.bukkeubook.book.manage.model.service.ContractService;

@Controller
@RequestMapping("/cont")
public class ContractController {
	
	private final ContractService contractService;
	
	@Autowired
	public ContractController( ContractService contractService) {
		this.contractService = contractService;
	}
	
	/* 근로계약서 전체 내역 조회 */
	@GetMapping("/findCont")
	public ModelAndView findContracts(ModelAndView mv) {
		
		List<EmpContAndEmpDTO> contList = contractService.findAllContracts();
		System.out.println(contList);
		
		mv.addObject("contList", contList);
		mv.setViewName("/manage/contract/contractSelect");
		
		return mv;
		
	}
	
	/* 근로계약서 상세 조회 */
	@GetMapping("findDetailCont")
	public ModelAndView findDetailContracts(HttpServletRequest request, String no, ModelAndView mv) {
		
		int contNo = Integer.valueOf(request.getParameter("no"));
		EmpContAndEmpDTO empCont = contractService.findDetailCont(contNo);
		
		System.out.println(empCont);
		
		mv.addObject("empCont", empCont);
		mv.setViewName("/manage/contract/contractInsert");
		
		return mv;
	}
	

}

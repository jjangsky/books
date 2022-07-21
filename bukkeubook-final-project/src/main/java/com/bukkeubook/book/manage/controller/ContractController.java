package com.bukkeubook.book.manage.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bukkeubook.book.manage.model.dto.ContFileDTO;
import com.bukkeubook.book.manage.model.dto.EmpContDTO;
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
		mv.setViewName("/manage/contract/contractDetail");
		
		return mv;
	}
	
	/* 근로계약서 등록 화면 이동 */
	@GetMapping("registPage")
	public ModelAndView moveRegistPage(ModelAndView mv) {
		
		mv.setViewName("/manage/contract/registContract");
		
		return mv;
	}
	
	/* 근로계약서 등록 하기 */
	@PostMapping("/registCont")
	public ModelAndView registContractEmp(HttpServletRequest request, 
											@RequestParam("singleFile") MultipartFile singleFile, 
											RedirectAttributes rttr, ModelAndView mv, EmpContDTO empCont) {
		
		/* 1. 근로 계약서 테이블에 전달 받은 정보 Insert */
		String memberName = "김유찬";
		
		/* 현재 시각 sql형으로 구하기 */
		Date today = new Date();
		long time = today.getTime();
		java.sql.Date current = new java.sql.Date(time);
		
		empCont.setContWriter(memberName);
		empCont.setContDate(current);
		
		System.out.println(empCont);
		
		/* 근로 계약서 내역 테이블에 등록(파일 등록X) */
		contractService.registNewContract(empCont);
		
		/* 2. 방금 등록한 내역 테이블의 근로계약서 번호 가져오기 */
		
		List<EmpContAndEmpDTO> contNumber = contractService.findAllContracts();
		int contractNum = contNumber.get(0).getContNo(); // 방금 등록한 근로계약서 테이블의 기본키
		
		System.out.println(contractNum);
		
		/* 3. 근로계약서 파일 업로드 */
		String root = System.getProperty("user.dir");
		System.out.println("root까지의 경로 : " + root);
		
		String filePath = root + "/src/main/resources/static/contract";
		
		File mkdir = new File(filePath);	
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		String originFileName = singleFile.getOriginalFilename();
		System.out.println("원본 이름 : " + originFileName);
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
		System.out.println("변경한 이름 : " + saveName);
		
		try {
			singleFile.transferTo(new File(filePath + "/" + saveName));
			
			ContFileDTO contFile = new ContFileDTO();
			contFile.setCfileOrigName(originFileName);
			contFile.setCfilePath(filePath);
			contFile.setCfileSavedName(saveName);
			contFile.setContNo(contractNum);
			
			contractService.registNewFile(contFile);
			
			rttr.addFlashAttribute("successMessage", "성공");
			mv.setViewName("redirect:/manage/contract/contractSelect");
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			new File(filePath + "/" + saveName).delete();
			rttr.addFlashAttribute("successMessage", "프로필 사진 변경을 실패하셨습니다.");
			mv.setViewName("redirect:/manage/contract/contractSelect");
			
		}
		return mv;
	}
	

}

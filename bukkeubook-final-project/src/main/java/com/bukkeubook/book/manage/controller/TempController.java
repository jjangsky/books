package com.bukkeubook.book.manage.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bukkeubook.book.manage.model.dto.EmpDTO;
import com.bukkeubook.book.manage.model.dto.ProfPhotoDTO;
import com.bukkeubook.book.manage.model.dto.SignDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.EmpAndDeptDTO;

public class TempController {
	/* 신규사원등록 기능(insert) */
	@GetMapping("empInsert")
	public ModelAndView empInsertPage(ModelAndView mv) {

		mv.setViewName("/manage/employee/empInsert");

		return mv;
	}

	/* 신규 직원 등록 insert */
	@PostMapping("insert")
	public ModelAndView insertEmp(EmpDTO empDTO, ModelAndView mv, HttpServletRequest request,
			@RequestParam("proFile") MultipartFile proFile, @RequestParam("nameFile") MultipartFile nameFile,
			RedirectAttributes rttr) {

		System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeee " + empDTO);
		/* 여기는 왜 그럴까요? */
		String empAddress = "주소";
		empDTO.setEmpAddress(empAddress);
		System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeee " + empDTO);

		/* 프로필 사진, 도장 사진을 제외한 나머지 회원 정보 insert */
		empService.insertNewEmp(empDTO);

		/* 방금 등록한 회원에 대해 기본키 가져오기 */
		List<EmpAndDeptDTO> lastEmp = empService.selectLastEmp();

		System.out.println(lastEmp.get(0).getEmpNo()); // 방금 등록한 회원의 번호 추출

		int registEmp = lastEmp.get(0).getEmpNo();

		/* 민님 이틀 갈아넣어서 만든 프로젝트 내부 저장 방식 소스 */
		String root = System.getProperty("user.dir");
		System.out.println("root까지의 경로 : " + root);

		String filePath = root + "/src/main/resources/static/images/mypage"; // 프사 저장 루트
		String signPath = root + "/src/main/resources/static/images/sign"; // 서명 저장 루트

		/* 파일 없으면 만들어줌 */
		File mkdir = new File(filePath);
		if (!mkdir.exists()) {
			mkdir.mkdirs();
		}
		File mkdir1 = new File(signPath);
		if (!mkdir1.exists()) {
			mkdir1.mkdirs();
		}

		/* 원본이랑 저장되는 값 이름이 같으면 안됨 -> 그래서 변경된 값으로 새로 프로젝트에 저장 */
		String originFileName = proFile.getOriginalFilename();
		String originFileName1 = nameFile.getOriginalFilename();
		System.out.println("프로필 사진 원본 이름 : " + originFileName);
		System.out.println("서명 사진 원본 이름 : " + originFileName1);

		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		String ext1 = originFileName1.substring(originFileName1.lastIndexOf("."));

		String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
		String saveName1 = UUID.randomUUID().toString().replace("-", "") + ext1;

		System.out.println("변경한 이름 : " + saveName);
		System.out.println("변경한 이름 : " + saveName1);

		/* 프로필 사진 저장 처리 */
		try {
			proFile.transferTo(new File(filePath + "/" + saveName));

			/* DB에 저장할 파일 정보를 DTO에 담기 */
			ProfPhotoDTO profile = new ProfPhotoDTO();
			profile.setEmpNo(registEmp);
			profile.setPhotoOrigName(originFileName);
			profile.setPhotoSavedName(saveName);
			profile.setPhotoSavedPath(filePath);

			/* 서비스로 보내서 DB로 전송 */
			empService.registEmpProFile(profile);

		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();

			/* 실패 시 파일 삭제 */
			new File(filePath + "/" + saveName).delete();
		}

		/* 서명 사진 저장 처리 */
		try {
			nameFile.transferTo(new File(signPath + "/" + saveName1));
			/* DB에 저장할 파일 정보를 DTO에 담기 */
			SignDTO signFile = new SignDTO();
			signFile.setEmpNo(registEmp);
			signFile.setSignName(originFileName1);
			signFile.setSignSavedName(saveName1);
			signFile.setSignPath(signPath);

			/* 서비스로 보내서 DB로 전송(signService) */
			signService.registEmpNameFile(signFile);

		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();

			/* 실패 시 파일 삭제 */
			new File(signPath + "/" + saveName1).delete();
		}

		rttr.addFlashAttribute("insertSuccessMessage", "성공"); // addFlashAttribute 한번만 보여주고 감
		mv.setViewName("redirect:/manage/empList");
		return mv;
	};
}

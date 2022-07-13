package com.bukkeubook.book.manage.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.bukkeubook.book.manage.model.dto.joinDTO.AppVacationAndEmpDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.CancleVacAndAppVacDTO;
import com.bukkeubook.book.manage.model.entity.AppVacationAndEmp;
import com.bukkeubook.book.manage.model.entity.CancleVacAndAppVac;
import com.bukkeubook.book.manage.model.repository.CancleVacationRepository;
import com.bukkeubook.book.manage.model.repository.EmpAnnualRepository;

@Service
public class EmpAnnualService {

	/* final 선언 및 modelMapper 빈 선언 */
	private final EmpAnnualRepository empAnnualRepository;
	private final CancleVacationRepository cancleVacationRepository;
	private final ModelMapper modelMapper;
	
	/* 필요한 의존 객체 타입에 해당하는 Bean을 찾아 주입 */
	@Autowired
	public EmpAnnualService(EmpAnnualRepository empAnnualRepository, CancleVacationRepository cancleVacationRepository, ModelMapper modelMapper) {
		this.empAnnualRepository = empAnnualRepository;
		this.cancleVacationRepository = cancleVacationRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 휴가 내역 조회 Service */
	public List<AppVacationAndEmpDTO> findRestList() {
		
		List<AppVacationAndEmp> restList = empAnnualRepository.findAll(Sort.by("vacNo"));
		
		return restList.stream().map(rest -> modelMapper.map(rest, AppVacationAndEmpDTO.class)).collect(Collectors.toUnmodifiableList());
	}
	
	/* 휴가 상세 내역 조회 */
	public AppVacationAndEmpDTO restDetailSelect(int vacNo) {
		
		AppVacationAndEmp appvacAndEmp = empAnnualRepository.findById(vacNo).get();
		
		return modelMapper.map(appvacAndEmp, AppVacationAndEmpDTO.class);
	}
	
	
	/* 휴가 취소 내역 조회 */
	public List<CancleVacAndAppVacDTO> findCancleRestList() {
		
		List<CancleVacAndAppVac> cancleRestList = cancleVacationRepository.findAllCancleRest(Sort.by("vacCancNo"));
		
		return cancleRestList.stream().map(cancleRest -> modelMapper.map(cancleRest, CancleVacAndAppVacDTO.class)).collect(Collectors.toList());
	}
		
		


}

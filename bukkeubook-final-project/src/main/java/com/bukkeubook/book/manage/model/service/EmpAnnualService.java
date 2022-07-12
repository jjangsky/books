package com.bukkeubook.book.manage.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.bukkeubook.book.manage.model.dto.joinDTO.AppVacationAndEmpDTO;
import com.bukkeubook.book.manage.model.entity.AppVacationAndEmp;
import com.bukkeubook.book.manage.model.repository.EmpAnnualRepository;

@Service
public class EmpAnnualService {

	private final EmpAnnualRepository empAnnualRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public EmpAnnualService(EmpAnnualRepository empAnnualRepository, ModelMapper modelMapper) {
		this.empAnnualRepository = empAnnualRepository;
		this.modelMapper = modelMapper;
	}

	public List<AppVacationAndEmpDTO> findRestList() {
		
		List<AppVacationAndEmp> restList = empAnnualRepository.findAll(Sort.by("vacNo"));
		
		return restList.stream().map(rest -> modelMapper.map(rest, AppVacationAndEmpDTO.class)).collect(Collectors.toUnmodifiableList());
	}


}

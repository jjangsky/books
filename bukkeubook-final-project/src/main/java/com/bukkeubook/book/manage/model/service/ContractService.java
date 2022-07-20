package com.bukkeubook.book.manage.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.manage.model.dto.joinDTO.EmpContAndEmpDTO;
import com.bukkeubook.book.manage.model.entity.EmpContAndEmp;
import com.bukkeubook.book.manage.model.repository.EmpContAndEmpRepository;


@Service
public class ContractService {
	
	private final EmpContAndEmpRepository empContAndEmpRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public ContractService(EmpContAndEmpRepository empContAndEmpRepository, ModelMapper modelMapper) {
		this.empContAndEmpRepository = empContAndEmpRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 근로계약서 내역 전체 조회 */
	public List<EmpContAndEmpDTO> findAllContracts() {
		
		List<EmpContAndEmp> contList = empContAndEmpRepository.findAll(Sort.by("contNo").descending());
		
		return contList.stream().map(list -> modelMapper.map(list, EmpContAndEmpDTO.class)).collect(Collectors.toList());
	}

	/* 근로계약서 내역 상세 조회 */
	public EmpContAndEmpDTO findDetailCont(int contNo) {
		
		EmpContAndEmp empContDetail = empContAndEmpRepository.findBycontNo(contNo);
		
		return modelMapper.map(empContDetail, EmpContAndEmpDTO.class);
	}

}

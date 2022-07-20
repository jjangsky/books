package com.bukkeubook.book.manage.model.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.manage.model.dto.ContFileDTO;
import com.bukkeubook.book.manage.model.dto.EmpContDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.EmpContAndEmpDTO;
import com.bukkeubook.book.manage.model.entity.EmpCont;
import com.bukkeubook.book.manage.model.entity.EmpContAndEmp;
import com.bukkeubook.book.manage.model.entity.LaborContFile;
import com.bukkeubook.book.manage.model.repository.EmpContAndEmpRepository;
import com.bukkeubook.book.manage.model.repository.EmpContRepository;
import com.bukkeubook.book.manage.model.repository.LaborContFileRepository;


@Service
public class ContractService {
	
	private final EmpContAndEmpRepository empContAndEmpRepository;
	private final EmpContRepository empContRepository;
	private final LaborContFileRepository laborContFileRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public ContractService(EmpContAndEmpRepository empContAndEmpRepository, ModelMapper modelMapper, EmpContRepository empContRepository, LaborContFileRepository laborContFileRepository) {
		this.empContAndEmpRepository = empContAndEmpRepository;
		this.empContRepository = empContRepository;
		this.laborContFileRepository = laborContFileRepository;
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
	
	/* 근로계약서 테이블 insert */
	@Transactional
	public void registNewContract(EmpContDTO empCont) {
		
		empContRepository.save(modelMapper.map(empCont, EmpCont.class));
		
	}
	
	/* 근로계약서 파일 업로드 */
	@Transactional
	public void registNewFile(ContFileDTO contFile) {
		
		laborContFileRepository.save(modelMapper.map(contFile, LaborContFile.class));
		
	}
	
	/* 근로계약서 내역 삭제 */
	@Transactional
	public void deleteCont(int No) {
		
		empContRepository.deleteByContNo(No);
		
	}

}

package com.bukkeubook.book.manage.model.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.books.model.dto.BookDTO;
import com.bukkeubook.book.manage.model.dto.EmpDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.EmpAndDeptDTO;
import com.bukkeubook.book.manage.model.entity.Emp;
import com.bukkeubook.book.manage.model.entity.EmpAndDept;
import com.bukkeubook.book.manage.model.repository.EmpRepository;

@Service
public class EmpService {
	
	private final EmpRepository empRepository;
	private final ModelMapper modelMapper;		//앤티티를 디티오로 변환 or 디티오를 엔티티로 변환

	@Autowired
	public EmpService(EmpRepository empRepository, ModelMapper modelMapper) {
		this.empRepository = empRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 사원조회 */
	public List<EmpAndDeptDTO> findEmpList() {
		
		List<EmpAndDept> empList = empRepository.findAll(Sort.by("empNo"));   //List<Emp>  = 앤티티 담기
		return empList.stream().map(emp -> modelMapper.map(emp, EmpAndDeptDTO.class)).toList(); 
	}
	
	/* 퇴사 사원 조회 */
	public List<EmpAndDeptDTO> findLeaveEmpList(String empEndYn) {
		List<EmpAndDept> leaveEmpList = empRepository.findByEmpEndYn(empEndYn);   //List<Emp>  = 앤티티 담기
		return leaveEmpList.stream().map(emp -> modelMapper.map(emp, EmpAndDeptDTO.class)).toList(); 
	}


	

	

	

}

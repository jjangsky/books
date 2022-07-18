package com.bukkeubook.book.mypage.model.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bukkeubook.book.manage.model.dto.EmpDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.EmpAndDeptDTO;
import com.bukkeubook.book.manage.model.entity.EmpAndDept;
import com.bukkeubook.book.mypage.model.repository.EmployeeRepository;

@Service
public class MyInfoModifyService {
	
	private final EmployeeRepository employeeRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public MyInfoModifyService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 마이페이지 개인정보 수정 화면 이동 */
	public EmpAndDeptDTO findMyInfo(int memberCode) {
		
		EmpAndDept myInfo = employeeRepository.findById(memberCode).get();
		
		return modelMapper.map(myInfo, EmpAndDeptDTO.class);
	}

	/* 마이페이지 개인정보 수정하기 */
	@Transactional
	public void modifyInfoEmp(int memberCode, EmpDTO emp) {
		
		
		EmpAndDept myInfo = employeeRepository.findById(memberCode).get();
		myInfo.setEmpEmail(emp.getEmpEmail());
		myInfo.setEmpAddress(emp.getEmpAddress());
		myInfo.setEmpDAddress(emp.getEmpDAddress());
		myInfo.setEmpPhone1(emp.getEmpPhone1());
		myInfo.setEmpPhone2(emp.getEmpPhone2());
		myInfo.setEmpPhone3(emp.getEmpPhone3());
		
	}
	

}

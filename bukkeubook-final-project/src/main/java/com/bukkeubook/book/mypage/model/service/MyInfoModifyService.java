package com.bukkeubook.book.mypage.model.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	

}

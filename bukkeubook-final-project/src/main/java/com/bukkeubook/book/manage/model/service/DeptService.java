package com.bukkeubook.book.manage.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.manage.model.dto.DeptDTO;
import com.bukkeubook.book.manage.model.entity.Dept;
import com.bukkeubook.book.manage.model.repository.DeptRepository;

@Service
public class DeptService {
	
	private final DeptRepository deptRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public DeptService(DeptRepository deptRepository, ModelMapper modelMapper) {
		this.deptRepository = deptRepository;
		this.modelMapper = modelMapper;
	}

	public List<DeptDTO> selectDeptList() {

		List<Dept> deptList = new ArrayList<Dept>();
		
		deptList = deptRepository.findAll();
		
		return deptList.stream().map(Dept -> modelMapper.map(Dept, DeptDTO.class)).collect(Collectors.toList());
	}
}

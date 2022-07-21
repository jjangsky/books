package com.bukkeubook.book.manage.model.service;

import java.sql.Date;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.manage.model.dto.joinDTO.AttendAndEmpDTO;
import com.bukkeubook.book.manage.model.entity.AttendAndEmp;
import com.bukkeubook.book.manage.model.repository.AttendAndEmpRepository;

@Service
public class AttendManageService {
	
	private final AttendAndEmpRepository attendAndEmpRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public AttendManageService(AttendAndEmpRepository attendAndEmpRepository, ModelMapper modelMapper) {
		this.attendAndEmpRepository = attendAndEmpRepository;
		this.modelMapper = modelMapper;
		}
	
	/* 전체 사원의 출근 기록 조회 */
	public List<AttendAndEmpDTO> findAllAttendList() {
		
		List<AttendAndEmp> attendList = attendAndEmpRepository.findAll(Sort.by("attNo").descending());
		
		return attendList.stream().map(list -> modelMapper.map(list, AttendAndEmpDTO.class)).collect(Collectors.toList());
	}
	
	/* 페이징 처리 갯수 구하기 */
	public int selectTotalCount(String searchCondition, String searchValue, Date startDate, Date endDate) {
		
		int count = 0;
			if(searchValue != null && startDate != null && endDate != null) {
				if(startDate != null & endDate !=null) {
					if("empNo".equals(searchCondition)) {
						count = attendAndEmpRepository.countByEmp_EmpNoAndAttDateBetween(searchValue, startDate, endDate);
						}
					if("empName".equals(searchCondition)) {
						count = attendAndEmpRepository.countByEmp_EmpNameAndAttDateBetween(searchValue, startDate, endDate);
						}
				}if("empNo".equals(searchCondition)) {
					count = attendAndEmpRepository.countByEmp_EmpNoContaining(searchValue);
					}
				 if("empName".equals(searchCondition)) {
					count = attendAndEmpRepository.countByEmp_EmpNameContaining(searchValue);
					}
			}else {
				count = (int)attendAndEmpRepository.count();
			}
				
		
		return count;
	}

}

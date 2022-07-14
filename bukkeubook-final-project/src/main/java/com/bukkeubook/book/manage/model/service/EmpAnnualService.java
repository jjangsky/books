package com.bukkeubook.book.manage.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.books.model.dto.BookDTO;
import com.bukkeubook.book.books.model.entity.Book;
import com.bukkeubook.book.common.paging.SelectCriteria;
import com.bukkeubook.book.manage.model.dto.joinDTO.AppVacationAndEmpDTO;
import com.bukkeubook.book.manage.model.entity.AppVacationAndEmp;
import com.bukkeubook.book.manage.model.repository.EmpAnnualRepository;

@Service
public class EmpAnnualService {

	/* final 선언 및 modelMapper 빈 선언 */
	private final EmpAnnualRepository empAnnualRepository;
	private final ModelMapper modelMapper;
	
	/* 필요한 의존 객체 타입에 해당하는 Bean을 찾아 주입 */
	@Autowired
	public EmpAnnualService(EmpAnnualRepository empAnnualRepository, ModelMapper modelMapper) {
		this.empAnnualRepository = empAnnualRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 휴가 내역 조회 Service */
//	public List<AppVacationAndEmpDTO> findRestList() {
//		
//		List<AppVacationAndEmp> restList = empAnnualRepository.findAll();
//		
//		return restList.stream().map(rest -> modelMapper.map(rest, AppVacationAndEmpDTO.class)).collect(Collectors.toList());
//	}
	

	public int selectTotalCount(String searchCondition, String searchValue) {

		int count = 0;
		if(searchValue != null) {
			if("deptName".equals(searchCondition)) {
				count = empAnnualRepository.countByDeptNameContaining(searchValue);
			}

			if("empNo".equals(searchCondition)) {
				count = empAnnualRepository.countByEmpNoContaining(searchValue);
			}
			
			if("empName".equals(searchCondition)) {
				count = empAnnualRepository.countByEmpNameContaining(searchValue);
			}
		} else {
			count = (int)empAnnualRepository.count();
		}

		return count;
	}


	public List<AppVacationAndEmpDTO> findRestList(SelectCriteria selectCriteria) {

		int index = selectCriteria.getPageNo() - 1;
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		Pageable paging = PageRequest.of(index, count, Sort.by("vacNo"));

		List<AppVacationAndEmp> restList = new ArrayList<AppVacationAndEmp>();
		if(searchValue != null) {

			if("deptName".equals(selectCriteria.getSearchCondition())) {
				restList = empAnnualRepository.findByDeptNameContaining(selectCriteria.getSearchValue(), paging);
			}

			if("empNo".equals(selectCriteria.getSearchCondition())) {
				restList = empAnnualRepository.findByEmpNoContaining(selectCriteria.getSearchValue(), paging);
			}
			
			if("empName".equals(selectCriteria.getSearchCondition())) {
				restList = empAnnualRepository.findByEmpNameContaining(selectCriteria.getSearchValue(), paging);
			}
		} else {
			restList = empAnnualRepository.findAll(paging).toList();
		}

		/* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return restList.stream().map(rest -> modelMapper.map(rest, AppVacationAndEmpDTO.class)).collect(Collectors.toList());
	}

	/* 휴가 상세 내역 조회 */
	public AppVacationAndEmpDTO restDetailSelect(int vacNo) {
		
		AppVacationAndEmp appvacAndEmp = empAnnualRepository.findById(vacNo).get();
		
		return modelMapper.map(appvacAndEmp, AppVacationAndEmpDTO.class);
	}



}

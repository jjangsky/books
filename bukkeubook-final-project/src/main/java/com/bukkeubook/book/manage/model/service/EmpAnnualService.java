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

import com.bukkeubook.book.books.model.entity.Book;
import com.bukkeubook.book.common.paging.SelectCriteria;
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
	
	public int selectTotalCount(String searchCondition, String searchValue) {

		int count = 0;
		if(searchValue != null) {
			if("Dept".equals(searchCondition)) {
				count = empAnnualRepository.countByemp_Dept_DeptNameContaining(searchValue);
			}

//			if("EmpNo".equals(searchCondition)) {
//				count = empAnnualRepository.countByEmp_EmpNoContaining(searchValue);
//			}
			
			if("EmpName".equals(searchCondition)) {
				count = empAnnualRepository.countByEmp_EmpNameContaining(searchValue);
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

		Pageable paging = PageRequest.of(index, count);

		List<AppVacationAndEmp> restList = new ArrayList<AppVacationAndEmp>();
		
		if(searchValue != null) {

			if("Dept".equals(selectCriteria.getSearchCondition())) {
				restList = empAnnualRepository.findByemp_Dept_DeptNameContaining(selectCriteria.getSearchValue(), paging);
			}

//			if("EmpNo".equals(selectCriteria.getSearchCondition())) {
//				restList = empAnnualRepository.findByEmp_EmpNoContaining((int)Integer.valueOf(selectCriteria.getSearchValue()), paging);
//			}
			
			if("EmpName".equals(selectCriteria.getSearchCondition())) {
				restList = empAnnualRepository.findByEmp_EmpNameContaining(selectCriteria.getSearchValue(), paging);
			}
		} else {
			restList = empAnnualRepository.findAll(paging).toList();
		}
		
		return restList.stream().map(rest -> modelMapper.map(rest, AppVacationAndEmpDTO.class)).collect(Collectors.toUnmodifiableList());
	}


}

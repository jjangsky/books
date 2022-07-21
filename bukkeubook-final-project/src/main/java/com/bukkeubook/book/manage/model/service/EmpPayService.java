package com.bukkeubook.book.manage.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.common.paging.SelectCriteria;
import com.bukkeubook.book.manage.model.dto.EmpDTO;
import com.bukkeubook.book.manage.model.dto.SalaryDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.PayAndEmpAndDeptDTO;
import com.bukkeubook.book.manage.model.entity.PayAndEmpAndDept;
import com.bukkeubook.book.manage.model.entity.Salary;
import com.bukkeubook.book.manage.model.repository.EmpPayRepository;


@Service
public class EmpPayService {
   
   private final ModelMapper modelMapper;
   private final EmpPayRepository empPayRepository;
   
   @Autowired
   public EmpPayService(EmpPayRepository empPayRepository, ModelMapper modelMapper) {
      this.empPayRepository = empPayRepository;
      this.modelMapper = modelMapper;
   }
   
   
   /* 급여계산내역 조회 & 검색기능 & 페이징 */
	public int selectTotalCount(String searchCondition, String searchValue) {

		int count = 0;
		if(searchValue != null) {

//			if("empName".equals(searchCondition)) {
//				count = empPayRepository.countByEmpNameContaining(searchValue);
//			}
			
			if("salMonth".equals(searchCondition)) {
				count = empPayRepository.countBySalMonthContaining(searchValue);
			}

		} else {
			count = (int)empPayRepository.count();
		}

		return count;
	}
	
	public List<PayAndEmpAndDeptDTO> searchPayList(SelectCriteria selectCriteria) {

		int index = selectCriteria.getPageNo() - 1;
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		Pageable paging = PageRequest.of(index, count, Sort.by("salNo"));

		List<PayAndEmpAndDept> payList = new ArrayList<PayAndEmpAndDept>();
		if(searchValue != null) {

//			if("empName".equals(selectCriteria.getSearchCondition())) {
//				payList = empPayRepository.findByEmpNameContaining(selectCriteria.getSearchValue(), paging);
//			}
			
			if("salMonth".equals(selectCriteria.getSearchCondition())) {
				payList = empPayRepository.findBySalMonthContaining(selectCriteria.getSearchValue(), paging);
			}
		} else {
			payList = empPayRepository.findAll(paging).toList();
		}
		
		/* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return payList.stream().map(salary -> modelMapper.map(salary, PayAndEmpAndDeptDTO.class)).collect(Collectors.toList());
	}
	
	/* 상세 급여지급내역 조회 */
	public PayAndEmpAndDeptDTO searchEmpPayDetail(int salNo) {
		
		PayAndEmpAndDept pay = empPayRepository.findById(salNo).get();
		
		return modelMapper.map(pay, PayAndEmpAndDeptDTO.class);
				
	}

	/* 급여계산내역 수정 페이지 연결*/
	public PayAndEmpAndDeptDTO findEmpPayBySalNo(int salNo) {
		
		PayAndEmpAndDept pay = empPayRepository.findById(salNo).get();

		return modelMapper.map(pay, PayAndEmpAndDeptDTO.class);
		
	}
  
	

		












}



package com.bukkeubook.book.manage.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.common.paging.SelectCriteria;
import com.bukkeubook.book.manage.model.dto.joinDTO.AppVacationAndEmpDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.CancelVacationAndAppVacationDTO;
import com.bukkeubook.book.manage.model.entity.AppVacationAndEmp;
import com.bukkeubook.book.manage.model.entity.CancelVacationAndAppVacation;
import com.bukkeubook.book.manage.model.repository.CancelVacRepository;
import com.bukkeubook.book.manage.model.repository.EmpAnnualRepository;

@Service
public class EmpAnnualService {

   private final EmpAnnualRepository empAnnualRepository;
   private final CancelVacRepository cancelVacRepository;
   private final ModelMapper modelMapper;

   
   @Autowired
   public EmpAnnualService(EmpAnnualRepository empAnnualRepository, CancelVacRepository cancelVacRepository, ModelMapper modelMapper) {
      this.empAnnualRepository = empAnnualRepository;
      this.cancelVacRepository = cancelVacRepository;
      this.modelMapper = modelMapper;
   }
   
   public int selectTotalCount(String searchCondition, String searchValue) {

      int count = 0;
      if(searchValue != null) {
         if("Dept".equals(searchCondition)) {
            count = empAnnualRepository.countByemp_Dept_DeptNameContaining(searchValue);
         }

//         if("EmpNo".equals(searchCondition)) {
//            count = empAnnualRepository.countByEmp_EmpNoContaining(Integer.valueOf(searchValue));
//         }
         
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

//         if("EmpNo".equals(selectCriteria.getSearchCondition())) {
//            restList = empAnnualRepository.findByEmp_EmpNoContaining(Integer.valueOf(selectCriteria.getSearchValue()), paging);
//         }
         
         if("EmpName".equals(selectCriteria.getSearchCondition())) {
            restList = empAnnualRepository.findByEmp_EmpNameContaining(selectCriteria.getSearchValue(), paging);
         }
      } else {
         restList = empAnnualRepository.findAll(paging).toList();
      }
      
      return restList.stream().map(rest -> modelMapper.map(rest, AppVacationAndEmpDTO.class)).collect(Collectors.toUnmodifiableList());
   }
   
	/* 휴가 상세 내역 조회 */
	public AppVacationAndEmpDTO restDetailSelect(int vacNo) {
		
		AppVacationAndEmp appvacAndEmp = empAnnualRepository.findById(vacNo).get();
		
		return modelMapper.map(appvacAndEmp, AppVacationAndEmpDTO.class);
	}

	/******************************************************************************************/
	/* 휴가 취소 신청 조회 */
	public int selectCancelTotalCount(String searchCondition, String searchValue) {

	      int count = 0;
	      if(searchValue != null) {
	         if("Dept".equals(searchCondition)) {
	            count = cancelVacRepository.countByaddvacEmp_emp_dept_deptNameContaining(searchValue);
	         }

//	         if("EmpNo".equals(searchCondition)) {
//	            count = cancelVacRepository.countByaddvacEmp_Emp_EmpNoContaining(searchValue);
//	         }
	         
	         if("EmpName".equals(searchCondition)) {
	            count = cancelVacRepository.countByaddvacEmp_emp_empNameContaining(searchValue);
	         }
	      } else {
	         count = (int)cancelVacRepository.count();
	      }

	      return count;
	}
	
	public List<CancelVacationAndAppVacationDTO> findCancelRestList(SelectCriteria selectCriteria) {
	     
	      int index = selectCriteria.getPageNo() - 1;
	      int count = selectCriteria.getLimit();
	      String searchValue = selectCriteria.getSearchValue();

	      Pageable paging = PageRequest.of(index, count);

	      List<CancelVacationAndAppVacation> cancelVacList = new ArrayList<CancelVacationAndAppVacation>();
	      
	      if(searchValue != null) {

	         if("Dept".equals(selectCriteria.getSearchCondition())) {
	        	 cancelVacList = cancelVacRepository.findByaddvacEmp_emp_dept_deptNameContaining(selectCriteria.getSearchValue(), paging);
	         }

//	         if("EmpNo".equals(selectCriteria.getSearchCondition())) {
//	            restList = empAnnualRepository.findByaddvacEmp_Emp_EmpNoContaining((int)Integer.valueOf(selectCriteria.getSearchValue()), paging);
//	         }
	         
	         if("EmpName".equals(selectCriteria.getSearchCondition())) {
	        	 cancelVacList = cancelVacRepository.findByaddvacEmp_emp_empNameContaining(selectCriteria.getSearchValue(), paging);
	         }
	      } else {
	    	  cancelVacList = cancelVacRepository.findAll(paging).toList();
	      }
	      
	      return cancelVacList.stream().map(cancelVac -> modelMapper.map(cancelVac, CancelVacationAndAppVacationDTO.class)).collect(Collectors.toUnmodifiableList());
	   }



}
package com.bukkeubook.book.manage.model.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.manage.model.dto.joinDTO.DayOffAndEmpAndDeptDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.EmpAndDeptDTO;
import com.bukkeubook.book.manage.model.entity.DayOffAndEmpAndDept;
import com.bukkeubook.book.manage.model.entity.EmpAndDept;
import com.bukkeubook.book.manage.model.repository.EmpDayOffRepository;

@Service
public class EmpDayOffService {
   
   private final EmpDayOffRepository empDayOffRepository;
   private final ModelMapper modelMapper;
   
   @Autowired
   public EmpDayOffService(EmpDayOffRepository empDayOffRepository, ModelMapper modelMapper) {
      this.empDayOffRepository = empDayOffRepository;
      this.modelMapper = modelMapper;
   }
   
   
   /* 직원 연차조회 */
   public List<DayOffAndEmpAndDeptDTO> findDayOffList() {
      
      List<DayOffAndEmpAndDept> annualList = empDayOffRepository.findAll(Sort.by("doffNo"));
      
      return annualList.stream().map(dayOff -> modelMapper.map(dayOff, DayOffAndEmpAndDeptDTO.class)).toList();
   }

   /* 직원 연차상세조회 */
   public DayOffAndEmpAndDeptDTO searchEmpDayOffDetail(int empNo) {
	   
	   DayOffAndEmpAndDept emp = empDayOffRepository.findById(empNo).get();
		
		System.out.println("레포지토리      " + emp);
		
		return modelMapper.map(emp, DayOffAndEmpAndDeptDTO.class); //앤티티를 넣어달라고 요청 -> modelMapper
}


}
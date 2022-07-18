package com.bukkeubook.book.manage.model.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bukkeubook.book.manage.model.dto.AppVacationDTO;
import com.bukkeubook.book.manage.model.dto.DayOffDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.DayOffAndEmpAndDeptDTO;
import com.bukkeubook.book.manage.model.entity.AppVacation;
import com.bukkeubook.book.manage.model.entity.DayOff;
import com.bukkeubook.book.manage.model.entity.DayOffAndEmpAndDept;
import com.bukkeubook.book.manage.model.repository.DayOffRepository2;
import com.bukkeubook.book.manage.model.repository.EmpAnnualRepository;
import com.bukkeubook.book.manage.model.repository.EmpDayOffRepository;
import com.bukkeubook.book.mypage.model.repository.DayOffRepository;

@Service
public class EmpDayOffService {
   
   private final EmpDayOffRepository empDayOffRepository;
   private final EmpAnnualRepository empAnnualRepository;
   private final DayOffRepository2 dayOffRepository2;
   private final ModelMapper modelMapper;
   
   @Autowired
   public EmpDayOffService(EmpDayOffRepository empDayOffRepository, EmpAnnualRepository empAnnualRepository, DayOffRepository2 dayOffRepository2, ModelMapper modelMapper) {
      this.empDayOffRepository = empDayOffRepository;
      this.empAnnualRepository = empAnnualRepository;
      this.dayOffRepository2 = dayOffRepository2;
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
   
   /* 휴가 신청이랑 연결된 트랜잭션 */
   @Transactional
	public List<DayOffDTO> findDayOffByNo(int empNo) {
	   
		List<DayOff> dayOffList = dayOffRepository2.findAllByEmpNo(empNo);
		
		System.out.println("아아아아아아아아아아아아아" + dayOffList);
		System.out.println("아아아아아아아아아아아아아" + dayOffList);
		System.out.println("아아아아아아아아아아아아아" + dayOffList);
		System.out.println("아아아아아아아아아아아아아" + dayOffList);
		System.out.println("아아아아아아아아아아아아아" + dayOffList);
		System.out.println("아아아아아아아아아아아아아" + dayOffList);
		System.out.println("아아아아아아아아아아아아아" + dayOffList);
		System.out.println("아아아아아아아아아아아아아" + dayOffList);
		System.out.println("아아아아아아아아아아아아아" + dayOffList);
		System.out.println("아아아아아아아아아아아아아" + dayOffList);
		System.out.println("아아아아아아아아아아아아아" + dayOffList);
		System.out.println("아아아아아아아아아아아아아" + dayOffList);
		System.out.println("아아아아아아아아아아아아아" + dayOffList);

		return dayOffList.stream().map(dayOff -> modelMapper.map(dayOff, DayOffDTO.class)).collect(Collectors.toList());
	}
   
//   @Transactional
//	public void modifyDayOffInfo(DayOffDTO dayOffDTO, @ModelAttribute AppVacationDTO appVacationDTO) {
//
//	   dayOffRepository.modifyDayOffInfo(modelMapper.map(dayOffDTO, DayOff.class));
//	   
//	   AppVacation appVac2 = empAnnualRepository.findAppVacByVacNo(appVacationDTO.getVacNo());
//	   appVac2.setVacStartDate(appVac2.getVacStartDate());
//	   appVac2.setVacEndDate(appVac2.getVacEndDate());
//	   
//	   System.out.println("출력되니?" + appVac2);
//	   // 사용한 연차일 수를 구한다.(long)
//	   
//	   DayOff dayOff2 = dayOffRepository.findDayOffByEmpNo(dayOffDTO.getEmpNo());
//	   dayOff2.setDoffRemain(dayOff2.getDoffRemain());
//	   dayOff2.setDoffAmount(dayOff2.getDoffAmount()- 1); // 사용한 연차일 수를 뺀다 update
//	   dayOff2.setDoffUse(dayOff2.getDoffUse() + 1);
//
//	      
//	}






}
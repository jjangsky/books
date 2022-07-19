package com.bukkeubook.book.manage.model.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.manage.model.dto.AppVacationDTO;
import com.bukkeubook.book.manage.model.dto.DayOffDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.DayOffAndEmpAndDeptDTO;
import com.bukkeubook.book.manage.model.entity.AppVacation;
import com.bukkeubook.book.manage.model.entity.DayOff;
import com.bukkeubook.book.manage.model.entity.DayOffAndEmpAndDept;
import com.bukkeubook.book.manage.model.repository.AppVacRepository;
import com.bukkeubook.book.manage.model.repository.DayOffRepository2;
import com.bukkeubook.book.manage.model.repository.EmpDayOffRepository;

@Service
public class EmpDayOffService {
   
   private final EmpDayOffRepository empDayOffRepository;
   private final DayOffRepository2 dayOffRepository2;
   private final AppVacRepository appVacRepository;
   private final ModelMapper modelMapper;
   
   @Autowired
   public EmpDayOffService(EmpDayOffRepository empDayOffRepository, DayOffRepository2 dayOffRepository2, AppVacRepository appVacRepository, ModelMapper modelMapper) {
      this.empDayOffRepository = empDayOffRepository;
      this.dayOffRepository2 = dayOffRepository2;
      this.appVacRepository = appVacRepository;
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
  
	/****************************************************************/
   /* 휴가 신청이랑 연결된 트랜잭션 */
	@Transactional
	public List<AppVacationDTO> findAppVacByEmpNo (int vacNo) {
		List<AppVacation> appVacList = appVacRepository.findAppVacByNo(vacNo);
		return appVacList.stream().map(appVac -> modelMapper.map(appVac, AppVacationDTO.class)).collect(Collectors.toList());
	}

   @Transactional
	public void modifyDayOffInfo(DayOffDTO dayOffDTO) {
		
	   DayOff dayOff = dayOffRepository2.findByEmpNo(dayOffDTO.getEmpNo());
	   dayOff.setDoffAmount(dayOffDTO.getDoffAmount());		// 총 연차 횟수	
	   dayOff.setDoffRemain(dayOff.getDoffRemain());		// 잔여 연차 횟수
	   dayOff.setDoffUse(dayOffDTO.getDoffUse());			// 사용 연차 횟수
	   
	   System.out.println("^____________________^" + dayOff);
	}
	/****************************************************************/
}



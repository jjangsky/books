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
import com.bukkeubook.book.manage.model.dto.joinDTO.AppVacationAndEmpDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.DayOffAndEmpAndDeptDTO;
import com.bukkeubook.book.manage.model.entity.AppVacation;
import com.bukkeubook.book.manage.model.entity.AppVacationAndEmp;
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
   public List<AppVacationAndEmpDTO> findAppVacByVacNo(int vacNo) {
	   
	   List<AppVacationAndEmp> appVacList = appVacRepository.findAppVacByVacNo(vacNo);
	   return appVacList.stream().map(appVac -> modelMapper.map(appVacList, AppVacationAndEmpDTO.class)).collect(Collectors.toList());
	   
	   }

	/****************************************************************/	
	@Transactional
	public DayOffAndEmpAndDeptDTO findByEmpNo(int empNo, int doffNo) {
		DayOffAndEmpAndDept dayOffList = dayOffRepository2.findByEmpNo(empNo);
		int nowDayOffAmount = dayOffList.getDoffAmount();
		int nowDayOffRemain = dayOffList.getDoffRemain();
		int nowDayOffUse = dayOffList.getDoffUse();
		dayOffList.setDoffRemain(nowDayOffAmount - nowDayOffUse);
		
		return modelMapper.map(dayOffList, DayOffAndEmpAndDeptDTO.class);
	}

//  empNo          사원번호
//  doffAmount     연차횟수
//  doffRemain     잔여연차횟수
//  doffUse        사용연차횟수
	
	@Transactional
	public void findDayOffEmpNo(int empNo, int doffAmount, int doffRemain, int doffUse) {
		List<AppVacationAndEmp> appVac = appVacRepository.findAppVacByVacNo(empNo);
		java.sql.Date vacStartDate = appVac.get(0).getVacStartDate();
		java.sql.Date vacEndDate = appVac.get(0).getVacEndDate();
		
		long vacStartTime = vacStartDate.getTime();
		long vacEndTime = vacEndDate.getTime();
		long test = (vacEndTime - vacStartTime);
		long day = test * 3600 * 24 * 1000;
		
		System.out.println("nsdjkfhwsafbjks;hi;jsdfnlsk'" + day);
	}
	
}



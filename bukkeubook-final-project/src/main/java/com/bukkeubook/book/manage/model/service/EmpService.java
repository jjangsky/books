package com.bukkeubook.book.manage.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bukkeubook.book.common.paging.SelectCriteria;
import com.bukkeubook.book.manage.model.dto.EmpDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.EmpAndDeptDTO;
import com.bukkeubook.book.manage.model.entity.Emp;
import com.bukkeubook.book.manage.model.entity.EmpAndDept;
import com.bukkeubook.book.manage.model.repository.EmpRepository;
import com.bukkeubook.book.manage.model.repository.OriginalEmpRepository;

@Service
public class EmpService {
	
	private final EmpRepository empRepository;
	private final OriginalEmpRepository originEmpRepository;
	private final ModelMapper modelMapper;		//앤티티를 디티오로 변환 or 디티오를 엔티티로 변환

	@Autowired
	public EmpService(EmpRepository empRepository, ModelMapper modelMapper,OriginalEmpRepository originEmpRepository) {
		this.empRepository = empRepository;
		this.modelMapper = modelMapper;
		this.originEmpRepository = originEmpRepository;
	}

	/* 사원조회 & 검색기능 & 페이징 */
	public int selectTotalCount(String searchCondition, String searchValue) {

		int count = 0;
		if(searchValue != null) {

			if("empName".equals(searchCondition)) {
				count = empRepository.countByEmpNameContaining(searchValue);
			}
			
			if("empJobCode".equals(searchCondition)) {
				count = empRepository.countByempJobCodeContaining(searchValue);
			}

		} else {
			count = (int)empRepository.count();
		}

		return count;
	}
	
	public List<EmpAndDeptDTO> searchEmpList(SelectCriteria selectCriteria) {

		int index = selectCriteria.getPageNo() - 1;
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		Pageable paging = PageRequest.of(index, count, Sort.by("empNo"));

		List<EmpAndDept> empList = new ArrayList<EmpAndDept>();
		if(searchValue != null) {

			if("empName".equals(selectCriteria.getSearchCondition())) {
				empList = empRepository.findByEmpNameContaining(selectCriteria.getSearchValue(), paging);
			}
			
			if("empJobCode".equals(selectCriteria.getSearchCondition())) {
				empList = empRepository.findByEmpJobCodeContaining(selectCriteria.getSearchValue(), paging);
			}
		} else {
			empList = empRepository.findAll(paging).toList();
		}
		
		/* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return empList.stream().map(emp -> modelMapper.map(emp, EmpAndDeptDTO.class)).collect(Collectors.toList());
	}
		
	/* 퇴사 사원 조회 */
	public List<EmpAndDeptDTO> findLeaveEmpList(String empEndYn) {
		List<EmpAndDept> leaveEmpList = empRepository.findByEmpEndYn(empEndYn);   // List<Emp> = 엔티티 담기
		return leaveEmpList.stream().map(emp -> modelMapper.map(emp, EmpAndDeptDTO.class)).toList(); 
	}
	
	/* 사원 상세조회 */
	public EmpAndDeptDTO searchEmpDetail(int empNo) {
		
		EmpAndDept emp = empRepository.findById(empNo).get();
		
		System.out.println("레포지토리      " + emp);
		
		return modelMapper.map(emp, EmpAndDeptDTO.class); // 엔티티를 넣어달라고 요청 -> modelMapper
	}
	
	/* 퇴사사원 상세조회 */
	public EmpAndDeptDTO searchLeaveEmpDetail(int empNo) {
		
		EmpAndDept emp = empRepository.findById(empNo).get();
		
		System.out.println("레포지토리      " + emp);
		
		return modelMapper.map(emp, EmpAndDeptDTO.class); // 엔티티를 넣어달라고 요청 -> modelMapper
	}

	/* 신규 사원 등록 */
	@Transactional
	public void insertNewEmp(EmpDTO emp) {
		
		System.out.println("Service                      sssssssssssss" + emp);
		
		originEmpRepository.save(modelMapper.map(emp, Emp.class));
	}
	
	/* 사원정보 수정 */
	public EmpDTO findEmpByEmpNo(int empNo) {
		System.out.println("확인1111111111111");

		Emp emp = originEmpRepository.findById(empNo).get();
		System.out.println("확인222222222222222");

		System.out.println("레포지토리      " + emp);
		
		return modelMapper.map(emp, EmpDTO.class); //앤티티를 넣어달라고 요청 -> modelMapper
	}

	/* 프로필 사진 등록 */
//	@Transactional
//	public void inputProfile(ProfPhotoDTO profile) {
//		
//		empAndProfileRepository.input(modelMapper.map(profile, ProfPhotoAndEmp.class));
//	}
	
	@Transactional
	public void modifyEmp(EmpDTO emp) {
	
		Emp foundemp = originEmpRepository.findById(emp.getEmpNo()).get();
	
		foundemp.setEmpName(emp.getEmpName());
		foundemp.setDeptCode(emp.getDeptCode());
		foundemp.setEmpJobCode(emp.getEmpJobCode());
		foundemp.setEmpPhone1(emp.getEmpPhone1());
		foundemp.setEmpPhone2(emp.getEmpPhone2());
		foundemp.setEmpPhone3(emp.getEmpPhone3());
		foundemp.setEmpEmail(emp.getEmpEmail());
		foundemp.setEmpAddress(emp.getEmpAddress());
		foundemp.setEmpDAddress(emp.getEmpDAddress());
		foundemp.setEmpPwd(emp.getEmpPwd());
	
	}



}

	

	

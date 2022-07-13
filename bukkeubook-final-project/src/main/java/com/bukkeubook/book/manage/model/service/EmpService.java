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

@Service
public class EmpService {
	
	private final EmpRepository empRepository;
	private final ModelMapper modelMapper;		//앤티티를 디티오로 변환 or 디티오를 엔티티로 변환

	@Autowired
	public EmpService(EmpRepository empRepository, ModelMapper modelMapper) {
		this.empRepository = empRepository;
		this.modelMapper = modelMapper;
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
		List<EmpAndDept> leaveEmpList = empRepository.findByEmpEndYn(empEndYn);   //List<Emp>  = 앤티티 담기
		return leaveEmpList.stream().map(emp -> modelMapper.map(emp, EmpAndDeptDTO.class)).toList(); 
	}
	
	/* 사원 상세조회 */
	public EmpAndDeptDTO searchEmpDetail(int empNo) {
		
		EmpAndDept emp = empRepository.findById(empNo).get();
		
		return modelMapper.map(empNo, EmpAndDeptDTO.class);
	}
	
}


	

	

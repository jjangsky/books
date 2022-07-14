package com.bukkeubook.book.manage.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bukkeubook.book.manage.model.dto.joinDTO.AppVacationAndEmpDTO;
import com.bukkeubook.book.manage.model.entity.AppVacationAndEmp;

@Repository
public interface EmpAnnualRepository extends JpaRepository<AppVacationAndEmp, Integer> {
	
	/* 휴가 신청 조회 */
	List<AppVacationAndEmp> findAll();

	/* 검색기능 & 페이징처리 */	

	List<AppVacationAndEmp> findByDeptNameContaining(String searchValue, Pageable paging);

	List<AppVacationAndEmp> findByEmpNoContaining(String searchValue, Pageable paging);

	List<AppVacationAndEmp> findByEmpNameContaining(String searchValue, Pageable paging);

	int countByDeptNameContaining(String searchValue);

	int countByEmpNoContaining(String searchValue);

	int countByEmpNameContaining(String searchValue);


	

		
}

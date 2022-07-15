package com.bukkeubook.book.manage.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bukkeubook.book.manage.model.entity.AppVacationAndEmp;

@Repository
public interface EmpAnnualRepository extends JpaRepository<AppVacationAndEmp, Integer> {

	List<AppVacationAndEmp> findAll(Sort by);

	int countByemp_Dept_DeptNameContaining(String searchValue);

//	int countByEmp_EmpNoContaining(String searchValue);
	
	int countByEmp_EmpNameContaining(String searchValue);

	List<AppVacationAndEmp> findByemp_Dept_DeptNameContaining(String searchValue, Pageable paging);

//	List<AppVacationAndEmp> findByEmp_EmpNoContaining(int searchValue, Pageable paging);

	List<AppVacationAndEmp> findByEmp_EmpNameContaining(String searchValue, Pageable paging);



}

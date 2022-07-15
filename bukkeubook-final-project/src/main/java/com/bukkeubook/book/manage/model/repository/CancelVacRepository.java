package com.bukkeubook.book.manage.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bukkeubook.book.manage.model.entity.CancelVacationAndAppVacation;

@Repository
public interface CancelVacRepository extends JpaRepository<CancelVacationAndAppVacation, Integer> {

	int countByaddvacEmp_emp_Dept_DeptNameContaining(String searchValue);

//	int countByaddvacEmp_Emp_EmpNoContaining(String searchValue);
	
	int countByaddvacEmp_Emp_EmpNameContaining(String searchValue);

	List<CancelVacationAndAppVacation> findByaddvacEmp_emp_Dept_DeptNameContaining(String searchValue, Pageable paging);

	List<CancelVacationAndAppVacation> findByaddvacEmp_Emp_EmpNameContaining(String searchValue, Pageable paging);
	
	




}

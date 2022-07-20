package com.bukkeubook.book.manage.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bukkeubook.book.manage.model.entity.CancelVacationAndAppVacation;

@Repository
public interface CancelVacRepository extends JpaRepository<CancelVacationAndAppVacation, Integer> {

	int countByappvacEmp_emp_dept_deptNameContaining(String searchValue);

	int countByappvacEmp_emp_empNameContaining(String searchValue);

	List<CancelVacationAndAppVacation> findByappvacEmp_emp_dept_deptNameContaining(String searchValue, Pageable paging);

	List<CancelVacationAndAppVacation> findByappvacEmp_emp_empNameContaining(String searchValue, Pageable paging);


}

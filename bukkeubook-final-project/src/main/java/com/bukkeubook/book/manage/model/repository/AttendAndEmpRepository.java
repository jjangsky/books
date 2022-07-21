package com.bukkeubook.book.manage.model.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bukkeubook.book.manage.model.entity.AttendAndEmp;


public interface AttendAndEmpRepository extends JpaRepository<AttendAndEmp, Integer> {

	int countByEmp_EmpNoAndAttDateBetween(String searchValue, Date startDate, Date endDate);

	int countByEmp_EmpNameAndAttDateBetween(String searchValue, Date startDate, Date endDate);

	int countByEmp_EmpNoContaining(String searchValue);

	int countByEmp_EmpNameContaining(String searchValue);

}

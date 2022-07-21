package com.bukkeubook.book.manage.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import com.bukkeubook.book.manage.model.entity.PayAndEmpAndDept;


@Repository
public interface EmpPayRepository extends JpaRepository<PayAndEmpAndDept, Integer> {

	int countBySalMonthContaining(String searchValue);

	List<PayAndEmpAndDept> findBySalMonthContaining(String searchValue, Pageable paging);

//	int countByEmpNameContaining(String searchValue);
//
//	List<PayAndEmpAndDept> findByEmp_NameContaining(String searchValue, Pageable paging);



}
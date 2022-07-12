package com.bukkeubook.book.manage.model.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bukkeubook.book.manage.model.entity.AppVacationAndEmp;

@Repository
public interface EmpAnnualRepository extends JpaRepository<AppVacationAndEmp, Integer> {

	List<AppVacationAndEmp> findAll(Sort by);



}

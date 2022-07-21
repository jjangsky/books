package com.bukkeubook.book.manage.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bukkeubook.book.manage.model.entity.EmpContAndEmp;


public interface EmpContAndEmpRepository extends JpaRepository<EmpContAndEmp, Integer>{

	EmpContAndEmp findBycontNo(int contNo);

}

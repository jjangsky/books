package com.bukkeubook.book.manage.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bukkeubook.book.manage.model.dto.EmpDTO;
import com.bukkeubook.book.manage.model.entity.Emp;

public interface OriginalEmpRepository extends JpaRepository<Emp, Integer>{

	List<Emp> findEmpByEmpNo(int empNo);



}

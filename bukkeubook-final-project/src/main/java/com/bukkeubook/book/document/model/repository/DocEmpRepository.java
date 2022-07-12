package com.bukkeubook.book.document.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bukkeubook.book.document.model.dto.EmpDTO;
import com.bukkeubook.book.document.model.entity.Emp;

public interface DocEmpRepository extends JpaRepository<Emp, Integer>{

	List<Emp> findByDeptCode(int dept);

}

package com.bukkeubook.book.manage.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bukkeubook.book.manage.model.entity.Emp;

public interface OriginalEmpRepository extends JpaRepository<Emp, Integer>{

	List<Emp> findMemberByempNo(int empNo);





}

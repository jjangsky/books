package com.bukkeubook.book.manage.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bukkeubook.book.manage.model.entity.Emp;

public interface OriginalEmpRepository extends JpaRepository<Emp, Integer>{

	List<Emp> findMemberByempNo(int empNo);

	List<Emp> findByEmpNameContaining(String searchValue);

//	@Query(value = "SELECT SEQ_EMP_NO.NEXTVAL FROM DUAL", nativeQuery = true)
//	int findCurrentSeqEmpNo();




}

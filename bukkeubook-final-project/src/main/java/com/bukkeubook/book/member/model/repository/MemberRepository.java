package com.bukkeubook.book.member.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bukkeubook.book.manage.model.dto.EmpDTO;
import com.bukkeubook.book.manage.model.entity.Emp;

public interface MemberRepository extends JpaRepository<Emp, Integer> {

	EmpDTO findByempNo(int empNo2);

}

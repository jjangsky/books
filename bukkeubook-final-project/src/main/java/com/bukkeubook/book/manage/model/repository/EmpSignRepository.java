package com.bukkeubook.book.manage.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bukkeubook.book.manage.model.entity.Sign;

@Repository
public interface EmpSignRepository extends JpaRepository<Sign, Integer>{

	Sign findByEmpNo(int memberCode);


}

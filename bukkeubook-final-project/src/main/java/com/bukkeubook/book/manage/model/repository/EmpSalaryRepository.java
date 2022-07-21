package com.bukkeubook.book.manage.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bukkeubook.book.manage.model.entity.SalaryAndEmpAndDept;


@Repository
public interface EmpSalaryRepository extends JpaRepository<SalaryAndEmpAndDept, Integer> {


}
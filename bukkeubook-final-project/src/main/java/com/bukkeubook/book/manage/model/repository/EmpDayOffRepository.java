package com.bukkeubook.book.manage.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bukkeubook.book.manage.model.entity.DayOffAndEmpAndDept;

@Repository
public interface EmpDayOffRepository extends JpaRepository<DayOffAndEmpAndDept, Integer> {


}
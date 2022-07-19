package com.bukkeubook.book.manage.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bukkeubook.book.manage.model.entity.Dept;

public interface SimpleDeptRepository extends JpaRepository<Dept, Integer>{

}

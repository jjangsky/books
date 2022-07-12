package com.bukkeubook.book.mypage.model.repository;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bukkeubook.book.manage.model.dto.AppVacationDTO;
import com.bukkeubook.book.manage.model.entity.AppVacation;

public interface VacationRepository extends JpaRepository<AppVacation, Integer>{


	List<AppVacation> findAllByEmpNoAndVacStatus(int memberCode, String approve);



}

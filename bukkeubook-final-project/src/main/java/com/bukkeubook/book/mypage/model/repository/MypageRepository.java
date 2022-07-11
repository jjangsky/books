package com.bukkeubook.book.mypage.model.repository;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bukkeubook.book.manage.model.dto.AppVacationDTO;

public interface MypageRepository extends JpaRepository<Category, Integer>{

	List<AppVacationDTO> findMyAnnualList();

}

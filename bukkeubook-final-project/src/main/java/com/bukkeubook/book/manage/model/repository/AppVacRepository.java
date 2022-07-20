package com.bukkeubook.book.manage.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bukkeubook.book.manage.model.entity.AppVacation;

@Repository
public interface AppVacRepository extends JpaRepository<AppVacation, Integer> {

//	List<AppVacation> findAppVacByNo(int vacNo);

	AppVacation findByVacNo(int vacNo);


}

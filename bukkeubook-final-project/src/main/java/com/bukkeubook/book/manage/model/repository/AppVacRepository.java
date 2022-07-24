package com.bukkeubook.book.manage.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bukkeubook.book.manage.model.entity.AppVacation;
import com.bukkeubook.book.manage.model.entity.AppVacationAndEmp;

@Repository
// Repository랑 Service는 이름 맞출 것
public interface AppVacRepository extends JpaRepository<AppVacation, Integer> {

	List<AppVacationAndEmp> findAppVacByVacNo(int vacNo);

}

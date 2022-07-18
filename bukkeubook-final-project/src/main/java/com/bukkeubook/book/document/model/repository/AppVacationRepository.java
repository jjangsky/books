package com.bukkeubook.book.document.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bukkeubook.book.document.model.entity.DocAppVacation;

public interface AppVacationRepository extends JpaRepository<DocAppVacation, Object>{
	
	@Query(value = "SELECT MAX(a.vacNo) FROM DocAppVacation a")
	int findCurrentSeq();

	List<DocAppVacation> findByEmpNo(int empNo);
}

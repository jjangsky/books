package com.bukkeubook.book.manage.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bukkeubook.book.manage.model.entity.ProfPhoto;

@Repository
public interface EmpAndProfileRepository extends JpaRepository<ProfPhoto, Integer>{

	/* 사원 프로필 사진 등록 */
//	void input(ProfPhoto map);

}

package com.bukkeubook.book.manage.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bukkeubook.book.manage.model.entity.ProfPhoto;

public interface ProfilePhotoRepository  extends JpaRepository<ProfPhoto, Integer>{

	/* 사원 프로필 사진 수정 */
	ProfPhoto findByEmpNo(int empNo);


}

package com.bukkeubook.book.document.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bukkeubook.book.document.model.entity.AppRoot;

public interface AppRootRepository extends JpaRepository <AppRoot,Object>{

	@Query(value = "SELECT MAX(a.appRootNo) FROM AppRoot a")
	int findCurrentSeqAccRoot();

	@Query("SELECT r.appRootNo,r.stepNo,r.stepNo FROM AppRoot r WHERE r.appRootNo = ?1")
	List<Object[]> findByAppRootNoDocList(int appRootNo);
	
}

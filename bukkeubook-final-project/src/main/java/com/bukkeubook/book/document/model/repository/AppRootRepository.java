package com.bukkeubook.book.document.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bukkeubook.book.document.model.entity.AppRoot;

public interface AppRootRepository extends JpaRepository <AppRoot,Integer>{

	@Query(value = "SELECT MAX(a.appRootNo) FROM AppRoot a")
	int findCurrentSeqAccRoot();

}

package com.bukkeubook.book.document.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bukkeubook.book.document.model.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer>{

	@Query(value = "SELECT MAX(d.docNo) FROM Document d")
	int findCurrentSeqDoc();

}

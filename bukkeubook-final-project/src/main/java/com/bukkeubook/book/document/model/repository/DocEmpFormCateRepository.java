package com.bukkeubook.book.document.model.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bukkeubook.book.document.model.entity.DocumentAndEmpAndFormCate;

public interface DocEmpFormCateRepository extends JpaRepository <DocumentAndEmpAndFormCate , Object>{

	List<DocumentAndEmpAndFormCate> findByEmpNoAndDocStatus(int tempEmpNo, String docStatus, Sort sort);

	DocumentAndEmpAndFormCate findDocEmpRepositoryByDocNoAndEmpNoAndDocStatus(int selectedDocNo, int tempEmpNo,
			String docStatus);

}

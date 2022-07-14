package com.bukkeubook.book.document.model.service;

import java.util.List;

import com.bukkeubook.book.document.model.dto.DeptDTO;
import com.bukkeubook.book.document.model.dto.DocumentAndEmpAndFormCateDTO;
import com.bukkeubook.book.document.model.dto.DocumentDTO;
import com.bukkeubook.book.document.model.dto.EmpDTO;
import com.bukkeubook.book.document.model.dto.FormCateDTO;

public interface DocService {

	List<FormCateDTO> findDocFormList();

	List<DeptDTO> findDept();

	List<EmpDTO> findEmp(int dept);

	void insertNewtempDocument(DocumentDTO newDoc);

	List<DocumentAndEmpAndFormCateDTO> findTempDocList(int tempEmpNo, String docStatus);

	DocumentAndEmpAndFormCateDTO findOneTempDoc(int selectedDocNo, int tempEmpNo, String docStatus);

	void updateTempDocument(DocumentDTO updateDoc);

	void deleteTempDoc(int docNo);

	void insertNewDoc(DocumentDTO newDoc);

}

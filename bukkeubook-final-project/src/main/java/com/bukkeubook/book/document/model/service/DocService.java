package com.bukkeubook.book.document.model.service;

import java.util.List;

import com.bukkeubook.book.document.model.dto.AppRootDTO;
import com.bukkeubook.book.document.model.dto.ApproverDTO;
import com.bukkeubook.book.document.model.dto.DeptDTO;
import com.bukkeubook.book.document.model.dto.DocumentAndEmpAndFormCateDTO;
import com.bukkeubook.book.document.model.dto.DocumentDTO;
import com.bukkeubook.book.document.model.dto.EmpDTO;
import com.bukkeubook.book.document.model.dto.FormCateDTO;
import com.bukkeubook.book.document.model.entity.Approver;

public interface DocService {

	List<FormCateDTO> findDocFormList();

	List<DeptDTO> findDept();

	List<EmpDTO> findEmp(int dept);

	void insertNewtempDocument(DocumentDTO newDoc);

	List<DocumentAndEmpAndFormCateDTO> findTempDocList(int tempEmpNo, String docStatus);

	DocumentAndEmpAndFormCateDTO findOneTempDoc(int selectedDocNo, int tempEmpNo, String docStatus);

	void updateTempDocument(DocumentDTO updateDoc);

	void deleteTempDoc(int docNo);

	void insertNewDocOneAcc(DocumentDTO newDoc, AppRootDTO appRoot, ApproverDTO approver);
	
	void insertNewDocTwoAcc(DocumentDTO newDoc, AppRootDTO appRoot, ApproverDTO appro, ApproverDTO appro2);
	
	void insertNewDocThreeAcc(DocumentDTO newDoc, AppRootDTO appRoot, ApproverDTO appro, ApproverDTO appro2, ApproverDTO appro3);

	void submitTempDocOneAcc(DocumentDTO tempDoc, AppRootDTO appRoot, ApproverDTO approver);

	void submitTempDocTwoAcc(DocumentDTO tempDoc, AppRootDTO appRoot, List<Approver> approverList);

}

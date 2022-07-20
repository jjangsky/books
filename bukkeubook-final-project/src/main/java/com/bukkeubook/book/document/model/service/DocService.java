package com.bukkeubook.book.document.model.service;

import java.util.List;

import com.bukkeubook.book.document.model.dto.AppRootDTO;
import com.bukkeubook.book.document.model.dto.AppVacationDTO;
import com.bukkeubook.book.document.model.dto.ApproverDTO;
import com.bukkeubook.book.document.model.dto.CancelVacationDTO;
import com.bukkeubook.book.document.model.dto.DeptDTO;
import com.bukkeubook.book.document.model.dto.DocWriteInfoDTO;
import com.bukkeubook.book.document.model.dto.DocumentAndEmpAndFormCateDTO;
import com.bukkeubook.book.document.model.dto.EmpDTO;
import com.bukkeubook.book.document.model.dto.FormCateDTO;
import com.bukkeubook.book.document.model.dto.InboxListDTO;
import com.bukkeubook.book.document.model.dto.SubmitDocumentDTO;
import com.bukkeubook.book.document.model.dto.TempStoreDocumentDTO;
import com.bukkeubook.book.document.model.entity.Approver;
import com.bukkeubook.book.document.model.entity.SubmitApprover;

public interface DocService {

	List<FormCateDTO> findDocFormList();

	List<DeptDTO> findDept();

	List<EmpDTO> findEmp(int dept);

	void insertNewtempDocument(TempStoreDocumentDTO newDoc);

	List<DocumentAndEmpAndFormCateDTO> findTempDocList(int tempEmpNo, String docStatus);

	DocumentAndEmpAndFormCateDTO findOneTempDoc(int selectedDocNo, int tempEmpNo, String docStatus);

	void updateTempDocument(TempStoreDocumentDTO updateDoc);

	void deleteTempDoc(int docNo);

	void insertNewDocOneAcc(SubmitDocumentDTO newDoc, AppRootDTO appRoot, ApproverDTO approver);
	
	void insertNewDocThreeAcc(SubmitDocumentDTO newDoc, AppRootDTO appRoot, List<SubmitApprover> approverList);

	void submitTempDocOneAcc(SubmitDocumentDTO tempDoc, AppRootDTO appRoot, ApproverDTO approver);

	void submitTempDocTwoAcc(SubmitDocumentDTO tempDoc, AppRootDTO appRoot, List<SubmitApprover> approverList);

	List<InboxListDTO> findInboxAllList(int empNo);

	List<DocumentAndEmpAndFormCateDTO> findByDocNoList(int empNo, String docStatus);

	TempStoreDocumentDTO findByDocNo(int docNo);

	DocWriteInfoDTO findWriterInfo(int empNo);

	void insertNewVacationApp(AppVacationDTO vacation);

	List<AppVacationDTO> findByEmpNoVacationList(int empNo);

	void insertNewCancelVacation(CancelVacationDTO cancVaca);

	List<Integer> vacationInfo();

	List<AppVacationDTO> allVacationList(int empNo);

	List<CancelVacationDTO> allCancelVacationList(int empNo);

	AppVacationDTO findByVacNo(int vacNo);

	CancelVacationDTO findByvacCancNo(int vacCancNo);

	void updateDocStatusApprove(int empNo, TempStoreDocumentDTO doc, String statusApp);

	List<String> checkDoc(int docNo, int empNo);

	void updateDocStatusRefuse(int empNo, TempStoreDocumentDTO doc, String statusApp);

}

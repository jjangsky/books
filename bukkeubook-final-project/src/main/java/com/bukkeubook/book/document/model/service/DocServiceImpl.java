package com.bukkeubook.book.document.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.document.model.dto.AppRootDTO;
import com.bukkeubook.book.document.model.dto.ApproverDTO;
import com.bukkeubook.book.document.model.dto.DeptDTO;
import com.bukkeubook.book.document.model.dto.DocumentAndEmpAndFormCateDTO;
import com.bukkeubook.book.document.model.dto.EmpDTO;
import com.bukkeubook.book.document.model.dto.FormCateDTO;
import com.bukkeubook.book.document.model.dto.SubmitDocumentDTO;
import com.bukkeubook.book.document.model.dto.TempStoreDocumentDTO;
import com.bukkeubook.book.document.model.entity.AppRoot;
import com.bukkeubook.book.document.model.entity.Approver;
import com.bukkeubook.book.document.model.entity.Dept;
import com.bukkeubook.book.document.model.entity.Document;
import com.bukkeubook.book.document.model.entity.DocumentAndEmpAndFormCate;
import com.bukkeubook.book.document.model.entity.Emp;
import com.bukkeubook.book.document.model.entity.FormCate;
import com.bukkeubook.book.document.model.entity.SubmitApprover;
import com.bukkeubook.book.document.model.entity.SubmitDocument;
import com.bukkeubook.book.document.model.repository.AppRootRepository;
import com.bukkeubook.book.document.model.repository.ApproverRepository;
import com.bukkeubook.book.document.model.repository.ApproverRepository2;
import com.bukkeubook.book.document.model.repository.DocDeptRepository;
import com.bukkeubook.book.document.model.repository.DocEmpFormCateRepository;
import com.bukkeubook.book.document.model.repository.DocEmpRepository;
import com.bukkeubook.book.document.model.repository.DocumentRepository;
import com.bukkeubook.book.document.model.repository.FormCateRepository;
import com.bukkeubook.book.document.model.repository.SubmitDocumentRepository;

@Service("docService")
public class DocServiceImpl implements DocService{
	
	private final DocDeptRepository docDeptRepository;
	private final DocEmpRepository docEmpRepository;
	private final FormCateRepository formRepository;
	private final DocumentRepository docRepository;
	private final SubmitDocumentRepository subDocRepository;
	private final DocEmpFormCateRepository docEmpFormCateRepository;
	private final AppRootRepository appRootRepository;
	private final ApproverRepository approverRepository;
	private final ApproverRepository2 approverRepository2;
	private final ModelMapper modelMapper;
	
	@Autowired
	public DocServiceImpl(DocDeptRepository docDeptRepository,
						  DocEmpRepository docEmpRepository,
						  ModelMapper modelMapper,
						  FormCateRepository formRepository,
						  DocumentRepository docRepository,
						  DocEmpFormCateRepository docEmpFormCateRepository,
						  AppRootRepository appRootRepository,
						  ApproverRepository approverRepository,
						  ApproverRepository2 approverRepository2,
						  SubmitDocumentRepository subDocRepository) {
		this.docDeptRepository = docDeptRepository;
		this.modelMapper = modelMapper;
		this.formRepository = formRepository;
		this.docEmpRepository = docEmpRepository;
		this.docRepository = docRepository;
		this.docEmpFormCateRepository = docEmpFormCateRepository;
		this.appRootRepository = appRootRepository;
		this.approverRepository = approverRepository;
		this.approverRepository2 = approverRepository2;
		this.subDocRepository = subDocRepository;
	}

	/* 전자결재 작성 첫화면 - 양식 고르기 */
	@Override
	public List<FormCateDTO> findDocFormList() {

		List<FormCate> formList = formRepository.findAll(Sort.by("formNo"));
		
		return formList.stream().map(form -> modelMapper.map(form, FormCateDTO.class)).toList();
	}

	/* 결재라인 지정 ajax select Tag Option Dept */
	@Override
	public List<DeptDTO> findDept() {

		List<Dept> deptList = docDeptRepository.findAll(Sort.by("deptCode"));
		
//		System.out.println("ssssssssssssssssssssssssssssssss" + deptList);
		
		return deptList.stream().map(dept -> modelMapper.map(dept, DeptDTO.class)).toList();
	}

	/* 결재라인 지정 ajax select Tag Option Emp */
	@Override
	public List<EmpDTO> findEmp(int dept) {

		List<Emp> empList = docEmpRepository.findByDeptCode(dept);
		
		return empList.stream().map(emp -> modelMapper.map(emp, EmpDTO.class)).toList();
//		return empList.stream().map(emp -> modelMapper.map(emp, EmpDTO.class)).toList();
	}

	/* 임시저장 */
	@Override
	@Transactional
	public void insertNewtempDocument(TempStoreDocumentDTO newDoc) {
		
		docRepository.save(modelMapper.map(newDoc, Document.class));
		
	}

	/* 임시저장 리스트 조회 */
	@Override
	public List<DocumentAndEmpAndFormCateDTO> findTempDocList(int tempEmpNo, String docStatus) {

		List<DocumentAndEmpAndFormCate> tempDocList = docEmpFormCateRepository.findByEmpNoAndDocStatus(tempEmpNo,docStatus,Sort.by("docNo"));
		
//		System.out.println("임시저장 리스트 조회 --------------------여기는 서비스에서 엔티티 조회했을때양" + tempDocList);
		
		return tempDocList.stream().map(tempDoc -> modelMapper.map(tempDoc, DocumentAndEmpAndFormCateDTO.class)).toList();
	}

	/* 임시저장 수정 페이지 접속*/
	@Override
	public DocumentAndEmpAndFormCateDTO findOneTempDoc(int selectedDocNo, int tempEmpNo, String docStatus) {

		DocumentAndEmpAndFormCate oneTempDoc = docEmpFormCateRepository.findDocEmpRepositoryByDocNoAndEmpNoAndDocStatus(selectedDocNo,tempEmpNo,docStatus);
		
		System.out.println("임시저장 한개만 조회 --------------------여기는 서비스에서 엔티티 조회했을때양"+oneTempDoc);
		
		return modelMapper.map(oneTempDoc, DocumentAndEmpAndFormCateDTO.class);
	}

	/* 임시저장 수정하기 */
	@Override
	@Transactional
	public void updateTempDocument(TempStoreDocumentDTO updateDoc) {
		
		Document foundDoc = docRepository.findById(updateDoc.getDocNo1()).get();
		
		System.out.println("서비스에서 찾은 수정할 아이  " + foundDoc);
		
		foundDoc.setCnt1(updateDoc.getCnt1());
		foundDoc.setTagCnt1(updateDoc.getTagCnt1());
		foundDoc.setDocTitle1(updateDoc.getDocTitle1());
		foundDoc.setWrDate1(updateDoc.getWrDate1());
		
	}

	/* 임시저장 삭제하기 */
	@Override
	@Transactional
	public void deleteTempDoc(int docNo) {

		docRepository.deleteById(docNo);
		
	}

	/* 새로작성한 기안서, 지결서 상신하기 - 결재자 1명일 때 */
	@Override
	@Transactional
	public void insertNewDocOneAcc(SubmitDocumentDTO newDoc,AppRootDTO appRoot,ApproverDTO approver) {
		
		subDocRepository.save(modelMapper.map(newDoc, SubmitDocument.class));
		
		int currentDocNo = docRepository.findCurrentSeqDoc();
		System.out.println("Service            ");
		System.out.println(currentDocNo);
		appRoot.setDocNo(currentDocNo);
		appRootRepository.save(modelMapper.map(appRoot, AppRoot.class));
		
		int currentAccRootNo = appRootRepository.findCurrentSeqAccRoot();
		System.out.println("Service            ");
		System.out.println(currentAccRootNo);
		
		approver.setAppRootNo(currentAccRootNo);
		approverRepository2.save(modelMapper.map(approver, Approver.class));
		
		
	}

	/* 새로작성한 기안서, 지결서 상신하기 - 결재자 2, 3명일 때 */
	@Override
	public void insertNewDocThreeAcc(SubmitDocumentDTO newDoc, AppRootDTO appRoot, List<SubmitApprover> approverList) {

		subDocRepository.save(modelMapper.map(newDoc, SubmitDocument.class));
		
		int currentDocNo = docRepository.findCurrentSeqDoc();
		System.out.println("Service            ");
		System.out.println(currentDocNo);
		appRoot.setDocNo(currentDocNo);
		appRootRepository.save(modelMapper.map(appRoot, AppRoot.class));
		
		int currentAccRootNo = appRootRepository.findCurrentSeqAccRoot();
		System.out.println("Service            ");
		System.out.println(currentAccRootNo);
		
		for(int i = 0; i < approverList.size(); i++) {
			SubmitApprover app = approverList.get(i);
			app.setAppRootNo2(currentAccRootNo);
		}
		
		approverRepository.saveAll(approverList);
		
	}

	/* 임시저장된 기안서, 지결서 상신하기 - 결재자 1명일 때 */
	@Override
	public void submitTempDocOneAcc(SubmitDocumentDTO tempDoc, AppRootDTO appRoot, ApproverDTO approver) {

		SubmitDocument foundDoc = subDocRepository.findById(tempDoc.getDocNo2()).get();
		foundDoc.setCnt2(tempDoc.getCnt2());
		foundDoc.setDocTitle2(tempDoc.getDocTitle2());
		foundDoc.setTagCnt2(tempDoc.getTagCnt2());
		foundDoc.setWrDate2(tempDoc.getWrDate2());
		foundDoc.setDocStatus2(tempDoc.getDocStatus2());
		System.out.println("Service           Document Update Success");
		
		appRoot.setDocNo(tempDoc.getDocNo2());
		appRootRepository.save(modelMapper.map(appRoot, AppRoot.class));
		
		int currentAccRootNo = appRootRepository.findCurrentSeqAccRoot();
		approver.setAppRootNo(currentAccRootNo);
		approverRepository2.save(modelMapper.map(approver, Approver.class));
	}

	/* 임시저장된 기안서, 지결서 상신하기 - 결재자 2명,3명 일 때 */
	@Override
	public void submitTempDocTwoAcc(SubmitDocumentDTO tempDoc, AppRootDTO appRoot, List<SubmitApprover> approverList) {

		SubmitDocument foundDoc = subDocRepository.findById(tempDoc.getDocNo2()).get();
		foundDoc.setCnt2(tempDoc.getCnt2());
		foundDoc.setDocTitle2(tempDoc.getDocTitle2());
		foundDoc.setTagCnt2(tempDoc.getTagCnt2());
		foundDoc.setWrDate2(tempDoc.getWrDate2());
		foundDoc.setDocStatus2(tempDoc.getDocStatus2());
		System.out.println("Service           Document Update Success");
		
		appRoot.setDocNo(tempDoc.getDocNo2());
		appRootRepository.save(modelMapper.map(appRoot, AppRoot.class));
		
		int currentAccRootNo = appRootRepository.findCurrentSeqAccRoot();
		for(int i=0; i<approverList.size();i++) {
			SubmitApprover app =  approverList.get(i);
			app.setAppRootNo2(currentAccRootNo);
		}
		approverRepository.saveAll(approverList);
		
	}

	
	
}

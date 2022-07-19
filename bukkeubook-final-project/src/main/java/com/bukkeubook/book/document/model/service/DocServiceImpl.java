package com.bukkeubook.book.document.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.internal.build.AllowSysOut;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
import com.bukkeubook.book.document.model.entity.AppRoot;
import com.bukkeubook.book.document.model.entity.Approver;
import com.bukkeubook.book.document.model.entity.Dept;
import com.bukkeubook.book.document.model.entity.DocAppVacation;
import com.bukkeubook.book.document.model.entity.DocCancelVacation;
import com.bukkeubook.book.document.model.entity.Document;
import com.bukkeubook.book.document.model.entity.DocumentAndEmpAndFormCate;
import com.bukkeubook.book.document.model.entity.Emp;
import com.bukkeubook.book.document.model.entity.FormCate;
import com.bukkeubook.book.document.model.entity.SubmitApprover;
import com.bukkeubook.book.document.model.entity.SubmitDocument;
import com.bukkeubook.book.document.model.repository.AppRootRepository;
import com.bukkeubook.book.document.model.repository.AppVacationRepository;
import com.bukkeubook.book.document.model.repository.ApproverRepository;
import com.bukkeubook.book.document.model.repository.ApproverRepository2;
import com.bukkeubook.book.document.model.repository.CancelVacationRepository;
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
	private final AppVacationRepository vacationRepository;
	private final CancelVacationRepository cancelVacaRepository;
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
						  SubmitDocumentRepository subDocRepository,
						  AppVacationRepository vacationRepository,
						  CancelVacationRepository cancelVacaRepository) {
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
		this.vacationRepository = vacationRepository;
		this.cancelVacaRepository = cancelVacaRepository;
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

		List<Emp> empList = docEmpRepository.findByDeptCode(dept,Sort.by("empNo").descending());
		
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

		List<DocumentAndEmpAndFormCate> tempDocList = docEmpFormCateRepository.findByEmpNoAndDocStatus(tempEmpNo,docStatus,Sort.by("wrDate").descending());
		
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

	/* 수신함 리스트 조회 */
	@Override
	public List<InboxListDTO> findInboxAllList(int empNo) {

		List<InboxListDTO> all = new ArrayList<>();
		
		/* 사원과 맞는 결재자테이블에서 결재자 리스트 */
		List<Object[]> correctToApproverList = approverRepository2.findByApproverNoDocList(empNo);
		
		/* 결재 경로 번호들 담을 리스트 */
		List<Integer> appRootNoList = new ArrayList<>();
		
		/* 결재자가 전결한 상태 (결재상태)들 담을 리스트 */
		List<String> appStatusList = new ArrayList<>();
		
		for(Object[] appro : correctToApproverList) {
			appRootNoList.add((int)appro[0]);
			appStatusList.add((String)appro[1]);
		}
//		System.out.println(appRootNoList);
//		System.out.println(appStatusList);
		
		for(int i = 0; i < appStatusList.size(); i++) {
			InboxListDTO inbox = new InboxListDTO();
			inbox.setAppStatus(appStatusList.get(i));
			all.add(inbox);
		}
		
		/* 가져온 결재경로번호에 맞는 결재경로 리스트 */
		AppRoot root = new AppRoot();
		List<AppRoot> correctAppRootList = new ArrayList<>();
		for(int i = 0; i<appRootNoList.size(); i++) {
			root = appRootRepository.findById(appRootNoList.get(i)).get();
			correctAppRootList.add(root);
		}
//		System.out.println(correctAppRootList);
		
		/* 가져온 문서번호에 맞는 문서 가져오기 */
		Document doc = new Document();
		List<Integer> docNoList = new ArrayList<>();
		List<Integer> stepNoList = new ArrayList<>();
		List<String> stepNameList = new ArrayList<>();
		List<Document> docList = new ArrayList<>();
		for(int i = 0; i<correctAppRootList.size(); i++) {
			int docNo = correctAppRootList.get(i).getDocNo();
			docNoList.add(docNo);
			int stepNo = correctAppRootList.get(i).getStepNo();
			stepNoList.add(stepNo);
//			doc = docRepository.findById(docList.get(i).getDocNo1()).get();
		}
		
		for(int i = 0; i<stepNoList.size(); i++) {
			String name = stepNoList.get(i) + "단계" ;
			stepNameList.add(name);
		}
		
		for(int i = 0; i<stepNameList.size();i++) {
			InboxListDTO inbox = all.get(i);
			inbox.setStepName(stepNameList.get(i));
		}
//		System.out.println(stepNameList);
		
//		System.out.println(docNoList);
		for(int i = 0; i<docNoList.size(); i++) {
			int docNo = docNoList.get(i);
			doc = docRepository.findById(docNo).get();
			docList.add(doc);
		}
		/* 엔티티 타입을 DTO로 바꾸어 리턴한다. */
		List<TempStoreDocumentDTO> returnDocList = docList.stream().map(document -> modelMapper.map(document, TempStoreDocumentDTO.class)).toList();
//		System.out.println(returnDocList);
		
		for(int i = 0; i<returnDocList.size();i++) {
			InboxListDTO inbox = all.get(i);
			inbox.setDocument(returnDocList.get(i));
			if(1 == returnDocList.get(i).getFormNo1()) {
				inbox.setFormName("기안서");
			}else {
				inbox.setFormName("지출결의서");
			}
		}
		
		return all;
	}

	/* 상신함 전체리스트 조회 */
	@Override
	public List<DocumentAndEmpAndFormCateDTO> findByDocNoList(int empNo , String docStatus) {

		List<DocumentAndEmpAndFormCate> docList = docEmpFormCateRepository.findByEmpNoAndDocStatusNot(empNo,docStatus,Sort.by("wrDate").descending());
		
		return docList.stream().map(doc -> modelMapper.map(doc, DocumentAndEmpAndFormCateDTO.class)).toList();
	}


	/* 수신함 상세 조회 */
	@Override
	public TempStoreDocumentDTO findByDocNo(int docNo) {

		Document doc = docRepository.findById(docNo).get();
		
		return modelMapper.map(doc, TempStoreDocumentDTO.class);
	}

	/* 전자결재 작성시 작성자 이름, 부서명, 문서번호 넣어주기 */
	@Override
	public DocWriteInfoDTO findWriterInfo(int empNo) {
		
		DocWriteInfoDTO info = new DocWriteInfoDTO();
		
		Emp emp = docEmpRepository.findById(empNo).get();
		EmpDTO e = modelMapper.map(emp, EmpDTO.class);
		info.setEmpName(e.getEmpName());
		info.setEmpJobCode(e.getEmpJobCode());
		
		int deptCode = e.getDeptCode();
		Dept dept = docDeptRepository.findById(deptCode).get();
		DeptDTO d = modelMapper.map(dept, DeptDTO.class);
		info.setDeptName(d.getDeptName());
		
		int currentDocNo = docRepository.findCurrentSeqDoc() + 1;
		info.setDocNo(currentDocNo);
		
		System.out.println(info);
		
		return info;
	}

	/* 휴가신청서 상신하기 */
	@Override
	@Transactional
	public void insertNewVacationApp(AppVacationDTO vacation) {

		int vacNo = vacationRepository.findCurrentSeq() + 10;
		vacation.setVacNo(vacNo);
		System.out.println("Service       " +vacation);
		
		vacationRepository.save(modelMapper.map(vacation, DocAppVacation.class));
		
	}

	/* 취소 신청서 작성시 자신이 작성한 휴가 신청서 리스트 조회 */
	@Override
	public List<AppVacationDTO> findByEmpNoVacationList(int empNo) {
		
		List<DocAppVacation> vacationByOneList = vacationRepository.findByEmpNo(empNo,Sort.by("vacAppNo").descending());
		
		return vacationByOneList.stream().map(vac -> modelMapper.map(vac, AppVacationDTO.class)).toList();
	}

	/* 휴가 취소신청서 상신 */
	@Override
	@Transactional
	public void insertNewCancelVacation(CancelVacationDTO cancVaca) {
		cancelVacaRepository.save(modelMapper.map(cancVaca, DocCancelVacation.class));
	}

	/* 휴가서류 문서번호 조회 */
	@Override
	public List<Integer> vacationInfo() {
		
		int vacNo = vacationRepository.findCurrentSeq() + 1;
		int cancVacNo = cancelVacaRepository.findCurrentSeq() +1;
		List<Integer> vacationInfo = new ArrayList<>();
		
		vacationInfo.add(vacNo);
		vacationInfo.add(cancVacNo);
		
		return vacationInfo;
	}

	/* 휴가 리스트 조회 */
	@Override
	public List<AppVacationDTO> allVacationList(int empNo) {
		
		List<DocAppVacation> allVacationList = vacationRepository.findByEmpNo(empNo,Sort.by("vacAppNo").descending());
		
		return allVacationList.stream().map(vaca -> modelMapper.map(vaca, AppVacationDTO.class)).toList();
	}

	/* 휴가취소 리스트 조회 */
	@Override
	public List<CancelVacationDTO> allCancelVacationList(int empNo) {

		List<DocCancelVacation> cancelList = cancelVacaRepository.findByEmpNo(empNo,Sort.by("vacCancDate").descending());
		
		return cancelList.stream().map(can -> modelMapper.map(can, CancelVacationDTO.class)).toList();
	}

	/* 휴가 신청 상세 조회 */
	@Override
	public AppVacationDTO findByVacNo(int vacNo) {

		DocAppVacation vaca = vacationRepository.findById(vacNo).get();
		
		return modelMapper.map(vaca, AppVacationDTO.class);
	}

	/* 휴가취소 상세조회 */
	@Override
	public CancelVacationDTO findByvacCancNo(int vacCancNo) {

		DocCancelVacation canc = cancelVacaRepository.findById(vacCancNo).get();
		
		return modelMapper.map(canc, CancelVacationDTO.class);
	}

	
}

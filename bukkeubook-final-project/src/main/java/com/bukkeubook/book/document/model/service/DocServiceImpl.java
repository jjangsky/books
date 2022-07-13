package com.bukkeubook.book.document.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.document.model.dto.DeptDTO;
import com.bukkeubook.book.document.model.dto.DocumentAndEmpAndFormCateDTO;
import com.bukkeubook.book.document.model.dto.DocumentDTO;
import com.bukkeubook.book.document.model.dto.EmpDTO;
import com.bukkeubook.book.document.model.dto.FormCateDTO;
import com.bukkeubook.book.document.model.entity.Dept;
import com.bukkeubook.book.document.model.entity.Document;
import com.bukkeubook.book.document.model.entity.DocumentAndEmpAndFormCate;
import com.bukkeubook.book.document.model.entity.Emp;
import com.bukkeubook.book.document.model.entity.FormCate;
import com.bukkeubook.book.document.model.repository.DocDeptRepository;
import com.bukkeubook.book.document.model.repository.DocEmpFormCateRepository;
import com.bukkeubook.book.document.model.repository.DocEmpRepository;
import com.bukkeubook.book.document.model.repository.DocumentRepository;
import com.bukkeubook.book.document.model.repository.FormCateRepository;

@Service("docService")
public class DocServiceImpl implements DocService{
	
	private final DocDeptRepository docDeptRepository;
	private final DocEmpRepository docEmpRepository;
	private final FormCateRepository formRepository;
	private final DocumentRepository docRepository;
	private final DocEmpFormCateRepository docEmpFormCateRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public DocServiceImpl(DocDeptRepository docDeptRepository,
						  DocEmpRepository docEmpRepository,
						  ModelMapper modelMapper,
						  FormCateRepository formRepository,
						  DocumentRepository docRepository,
						  DocEmpFormCateRepository docEmpFormCateRepository) {
		this.docDeptRepository = docDeptRepository;
		this.modelMapper = modelMapper;
		this.formRepository = formRepository;
		this.docEmpRepository = docEmpRepository;
		this.docRepository = docRepository;
		this.docEmpFormCateRepository = docEmpFormCateRepository;
	}

	@Override
	public List<FormCateDTO> findDocFormList() {

		List<FormCate> formList = formRepository.findAll(Sort.by("formNo"));
		
		return formList.stream().map(form -> modelMapper.map(form, FormCateDTO.class)).toList();
	}

	@Override
	public List<DeptDTO> findDept() {

		List<Dept> deptList = docDeptRepository.findAll(Sort.by("deptCode"));
		
//		System.out.println("ssssssssssssssssssssssssssssssss" + deptList);
		
		return deptList.stream().map(dept -> modelMapper.map(dept, DeptDTO.class)).toList();
	}

	@Override
	public List<EmpDTO> findEmp(int dept) {

		List<Emp> empList = docEmpRepository.findByDeptCode(dept);
		
		return empList.stream().map(emp -> modelMapper.map(emp, EmpDTO.class)).toList();
//		return empList.stream().map(emp -> modelMapper.map(emp, EmpDTO.class)).toList();
	}

	@Override
	@Transactional
	public void insertNewtempDocument(DocumentDTO newDoc) {
		
		docRepository.save(modelMapper.map(newDoc, Document.class));
		
	}

	@Override
	public List<DocumentAndEmpAndFormCateDTO> findTempDocList(int tempEmpNo, String docStatus) {

		List<DocumentAndEmpAndFormCate> tempDocList = docEmpFormCateRepository.findByEmpNoAndDocStatus(tempEmpNo,docStatus,Sort.by("docNo"));
		
//		System.out.println("임시저장 리스트 조회 --------------------여기는 서비스에서 엔티티 조회했을때양" + tempDocList);
		
		return tempDocList.stream().map(tempDoc -> modelMapper.map(tempDoc, DocumentAndEmpAndFormCateDTO.class)).toList();
	}

	@Override
	public DocumentAndEmpAndFormCateDTO findOneTempDoc(int selectedDocNo, int tempEmpNo, String docStatus) {

		DocumentAndEmpAndFormCate oneTempDoc = docEmpFormCateRepository.findDocEmpRepositoryByDocNoAndEmpNoAndDocStatus(selectedDocNo,tempEmpNo,docStatus);
		
		System.out.println("임시저장 한개만 조회 --------------------여기는 서비스에서 엔티티 조회했을때양"+oneTempDoc);
		
		return modelMapper.map(oneTempDoc, DocumentAndEmpAndFormCateDTO.class);
	}

	
}

package com.bukkeubook.book.document.model.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.document.model.dto.FormCateDTO;
import com.bukkeubook.book.document.model.entity.FormCate;
import com.bukkeubook.book.document.model.repository.DocumentRepository;
import com.bukkeubook.book.document.model.repository.FormCateRepository;

@Service("docService")
public class DocServiceImpl implements DocService{
	
	private final DocumentRepository docRepository;
	private final FormCateRepository formRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public DocServiceImpl(DocumentRepository docRepository,ModelMapper modelMapper,FormCateRepository formRepository) {
		this.docRepository = docRepository;
		this.modelMapper = modelMapper;
		this.formRepository = formRepository;
	}

	@Override
	public List<FormCateDTO> findDocFormList() {

		List<FormCate> formList = formRepository.findAll(Sort.by("formNo"));
		
		return formList.stream().map(form -> modelMapper.map(form, FormCateDTO.class)).toList();
	}

	
}

package com.bukkeubook.book.manage.model.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bukkeubook.book.manage.model.dto.SignDTO;
import com.bukkeubook.book.manage.model.entity.Sign;
import com.bukkeubook.book.manage.model.repository.EmpSignRepository;


@Service
public class SignService {
	
	private final EmpSignRepository empSignRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public SignService(ModelMapper modelMapper, EmpSignRepository empSignRepository) {
		this.empSignRepository = empSignRepository;
		this.modelMapper = modelMapper;
		
	}
	
	/*  도장 사진 등록(변경)*/
	@Transactional
	public void registSign(SignDTO sign) {
		
		empSignRepository.save(modelMapper.map(sign, Sign.class));
		
	}
	/* 도장 사진 등록(회원 가입) */
	@Transactional
	public void registEmpNameFile(SignDTO signFile) {
		
		empSignRepository.save(modelMapper.map(signFile, Sign.class));
		
	}

}

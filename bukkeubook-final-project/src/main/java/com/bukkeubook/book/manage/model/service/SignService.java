package com.bukkeubook.book.manage.model.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bukkeubook.book.manage.model.dto.SignDTO;
import com.bukkeubook.book.manage.model.dto.joinDTO.EmpAndDeptDTO;
import com.bukkeubook.book.manage.model.entity.EmpAndDept;
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
	
	/*  도장 사진 조회 */
	public SignDTO searchEmpSign(int number) {

		Sign mySign = empSignRepository.findById(number).get();
		
		System.out.println("레포지토리      " + mySign);
		
		return modelMapper.map(mySign, SignDTO.class); //앤티티를 넣어달라고 요청 -> modelMapper
	}








}

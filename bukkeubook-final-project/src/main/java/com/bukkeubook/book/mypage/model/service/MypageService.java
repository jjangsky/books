package com.bukkeubook.book.mypage.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.manage.model.dto.AppVacationDTO;
import com.bukkeubook.book.mypage.model.repository.MypageRepository;

@Service
public class MypageService{
	
	private final MypageRepository mypageRepository;
	private final ModelMapper modelMapper;	
	
	@Autowired
	public MypageService(MypageRepository mypageRepository, ModelMapper modelMapper) {
		this.mypageRepository = mypageRepository;
		this.modelMapper = modelMapper;
	}

	public List<AppVacationDTO> findMyAnnualList(){
	
		List<AppVacationDTO> vacationList = mypageRepository.findMyAnnualList();
		
		return vacationList.stream().map(vacation -> modelMapper.map(vacation, AppVacationDTO.class)).collect(Collectors.toList());

	}
}

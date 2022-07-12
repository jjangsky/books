package com.bukkeubook.book.mypage.model.service;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.manage.model.dto.AppVacationDTO;
import com.bukkeubook.book.manage.model.dto.DayOffDTO;
import com.bukkeubook.book.manage.model.entity.AppVacation;
import com.bukkeubook.book.manage.model.entity.DayOff;
import com.bukkeubook.book.mypage.model.repository.DayOffRepository;
import com.bukkeubook.book.mypage.model.repository.VacationRepository;

@Service
public class MypageService{
	
	private final VacationRepository vacationRepository;
	private final DayOffRepository dayOffRepository;
	private final ModelMapper modelMapper;	
	
	@Autowired
	public MypageService(VacationRepository vacationRepository, DayOffRepository dayOffRepository, ModelMapper modelMapper) {
		this.vacationRepository = vacationRepository;
		this.dayOffRepository = dayOffRepository;
		this.modelMapper = modelMapper;
	}

	public List<AppVacationDTO> findMyAnnualList(int memberCode, String approve){
	
		List<AppVacation> vacationList = vacationRepository.findAllByEmpNoAndVacStatus(memberCode, approve);
		
		return vacationList.stream().map(vacation -> modelMapper.map(vacation, AppVacationDTO.class)).collect(Collectors.toList());

	}

	public List<DayOffDTO> findMyDayOffList(int memberCode) {
		
		List<DayOff> dayOffList = dayOffRepository.findAllByEmpNo(memberCode);
		
				return dayOffList.stream().map(dayOff -> modelMapper.map(dayOff, DayOffDTO.class)).collect(Collectors.toList());
	}
}

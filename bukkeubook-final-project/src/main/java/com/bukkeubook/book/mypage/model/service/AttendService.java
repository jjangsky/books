package com.bukkeubook.book.mypage.model.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bukkeubook.book.manage.model.dto.AttendDTO;
import com.bukkeubook.book.manage.model.entity.Attend;
import com.bukkeubook.book.mypage.model.repository.AttendRepository;

@Service
public class AttendService {
	
	private final AttendRepository attendRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public AttendService(AttendRepository attendRepository, ModelMapper modelMapper) {
		this.attendRepository = attendRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 마이페이지 출퇴근 내역 조회 */
	public List<AttendDTO> findMyAttend(int memberCode) {
		
		List<Attend> attendList = attendRepository.findAllByEmpNo(memberCode);
		
		return attendList.stream().map(attend -> modelMapper.map(attend, AttendDTO.class)).collect(Collectors.toList());
		
	}
	
	/* 마이페이지에서 출근 등록하기 */
	@Transactional
	public void registCheckIn(AttendDTO attend) {
		
		attendRepository.save(modelMapper.map(attend, Attend.class));
		
	}
	
	/* 마이페이지에서 퇴근 등록하기 */
	@Transactional
	public void modifyCheckOut(AttendDTO attend) {
		
		Attend modifyCheckOut = attendRepository.findByAttDateLike(attend.getAttDate());
		
		modifyCheckOut.setAttEnd(attend.getAttDate());
		
	}
	
	
	
	

}

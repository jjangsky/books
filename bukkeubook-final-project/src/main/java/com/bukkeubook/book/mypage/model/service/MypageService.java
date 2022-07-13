package com.bukkeubook.book.mypage.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bukkeubook.book.manage.model.dto.AppVacationDTO;
import com.bukkeubook.book.manage.model.dto.DayOffDTO;
import com.bukkeubook.book.manage.model.entity.AppVacation;
import com.bukkeubook.book.manage.model.entity.DayOff;
import com.bukkeubook.book.mypage.model.dto.CalendarDTO;
import com.bukkeubook.book.mypage.model.entity.Calendar;
import com.bukkeubook.book.mypage.model.repository.CalendarRepository;
import com.bukkeubook.book.mypage.model.repository.DayOffRepository;
import com.bukkeubook.book.mypage.model.repository.VacationRepository;

@Service
public class MypageService{
	
	private final VacationRepository vacationRepository;
	private final DayOffRepository dayOffRepository;
	private final CalendarRepository calendarRepository;
	private final ModelMapper modelMapper;	
	
	@Autowired
	public MypageService(VacationRepository vacationRepository, DayOffRepository dayOffRepository, CalendarRepository calendarRepository, ModelMapper modelMapper) {
		this.vacationRepository = vacationRepository;
		this.dayOffRepository = dayOffRepository;
		this.calendarRepository = calendarRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 마이페이지 연차(휴가) 사용 내역 조회 */
	public List<AppVacationDTO> findMyAnnualList(int memberCode, String approve){
	
		List<AppVacation> vacationList = vacationRepository.findAllByEmpNoAndVacStatus(memberCode, approve);
		
		return vacationList.stream().map(vacation -> modelMapper.map(vacation, AppVacationDTO.class)).collect(Collectors.toList());

	}
	
	/* 마이페이지 연차 횟수 조회 */
	public List<DayOffDTO> findMyDayOffList(int memberCode) {
		
		List<DayOff> dayOffList = dayOffRepository.findAllByEmpNo(memberCode);
		
		return dayOffList.stream().map(dayOff -> modelMapper.map(dayOff, DayOffDTO.class)).collect(Collectors.toList());
	}
	
	/* 일정 전체 조회  */
	public List<CalendarDTO> findMyCalendar(int memberCode) {
		
		List<Calendar> calendarList = calendarRepository.findAllByEmpNo(memberCode);
		
		return calendarList.stream().map(calendar -> modelMapper.map(calendar, CalendarDTO.class)).collect(Collectors.toList());
	}

	/* 일정 등록 */
	@Transactional
	public void registNewCalendar(CalendarDTO newCalendar) {
		
		calendarRepository.save(modelMapper.map(newCalendar, Calendar.class));
		
		
	}
	
	/* 일정 상세 조회 */
	public CalendarDTO findMyCalendarDetail(int calCode) {
		
		Calendar CalendarDetail = calendarRepository.findById(calCode).get();
		
		return modelMapper.map(CalendarDetail, CalendarDTO.class);
	}
	
	/* 일정 수정 */
	@Transactional
	public void modifyMyCalendar(CalendarDTO newCalendar) {
		
		Calendar updateCal = calendarRepository.findById(newCalendar.getCode()).get();
		updateCal.setTitle(newCalendar.getTitle());
		updateCal.setStart(newCalendar.getStart());
		updateCal.setEnd(newCalendar.getEnd());
		updateCal.setContent(newCalendar.getContent());
	}

	/* 일정 삭제 */
	public void deleteCalendar(int calCode) {
		
		calendarRepository.deleteById(calCode);
		
	}
}

package com.bukkeubook.book.mypage.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bukkeubook.book.manage.model.entity.DayOff;

public interface DayOffRepository extends JpaRepository<DayOff, Integer>{

	List<DayOff> findAllByEmpNo(int memberCode);
	
	/* 휴가 - 연차 트랜잭션 부분 (인사부) */
	void modifyDayOffInfo(DayOff map);

	DayOff findDayOffByEmpNo(int empNo);

}

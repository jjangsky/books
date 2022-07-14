package com.bukkeubook.book.mypage.model.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bukkeubook.book.manage.model.dto.AttendDTO;
import com.bukkeubook.book.manage.model.entity.Attend;

public interface AttendRepository extends JpaRepository<Attend, Integer>{

	List<Attend> findAllByEmpNo(int memberCode);

	Attend findByAttDateLike(Date attDate);





	


	

}
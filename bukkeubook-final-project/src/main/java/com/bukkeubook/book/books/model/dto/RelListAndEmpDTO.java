package com.bukkeubook.book.books.model.dto;

import java.sql.Date;

import com.bukkeubook.book.manage.model.dto.EmpDTO;

public class RelListAndEmpDTO {
//	REL_NO	NUMBER
//	REL_DATE	DATE
//	EMP_NO	NUMBER
	
	private int relNo;				// 출고번호
	private java.sql.Date relDate;	// 출고날짜
	private int empNo;
	private EmpDTO empDTO;			// 사원번호
	
	public RelListAndEmpDTO() {
	}

	public RelListAndEmpDTO(int relNo, Date relDate, int empNo, EmpDTO empDTO) {
		this.relNo = relNo;
		this.relDate = relDate;
		this.empNo = empNo;
		this.empDTO = empDTO;
	}

	public int getRelNo() {
		return relNo;
	}

	public void setRelNo(int relNo) {
		this.relNo = relNo;
	}

	public java.sql.Date getRelDate() {
		return relDate;
	}

	public void setRelDate(java.sql.Date relDate) {
		this.relDate = relDate;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public EmpDTO getEmpDTO() {
		return empDTO;
	}

	public void setEmpDTO(EmpDTO empDTO) {
		this.empDTO = empDTO;
	}

	@Override
	public String toString() {
		return "RelListAndEmpDTO [relNo=" + relNo + ", relDate=" + relDate + ", empNo=" + empNo + ", empDTO=" + empDTO
				+ "]";
	}
	
	
	
}

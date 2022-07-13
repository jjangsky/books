package com.bukkeubook.book.manage.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_APP_VACATION")
public class AppVacationAndEmp implements Serializable{

	private static final long serialVersionUID = 2528362317115288909L;
	
	/* DB 자료형 */
	
//	VAC_NO			NUMBER					신청서번호(PK)
//	VAC_START_DATE	DATE					휴가시작일
//	VAC_END_DATE	DATE					휴가종료일
//	EMP_NO			NUMBER					사원번호
//	EMP_NAME		NVARCHAR2(31 CHAR)		사원명

	
//	VAC_END_DATE	DATE				휴가종료일
//	VAC_START_DATE	DATE				휴가시작일
//	VAC_STATUS		NVARCHAR2(15 CHAR)	휴가상태
//	VAC_REASON		NVARCHAR2(255 CHAR)	휴가사유
//	VAC_APP_NO		DATE				신청일자
//	VAC_NO			NUMBER				신청서번호
//	EMP_NO			NUMBER				사원번호
//	VAC_EMER		VARCHAR2(31 BYTE)	비상연락처	

	@Id
	@Column(name = "VAC_NO")
	private int vacNo;
	
	@Column(name = "VAC_START_DATE")
	private java.sql.Date vacStartDate;
	
	@Column(name = "VAC_END_DATE")
	private java.sql.Date vacEndDate;
	
	@ManyToOne
	@JoinColumn(name = "EMP_NO")
	private EmpAndDept emp;

	public AppVacationAndEmp() {
		super();
	}

	public AppVacationAndEmp(int vacNo, Date vacStartDate, Date vacEndDate, EmpAndDept emp) {
		super();
		this.vacNo = vacNo;
		this.vacStartDate = vacStartDate;
		this.vacEndDate = vacEndDate;
		this.emp = emp;
	}

	public int getVacNo() {
		return vacNo;
	}

	public void setVacNo(int vacNo) {
		this.vacNo = vacNo;
	}

	public java.sql.Date getVacStartDate() {
		return vacStartDate;
	}

	public void setVacStartDate(java.sql.Date vacStartDate) {
		this.vacStartDate = vacStartDate;
	}

	public java.sql.Date getVacEndDate() {
		return vacEndDate;
	}

	public void setVacEndDate(java.sql.Date vacEndDate) {
		this.vacEndDate = vacEndDate;
	}

	public EmpAndDept getEmp() {
		return emp;
	}

	public void setEmp(EmpAndDept emp) {
		this.emp = emp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AppVacationAndEmp [vacNo=" + vacNo + ", vacStartDate=" + vacStartDate + ", vacEndDate=" + vacEndDate
				+ ", emp=" + emp + "]";
	}

	
}

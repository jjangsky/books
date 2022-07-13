package com.bukkeubook.book.manage.model.dto.joinDTO;

import java.io.Serializable;
import java.sql.Date;

public class CancleVacAndAppVacDTO implements Serializable{

	private static final long serialVersionUID = 6672157616918668876L;

//	VAC_CANC_NO		NUMBER				취소신청서번호
//	VAC_CANC_DATE	DATE				신청일자
//	VAC_CANC_REASON	NVARCHAR2(255 CHAR)	취소사유
//	VAC_CANC_STATUS	NVARCHAR2(15 CHAR)	문서상태
//	EMP_NO			NUMBER				사원번호
	
	private int vacCancNo;
	private java.sql.Date vacCancDate;
	private String vacCancReason;
	private String vacCancStatus;
	private int empNo;
	private AppVacationAndEmpDTO appVacAndEmp;
	
	public CancleVacAndAppVacDTO() {
	}
	
	public CancleVacAndAppVacDTO(int vacCancNo, Date vacCancDate, String vacCancReason, String vacCancStatus, int empNo,
			AppVacationAndEmpDTO appVacAndEmp) {
		this.vacCancNo = vacCancNo;
		this.vacCancDate = vacCancDate;
		this.vacCancReason = vacCancReason;
		this.vacCancStatus = vacCancStatus;
		this.empNo = empNo;
		this.appVacAndEmp = appVacAndEmp;
	}
	public int getVacCancNo() {
		return vacCancNo;
	}
	public void setVacCancNo(int vacCancNo) {
		this.vacCancNo = vacCancNo;
	}
	public java.sql.Date getVacCancDate() {
		return vacCancDate;
	}
	public void setVacCancDate(java.sql.Date vacCancDate) {
		this.vacCancDate = vacCancDate;
	}
	public String getVacCancReason() {
		return vacCancReason;
	}
	public void setVacCancReason(String vacCancReason) {
		this.vacCancReason = vacCancReason;
	}
	public String getVacCancStatus() {
		return vacCancStatus;
	}
	public void setVacCancStatus(String vacCancStatus) {
		this.vacCancStatus = vacCancStatus;
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public AppVacationAndEmpDTO getAppVacAndEmp() {
		return appVacAndEmp;
	}
	public void setAppVacAndEmp(AppVacationAndEmpDTO appVacAndEmp) {
		this.appVacAndEmp = appVacAndEmp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "CancleVacAndAppVacDTO [vacCancNo=" + vacCancNo + ", vacCancDate=" + vacCancDate + ", vacCancReason="
				+ vacCancReason + ", vacCancStatus=" + vacCancStatus + ", empNo=" + empNo + ", appVacAndEmp="
				+ appVacAndEmp + "]";
	}
	
}

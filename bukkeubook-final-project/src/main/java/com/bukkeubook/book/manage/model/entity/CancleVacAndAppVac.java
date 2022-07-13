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
@Table(name = "TBL_CANCEL_VACATION")
public class CancleVacAndAppVac implements Serializable {

	private static final long serialVersionUID = -8553618717975671653L;
	
	/* DB 자료형 */
	
//	VAC_CANC_NO		NUMBER				취소신청서번호
//	VAC_CANC_DATE	DATE				신청일자
//  EMP_JOB_CODE   NVARCHAR2(31 CHAR)  	직급  (v)
//	EMP_NAME		NVARCHAR2(31 CHAR)	사원명 (v)
//	VAC_CANC_STATUS	NVARCHAR2(15 CHAR)	문서상태
//	VAC_CANC_REASON	NVARCHAR2(255 CHAR)	취소사유
//	EMP_NO			NUMBER				사원번호(v)
	
	@Id
	@Column(name = "VAC_CANC_NO")
	private int vacCancNo;
	
	@Column(name = "VAC_CANC_DATE")
	private java.sql.Date vacCancDate;
	
	@Column(name = "VAC_CANC_STATUS")
	private String vacCancStatus;
	
	@Column(name = "VAC_CANC_REASON")
	private String vacCancReason;
	
	@ManyToOne
	@JoinColumn(name = "EMP_NO")
	private AppVacationAndEmp empNo;

	public CancleVacAndAppVac() {
	}

	public CancleVacAndAppVac(int vacCancNo, Date vacCancDate, String vacCancStatus, String vacCancReason,
			AppVacationAndEmp empNo) {
		this.vacCancNo = vacCancNo;
		this.vacCancDate = vacCancDate;
		this.vacCancStatus = vacCancStatus;
		this.vacCancReason = vacCancReason;
		this.empNo = empNo;
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

	public String getVacCancStatus() {
		return vacCancStatus;
	}

	public void setVacCancStatus(String vacCancStatus) {
		this.vacCancStatus = vacCancStatus;
	}

	public String getVacCancReason() {
		return vacCancReason;
	}

	public void setVacCancReason(String vacCancReason) {
		this.vacCancReason = vacCancReason;
	}

	public AppVacationAndEmp getEmpNo() {
		return empNo;
	}

	public void setEmpNo(AppVacationAndEmp empNo) {
		this.empNo = empNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CancleVacationAndEmp [vacCancNo=" + vacCancNo + ", vacCancDate=" + vacCancDate + ", vacCancStatus="
				+ vacCancStatus + ", vacCancReason=" + vacCancReason + ", empNo=" + empNo + "]";
	}
}

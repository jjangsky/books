package com.bukkeubook.book.document.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Document")
@Table(name = "TBL_DOCUMENT")
public class Document {
	
//	DOC_NO	NUMBER
//	DOC_TITLE	NVARCHAR2(255 CHAR)
//	DOC_CONT_1	NVARCHAR2(2000 CHAR)
//	DOC_DEL_YN	VARCHAR2(3 BYTE)
//	DOC_STATUS	NVARCHAR2(15 CHAR)
//	DOC_CR_DATE	DATE
//	DOC_MOD_DATE	DATE
//	APP_PATH_NO	NUMBER
//	DOC_CONT_2	NVARCHAR2(2000 CHAR)
//	EMP_NO	NUMBER
//	FORM_NO	NUMBER
//	DOC_FINAL_DAY	DATE
	
	@Id
	@Column(name = "DOC_NO")
	private int docNo;
	
	@Column(name = "DOC_TITLE")
	private String docTitle;
	
	@Column(name = "DOC_CONT_1")
	private String docCont1;
	
	@Column(name = "DOC_DEL_YN")
	private String docDelYn;
	
	@Column(name = "DOC_STATUS")
	private String docStatus;
	
	@Column(name = "DOC_CR_DATE")
	private java.sql.Date docCrDate;
	
	@Column(name = "DOC_MOD_DATE")
	private java.sql.Date docModDate;
	
	@Column(name = "APP_PATH_NO")
	private int appPathNo;
	
	@Column(name = "DOC_CONT_2")
	private String docCont2;
	
	@Column(name = "EMP_NO")
	private int empNo;
	
	@Column(name = "FORM_NO")
	private int formNo;
	
	@Column(name = "DOC_FINAL_DAY")
	private java.sql.Date docFinalDay;

	public Document() {
		super();
	}

	public Document(int docNo, String docTitle, String docCont1, String docDelYn, String docStatus, Date docCrDate,
			Date docModDate, int appPathNo, String docCont2, int empNo, int formNo, Date docFinalDay) {
		super();
		this.docNo = docNo;
		this.docTitle = docTitle;
		this.docCont1 = docCont1;
		this.docDelYn = docDelYn;
		this.docStatus = docStatus;
		this.docCrDate = docCrDate;
		this.docModDate = docModDate;
		this.appPathNo = appPathNo;
		this.docCont2 = docCont2;
		this.empNo = empNo;
		this.formNo = formNo;
		this.docFinalDay = docFinalDay;
	}

	public int getDocNo() {
		return docNo;
	}

	public void setDocNo(int docNo) {
		this.docNo = docNo;
	}

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getDocCont1() {
		return docCont1;
	}

	public void setDocCont1(String docCont1) {
		this.docCont1 = docCont1;
	}

	public String getDocDelYn() {
		return docDelYn;
	}

	public void setDocDelYn(String docDelYn) {
		this.docDelYn = docDelYn;
	}

	public String getDocStatus() {
		return docStatus;
	}

	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}

	public java.sql.Date getDocCrDate() {
		return docCrDate;
	}

	public void setDocCrDate(java.sql.Date docCrDate) {
		this.docCrDate = docCrDate;
	}

	public java.sql.Date getDocModDate() {
		return docModDate;
	}

	public void setDocModDate(java.sql.Date docModDate) {
		this.docModDate = docModDate;
	}

	public int getAppPathNo() {
		return appPathNo;
	}

	public void setAppPathNo(int appPathNo) {
		this.appPathNo = appPathNo;
	}

	public String getDocCont2() {
		return docCont2;
	}

	public void setDocCont2(String docCont2) {
		this.docCont2 = docCont2;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public int getFormNo() {
		return formNo;
	}

	public void setFormNo(int formNo) {
		this.formNo = formNo;
	}

	public java.sql.Date getDocFinalDay() {
		return docFinalDay;
	}

	public void setDocFinalDay(java.sql.Date docFinalDay) {
		this.docFinalDay = docFinalDay;
	}

	@Override
	public String toString() {
		return "Document [docNo=" + docNo + ", docTitle=" + docTitle + ", docCont1=" + docCont1 + ", docDelYn="
				+ docDelYn + ", docStatus=" + docStatus + ", docCrDate=" + docCrDate + ", docModDate=" + docModDate
				+ ", appPathNo=" + appPathNo + ", docCont2=" + docCont2 + ", empNo=" + empNo + ", formNo=" + formNo
				+ ", docFinalDay=" + docFinalDay + "]";
	}

	
	
	
}

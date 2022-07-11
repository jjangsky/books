package com.bukkeubook.book.document.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class DocumentDTO implements Serializable{
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
	
	private static final long serialVersionUID = 2384618590532492639L;
	private int docNo;
	private String docTitle;
	private String docCont1;
	private String docDelYn;
	private String docStatus;
	private java.sql.Date docCrDate;
	private java.sql.Date docModDate;
	private int appPathNo;
	private String docCont2;
	private int empNo;
	private int formNo;
	private java.sql.Date docFinalDay;
	public DocumentDTO() {
		super();
	}
	public DocumentDTO(int docNo, String docTitle, String docCont1, String docDelYn, String docStatus, Date docCrDate,
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "DocumentDTO [docNo=" + docNo + ", docTitle=" + docTitle + ", docCont1=" + docCont1 + ", docDelYn="
				+ docDelYn + ", docStatus=" + docStatus + ", docCrDate=" + docCrDate + ", docModDate=" + docModDate
				+ ", appPathNo=" + appPathNo + ", docCont2=" + docCont2 + ", empNo=" + empNo + ", formNo=" + formNo
				+ ", docFinalDay=" + docFinalDay + "]";
	}
	
	
	
}

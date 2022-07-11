package com.bukkeubook.book.document.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class ApprovalDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6494212300682273135L;

//	DOC_APP_NO	NUMBER
//	DOC_STATUS2	VARCHAR2(255 BYTE)
//	DOC_APP_DATE	DATE
//	DOC_NO	NUMBER
	
	private int docAppNo;
	private String docStatus2;
	private java.sql.Date docAppDate;
	private int docNo;
	public ApprovalDTO() {
		super();
	}
	public ApprovalDTO(int docAppNo, String docStatus2, Date docAppDate, int docNo) {
		super();
		this.docAppNo = docAppNo;
		this.docStatus2 = docStatus2;
		this.docAppDate = docAppDate;
		this.docNo = docNo;
	}
	public int getDocAppNo() {
		return docAppNo;
	}
	public void setDocAppNo(int docAppNo) {
		this.docAppNo = docAppNo;
	}
	public String getDocStatus2() {
		return docStatus2;
	}
	public void setDocStatus2(String docStatus2) {
		this.docStatus2 = docStatus2;
	}
	public java.sql.Date getDocAppDate() {
		return docAppDate;
	}
	public void setDocAppDate(java.sql.Date docAppDate) {
		this.docAppDate = docAppDate;
	}
	public int getDocNo() {
		return docNo;
	}
	public void setDocNo(int docNo) {
		this.docNo = docNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ApprovalDTO [docAppNo=" + docAppNo + ", docStatus2=" + docStatus2 + ", docAppDate=" + docAppDate
				+ ", docNo=" + docNo + "]";
	}
	
	
}

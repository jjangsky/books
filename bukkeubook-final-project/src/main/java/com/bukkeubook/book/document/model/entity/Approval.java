package com.bukkeubook.book.document.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Approval")
@Table(name = "TBL_APPROVAL")
public class Approval{

//	DOC_APP_NO	NUMBER
//	DOC_STATUS2	VARCHAR2(255 BYTE)
//	DOC_APP_DATE	DATE
//	DOC_NO	NUMBER
	
	@Id
	@Column(name = "DOC_APP_NO")
	private int docAppNo;
	
	@Column(name = "DOC_STATUS2")
	private String docStatus2;
	
	@Column(name = "DOC_APP_DATE")
	private java.sql.Date docAppDate;
	
	@Column(name = "DOC_NO")
	private int docNo;

	public Approval() {
		super();
	}

	public Approval(int docAppNo, String docStatus2, Date docAppDate, int docNo) {
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

	@Override
	public String toString() {
		return "Approval [docAppNo=" + docAppNo + ", docStatus2=" + docStatus2 + ", docAppDate=" + docAppDate
				+ ", docNo=" + docNo + "]";
	}
	
	
	
}

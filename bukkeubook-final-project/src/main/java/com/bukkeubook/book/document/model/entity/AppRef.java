package com.bukkeubook.book.document.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "AppRef")
@Table(name = "TBL_APP_REF")
public class AppRef{

//	REF_NO	NUMBER
//	DOC_NO	NUMBER
//	EMP_NO	NUMBER
	@Id
	@Column(name = "REF_NO")
	private int refNo;
	
	@Column(name = "DOC_NO")
	private int docNo;
	
	@Column(name = "EMP_NO")
	private int empNo;

	public AppRef() {
		super();
	}

	public AppRef(int refNo, int docNo, int empNo) {
		super();
		this.refNo = refNo;
		this.docNo = docNo;
		this.empNo = empNo;
	}

	public int getRefNo() {
		return refNo;
	}

	public void setRefNo(int refNo) {
		this.refNo = refNo;
	}

	public int getDocNo() {
		return docNo;
	}

	public void setDocNo(int docNo) {
		this.docNo = docNo;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	@Override
	public String toString() {
		return "AppRef [refNo=" + refNo + ", docNo=" + docNo + ", empNo=" + empNo + "]";
	}
	
	
}

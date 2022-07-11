package com.bukkeubook.book.document.model.dto;

import java.io.Serializable;

public class AppRefDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6161676259331614456L;

//	REF_NO	NUMBER
//	DOC_NO	NUMBER
//	EMP_NO	NUMBER
	
	private int refNo;
	private int docNo;
	private int empNo;
	public AppRefDTO() {
		super();
	}
	public AppRefDTO(int refNo, int docNo, int empNo) {
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AppRefDTO [refNo=" + refNo + ", docNo=" + docNo + ", empNo=" + empNo + "]";
	}
	
	
}

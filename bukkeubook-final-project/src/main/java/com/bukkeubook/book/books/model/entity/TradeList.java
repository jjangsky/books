package com.bukkeubook.book.books.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="ReleaseBook")
@Table(name="TBL_REL_BK_LIST")
public class TradeList implements Serializable{

	private static final long serialVersionUID = -3608341261312641532L;

	/*
	 * DB 자료형
	 * 
	 * TL_NO	NUMBER	거래번호
		TL_DATE	DATE	거래일자
		TL_AMOUNT	NUMBER	도서수량
		TL_DETAIL	NVARCHAR2(255 CHAR)	상세내용
		CNT_NO	NUMBER	거래처번호
		BK_NO	NUMBER	도서코드
		EMP_NO	NUMBER	사원번호
	 */
	@Id
	@Column(name="TL_NO")
	private int tlNo;				// 거래번호
	
	@Column(name="TL_DATE")
	private java.sql.Date tlDate;	// 거래일자
	
	@Column(name="TL_AMOUNT")
	private int tlAmount;			// 도서수량
	
	@Column(name="TL_DETAIL")
	private String tlDetail;		// 상세내용
	
	@Column(name="CNT_NO")
	private int cntNo;				// 거래처번호
	
	@Column(name="BK_NO")
	private int bkNo;				// 도서코드
	
	@Column(name="EMP_NO")
	private int empNo;				// 사원번호

	public TradeList() {
	}

	public TradeList(int tlNo, Date tlDate, int tlAmount, String tlDetail, int cntNo, int bkNo, int empNo) {
		this.tlNo = tlNo;
		this.tlDate = tlDate;
		this.tlAmount = tlAmount;
		this.tlDetail = tlDetail;
		this.cntNo = cntNo;
		this.bkNo = bkNo;
		this.empNo = empNo;
	}

	public int getTlNo() {
		return tlNo;
	}

	public void setTlNo(int tlNo) {
		this.tlNo = tlNo;
	}

	public java.sql.Date getTlDate() {
		return tlDate;
	}

	public void setTlDate(java.sql.Date tlDate) {
		this.tlDate = tlDate;
	}

	public int getTlAmount() {
		return tlAmount;
	}

	public void setTlAmount(int tlAmount) {
		this.tlAmount = tlAmount;
	}

	public String getTlDetail() {
		return tlDetail;
	}

	public void setTlDetail(String tlDetail) {
		this.tlDetail = tlDetail;
	}

	public int getCntNo() {
		return cntNo;
	}

	public void setCntNo(int cntNo) {
		this.cntNo = cntNo;
	}

	public int getBkNo() {
		return bkNo;
	}

	public void setBkNo(int bkNo) {
		this.bkNo = bkNo;
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
		return "TradeList [tlNo=" + tlNo + ", tlDate=" + tlDate + ", tlAmount=" + tlAmount + ", tlDetail=" + tlDetail
				+ ", cntNo=" + cntNo + ", bkNo=" + bkNo + ", empNo=" + empNo + "]";
	}
	
}









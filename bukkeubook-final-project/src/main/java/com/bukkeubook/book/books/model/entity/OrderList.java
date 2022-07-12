package com.bukkeubook.book.books.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="OrderList")
@Table(name="TBL_ORDER_LIST")
public class OrderList implements Serializable{

	private static final long serialVersionUID = -7371954659998907223L;

	/*
	 * DB 자료형
	 * 
	 * OR_NO	NUMBER	발주번호
		OR_DATE	DATE	등록날짜
		OR_APPR_YN	VARCHAR2(6 BYTE)	승인현황
		OR_AMOUNT	NUMBER	발주수량
		CNT_NO	NUMBER	거래처번호
		BK_NO	VARCHAR2(100 BYTE)	도서코드
		EMP_NO	NUMBER	사원번호
		OR_APP_DATE	DATE	승인날짜
	 */
	@Id
	@Column(name="OR_NO")
	private int orNo;				// 발주번호
	
	@Column(name="OR_DATE")
	private java.sql.Date orDate;	// 등록날짜
	
	@Column(name="OR_APPR_YN")
	private String orApprYn;		// 승인현황
	
	@Column(name="OR_AMOUNT")
	private int orAmount;			// 발주수량
	
	@Column(name="CNT_NO")
	private int cntNo;				// 거래처번호
	
	@Column(name="BK_NO")
	private String bkNo;				// 도서코드
	
	@Column(name="EMP_NO")
	private int empNo;				// 사원번호
	
	@Column(name="OR_APP_DATE")
	private java.sql.Date orAppDate;	// 승인날짜

	public OrderList() {
	}

	public OrderList(int orNo, Date orDate, String orApprYn, int orAmount, int cntNo, String bkNo, int empNo,
			Date orAppDate) {
		this.orNo = orNo;
		this.orDate = orDate;
		this.orApprYn = orApprYn;
		this.orAmount = orAmount;
		this.cntNo = cntNo;
		this.bkNo = bkNo;
		this.empNo = empNo;
		this.orAppDate = orAppDate;
	}

	public int getOrNo() {
		return orNo;
	}

	public void setOrNo(int orNo) {
		this.orNo = orNo;
	}

	public java.sql.Date getOrDate() {
		return orDate;
	}

	public void setOrDate(java.sql.Date orDate) {
		this.orDate = orDate;
	}

	public String getOrApprYn() {
		return orApprYn;
	}

	public void setOrApprYn(String orApprYn) {
		this.orApprYn = orApprYn;
	}

	public int getOrAmount() {
		return orAmount;
	}

	public void setOrAmount(int orAmount) {
		this.orAmount = orAmount;
	}

	public int getCntNo() {
		return cntNo;
	}

	public void setCntNo(int cntNo) {
		this.cntNo = cntNo;
	}

	public String getBkNo() {
		return bkNo;
	}

	public void setBkNo(String bkNo) {
		this.bkNo = bkNo;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public java.sql.Date getOrAppDate() {
		return orAppDate;
	}

	public void setOrAppDate(java.sql.Date orAppDate) {
		this.orAppDate = orAppDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OrderList [orNo=" + orNo + ", orDate=" + orDate + ", orApprYn=" + orApprYn + ", orAmount=" + orAmount
				+ ", cntNo=" + cntNo + ", bkNo=" + bkNo + ", empNo=" + empNo + ", orAppDate=" + orAppDate + "]";
	}
	
}













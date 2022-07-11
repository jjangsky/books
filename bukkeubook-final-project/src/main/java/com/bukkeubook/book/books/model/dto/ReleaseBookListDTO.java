package com.bukkeubook.book.books.model.dto;

import java.io.Serializable;

public class ReleaseBookListDTO implements Serializable{

	private static final long serialVersionUID = 3162215724507161378L;

	/* 
	 * DB 자료형
	 * 
	 * REL_BK_CODE	NUMBER	목록번호
		BK_NO	NUMBER	도서코드
		REL_NO	NUMBER	출고번호
		REL_BK_AMOUNT	NUMBER	출고수량
	 */
	private int relBkCode;		// 목록번호
	private int bkNo;			// 도서코드
	private int relNo;			// 출고번호
	private int relBkAmount;	// 출고수량
	
	public ReleaseBookListDTO() {
	}

	public ReleaseBookListDTO(int relBkCode, int bkNo, int relNo, int relBkAmount) {
		this.relBkCode = relBkCode;
		this.bkNo = bkNo;
		this.relNo = relNo;
		this.relBkAmount = relBkAmount;
	}

	public int getRelBkCode() {
		return relBkCode;
	}

	public void setRelBkCode(int relBkCode) {
		this.relBkCode = relBkCode;
	}

	public int getBkNo() {
		return bkNo;
	}

	public void setBkNo(int bkNo) {
		this.bkNo = bkNo;
	}

	public int getRelNo() {
		return relNo;
	}

	public void setRelNo(int relNo) {
		this.relNo = relNo;
	}

	public int getRelBkAmount() {
		return relBkAmount;
	}

	public void setRelBkAmount(int relBkAmount) {
		this.relBkAmount = relBkAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ReleaseBookListDTO [relBkCode=" + relBkCode + ", bkNo=" + bkNo + ", relNo=" + relNo + ", relBkAmount="
				+ relBkAmount + "]";
	}
	
}

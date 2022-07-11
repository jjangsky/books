package com.bukkeubook.book.books.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="ReleaseBook")
@Table(name="TBL_REL_BK_LIST")
public class ReleaseBook implements Serializable{

	private static final long serialVersionUID = 3443782804801253842L;

	/* 
	 * DB 자료형
	 * 
	 * REL_BK_CODE	NUMBER	목록번호
		BK_NO	NUMBER	도서코드
		REL_NO	NUMBER	출고번호
		REL_BK_AMOUNT	NUMBER	출고수량
	 */
	@Id
	@Column(name="REL_BK_CODE")
	private int relBkCode;				// 목록번호
	
	@Column(name="BK_NO")
	private int bkNo;					// 도서코드
	
	@Column(name="REL_NO")
	private int relNo;					// 출고번호
	
	@Column(name="REL_BK_AMOUNT")
	private int relBkAmount;			// 출고수량

	public ReleaseBook() {
	}

	public ReleaseBook(int relBkCode, int bkNo, int relNo, int relBkAmount) {
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
		return "ReleaseBook [relBkCode=" + relBkCode + ", bkNo=" + bkNo + ", relNo=" + relNo + ", relBkAmount="
				+ relBkAmount + "]";
	}
	
}

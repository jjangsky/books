package com.bukkeubook.book.books.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="RelBkList")
@Table(name="TBL_REL_BK_LIST")
public class RelBkList {
//	REL_BK_CODE	NUMBER
//	BK_NO	VARCHAR2(100 BYTE)
//	REL_NO	NUMBER
//	REL_BK_AMOUNT	NUMBER
	
	@Id
	@Column(name="REL_BK_CODE")
	private int relBkCode;		// 목록번호
	
	@Column(name="BK_NO")
	private String bkNo;		// 도서코드
	
	@Column(name="REL_NO")
	private int relNo;			// 출고번호
	
	@Column(name="REL_BK_AMOUNT")
	private int relBkAmount;	// 출고수량

	public RelBkList() {
	}

	public RelBkList(int relBkCode, String bkNo, int relNo, int relBkAmount) {
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

	public String getBkNo() {
		return bkNo;
	}

	public void setBkNo(String bkNo) {
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

	@Override
	public String toString() {
		return "RelBkList [relBkCode=" + relBkCode + ", bkNo=" + bkNo + ", relNo=" + relNo + ", relBkAmount="
				+ relBkAmount + "]";
	}
	
	
}

package com.bukkeubook.book.books.model.dto;

import java.io.Serializable;

public class DamBookDTO implements Serializable{

	private static final long serialVersionUID = -2097923780466515781L;
	
	/* DB 자료형(TBL_DAM_BOOK) */
//	BK_NO	VARCHAR2(100 BYTE)	도서코드
//	DAM_AMOUNT	NUMBER	훼손수량
	private int bkNo;		// 도서코드
	private int damAmount;	// 훼손수량
	
	public DamBookDTO() {
	}

	public DamBookDTO(int damAmount, int bkNo) {
		this.damAmount = damAmount;
		this.bkNo = bkNo;
	}

	public int getDamAmount() {
		return damAmount;
	}

	public void setDamAmount(int damAmount) {
		this.damAmount = damAmount;
	}

	public int getBkNo() {
		return bkNo;
	}

	public void setBkNo(int bkNo) {
		this.bkNo = bkNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DamBookDTO [damAmount=" + damAmount + ", bkNo=" + bkNo + "]";
	}
	
}

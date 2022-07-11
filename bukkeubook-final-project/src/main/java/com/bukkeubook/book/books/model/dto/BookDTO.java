package com.bukkeubook.book.books.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class BookDTO implements Serializable{

	private static final long serialVersionUID = -2284216042772057397L;

	/*
	 * DB 자료형
	 * 
	 * BK_NO	NUMBER	도서코드
		BK_NAME	VARCHAR2(255 BYTE)	도서명
		BK_AUTHOR	VARCHAR2(255 BYTE)	저자
		BK_PUB	NVARCHAR2(255 CHAR)	출판사
		BK_PRICE	NUMBER	가격
		BK_STORE_ST	NUMBER	판매재고수량
		BK_WH_ST	NUMBER	창고재고수량
		BK_PUB_DATE	DATE	발행일
		BK_LAST_DATE	DATE	최근입고일
		BK_ISBN	VARCHAR2(31 BYTE)	ISBN
		BK_SELL_YN	VARCHAR2(3 BYTE)	판매여부
		BK_CATE	VARCHAR2(100 BYTE)	카테고리
	 */
	private int bkNo;					// 도서코드
	private String bkName;				// 도서명
	private String bkAuthor;			// 저자
	private String bkPub;				// 출판사
	private int bkPrice;				// 가격
	private int bkStoreSt;				// 판매재고수량
	private int bkWhSt;					// 창고재고수량
	private java.sql.Date bkPubDate;	// 발행일
	private java.sql.Date bkLastDate;	// 최근입고일
	private String bkIsbn;				// ISBN
	private String bkSellYn;			// 판매여부
	private String bkCate;				// 카테고리
	
	public BookDTO() {
	}

	public BookDTO(int bkNo, String bkName, String bkAuthor, String bkPub, int bkPrice, int bkStoreSt, int bkWhSt,
			Date bkPubDate, Date bkLastDate, String bkIsbn, String bkSellYn, String bkCate) {
		this.bkNo = bkNo;
		this.bkName = bkName;
		this.bkAuthor = bkAuthor;
		this.bkPub = bkPub;
		this.bkPrice = bkPrice;
		this.bkStoreSt = bkStoreSt;
		this.bkWhSt = bkWhSt;
		this.bkPubDate = bkPubDate;
		this.bkLastDate = bkLastDate;
		this.bkIsbn = bkIsbn;
		this.bkSellYn = bkSellYn;
		this.bkCate = bkCate;
	}

	public int getBkNo() {
		return bkNo;
	}

	public void setBkNo(int bkNo) {
		this.bkNo = bkNo;
	}

	public String getBkName() {
		return bkName;
	}

	public void setBkName(String bkName) {
		this.bkName = bkName;
	}

	public String getBkAuthor() {
		return bkAuthor;
	}

	public void setBkAuthor(String bkAuthor) {
		this.bkAuthor = bkAuthor;
	}

	public String getBkPub() {
		return bkPub;
	}

	public void setBkPub(String bkPub) {
		this.bkPub = bkPub;
	}

	public int getBkPrice() {
		return bkPrice;
	}

	public void setBkPrice(int bkPrice) {
		this.bkPrice = bkPrice;
	}

	public int getBkStoreSt() {
		return bkStoreSt;
	}

	public void setBkStoreSt(int bkStoreSt) {
		this.bkStoreSt = bkStoreSt;
	}

	public int getBkWhSt() {
		return bkWhSt;
	}

	public void setBkWhSt(int bkWhSt) {
		this.bkWhSt = bkWhSt;
	}

	public java.sql.Date getBkPubDate() {
		return bkPubDate;
	}

	public void setBkPubDate(java.sql.Date bkPubDate) {
		this.bkPubDate = bkPubDate;
	}

	public java.sql.Date getBkLastDate() {
		return bkLastDate;
	}

	public void setBkLastDate(java.sql.Date bkLastDate) {
		this.bkLastDate = bkLastDate;
	}

	public String getBkIsbn() {
		return bkIsbn;
	}

	public void setBkIsbn(String bkIsbn) {
		this.bkIsbn = bkIsbn;
	}

	public String getBkSellYn() {
		return bkSellYn;
	}

	public void setBkSellYn(String bkSellYn) {
		this.bkSellYn = bkSellYn;
	}

	public String getBkCate() {
		return bkCate;
	}

	public void setBkCate(String bkCate) {
		this.bkCate = bkCate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BookDTO [bkNo=" + bkNo + ", bkName=" + bkName + ", bkAuthor=" + bkAuthor + ", bkPub=" + bkPub
				+ ", bkPrice=" + bkPrice + ", bkStoreSt=" + bkStoreSt + ", bkWhSt=" + bkWhSt + ", bkPubDate="
				+ bkPubDate + ", bkLastDate=" + bkLastDate + ", bkIsbn=" + bkIsbn + ", bkSellYn=" + bkSellYn
				+ ", bkCate=" + bkCate + "]";
	}
	
}

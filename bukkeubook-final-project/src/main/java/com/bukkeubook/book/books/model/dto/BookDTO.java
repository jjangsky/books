package com.bukkeubook.book.books.model.dto;

import java.sql.Date;

public class BookDTO {
	
//	BK_NO	NUMBER
//	BK_NAME	VARCHAR2(255 BYTE)
//	BK_AUTHOR	VARCHAR2(255 BYTE)
//	BK_PUB	NVARCHAR2(255 CHAR)
//	BK_PRICE	NUMBER
//	BK_STORE_ST	NUMBER
//	BK_WH_ST	NUMBER
//	BK_PUB_DATE	DATE
//	BK_LAST_DATE	DATE
//	BK_ISBN	VARCHAR2(31 BYTE)
//	BK_SELL_YN	VARCHAR2(3 BYTE)
//	BK_CATE	VARCHAR2(100 BYTE)
	
	private int no;						// 도서코드
	private String name;				// 도서명
	private String author;				// 저자
	private String pub;					// 출판사
	private int price;					// 가격
	private int storeSt;				// 판매재고수량
	private int whSt;					// 창고재고수량
	private java.util.Date pubDate;		// 발행일
	private java.util.Date lastDate;	// 최근입고일
	private String isbn;				// ISBN
	private String sellYn;				// 판매여부
	private String cate;					// 카테고리
	
	public BookDTO() {
	}

	public BookDTO(int no, String name, String author, String pub, int price, int storeSt, int whSt,
			java.util.Date pubDate, java.util.Date lastDate, String isbn, String sellYn, String cate) {
		this.no = no;
		this.name = name;
		this.author = author;
		this.pub = pub;
		this.price = price;
		this.storeSt = storeSt;
		this.whSt = whSt;
		this.pubDate = pubDate;
		this.lastDate = lastDate;
		this.isbn = isbn;
		this.sellYn = sellYn;
		this.cate = cate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPub() {
		return pub;
	}

	public void setPub(String pub) {
		this.pub = pub;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStoreSt() {
		return storeSt;
	}

	public void setStoreSt(int storeSt) {
		this.storeSt = storeSt;
	}

	public int getWhSt() {
		return whSt;
	}

	public void setWhSt(int whSt) {
		this.whSt = whSt;
	}

	public java.util.Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(java.util.Date pubDate) {
		this.pubDate = pubDate;
	}

	public java.util.Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(java.util.Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getSellYn() {
		return sellYn;
	}

	public void setSellYn(String sellYn) {
		this.sellYn = sellYn;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	@Override
	public String toString() {
		return "BookDTO [no=" + no + ", name=" + name + ", author=" + author + ", pub=" + pub + ", price=" + price
				+ ", storeSt=" + storeSt + ", whSt=" + whSt + ", pubDate=" + pubDate + ", lastDate=" + lastDate
				+ ", isbn=" + isbn + ", sellYn=" + sellYn + ", cate=" + cate + "]";
	}
	
	
	
	
	
}

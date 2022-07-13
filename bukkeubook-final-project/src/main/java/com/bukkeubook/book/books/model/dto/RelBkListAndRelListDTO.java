package com.bukkeubook.book.books.model.dto;

public class RelBkListAndRelListDTO {
//	REL_BK_CODE	NUMBER
//	BK_NO	VARCHAR2(100 BYTE)
//	REL_NO	NUMBER
//	REL_BK_AMOUNT	NUMBER
	
	private int relBkCode;		// 목록번호
	private int relBkAmount;	// 출고수량
	private String bkNo;
	private int relNo;
	private RelListAndEmpDTO relListAndEmp;		// 출고번호
	private BookDTO book;		
	
	public RelBkListAndRelListDTO() {
	}

	public RelBkListAndRelListDTO(int relBkCode, int relBkAmount, String bkNo, int relNo, BookDTO book,
			RelListAndEmpDTO relListAndEmp) {
		super();
		this.relBkCode = relBkCode;
		this.relBkAmount = relBkAmount;
		this.bkNo = bkNo;
		this.relNo = relNo;
		this.book = book;
		this.relListAndEmp = relListAndEmp;
	}

	public int getRelBkCode() {
		return relBkCode;
	}

	public void setRelBkCode(int relBkCode) {
		this.relBkCode = relBkCode;
	}

	public int getRelBkAmount() {
		return relBkAmount;
	}

	public void setRelBkAmount(int relBkAmount) {
		this.relBkAmount = relBkAmount;
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

	public BookDTO getBook() {
		return book;
	}

	public void setBook(BookDTO book) {
		this.book = book;
	}

	public RelListAndEmpDTO getRelListAndEmp() {
		return relListAndEmp;
	}

	public void setRelListAndEmp(RelListAndEmpDTO relListAndEmp) {
		this.relListAndEmp = relListAndEmp;
	}

	@Override
	public String toString() {
		return "RelBkListAndRelListDTO [relBkCode=" + relBkCode + ", relBkAmount=" + relBkAmount + ", bkNo=" + bkNo
				+ ", relNo=" + relNo + ", book=" + book + ", relListAndEmp=" + relListAndEmp + "]";
	}
	
}

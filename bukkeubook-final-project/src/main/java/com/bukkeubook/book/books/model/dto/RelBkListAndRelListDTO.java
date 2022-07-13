package com.bukkeubook.book.books.model.dto;

public class RelBkListAndRelListDTO {
//	REL_BK_CODE	NUMBER
//	BK_NO	VARCHAR2(100 BYTE)
//	REL_NO	NUMBER
//	REL_BK_AMOUNT	NUMBER
	
	private int relBkCode;		// 목록번호
	private int relBkAmount;	// 출고수량
	private String no;
	private int relNo;
	private BookDTO bookDTO;		
	private RelListAndEmpDTO relListAndEmpDTO;		// 출고번호
	
	public RelBkListAndRelListDTO() {
	}

	public RelBkListAndRelListDTO(int relBkCode, int relBkAmount, String no, int relNo, BookDTO bookDTO,
			RelListAndEmpDTO relListAndEmpDTO) {
		this.relBkCode = relBkCode;
		this.relBkAmount = relBkAmount;
		this.no = no;
		this.relNo = relNo;
		this.bookDTO = bookDTO;
		this.relListAndEmpDTO = relListAndEmpDTO;
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

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public int getRelNo() {
		return relNo;
	}

	public void setRelNo(int relNo) {
		this.relNo = relNo;
	}

	public BookDTO getBookDTO() {
		return bookDTO;
	}

	public void setBookDTO(BookDTO bookDTO) {
		this.bookDTO = bookDTO;
	}

	public RelListAndEmpDTO getRelListAndEmpDTO() {
		return relListAndEmpDTO;
	}

	public void setRelListAndEmpDTO(RelListAndEmpDTO relListAndEmpDTO) {
		this.relListAndEmpDTO = relListAndEmpDTO;
	}

	@Override
	public String toString() {
		return "RelBkListAndRelListDTO [relBkCode=" + relBkCode + ", relBkAmount=" + relBkAmount + ", no=" + no
				+ ", relNo=" + relNo + ", bookDTO=" + bookDTO + ", relListAndEmpDTO=" + relListAndEmpDTO + "]";
	}

	
	

	
}

package com.bukkeubook.book.books.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TBL_REL_BK_LIST")
public class RelBkListAndRelList implements Serializable{
	private static final long serialVersionUID = 1L;

//	REL_BK_CODE	NUMBER
//	BK_NO	VARCHAR2(100 BYTE)
//	REL_NO	NUMBER
//	REL_BK_AMOUNT	NUMBER
	
	@Id
	@Column(name="REL_BK_CODE")
	private int relBkCode;		// 목록번호
	
	@ManyToOne
	@JoinColumn(name="BK_NO")
	private Book book;		// 도서코드
	
	@Column(name="REL_BK_AMOUNT")
	private int relBkAmount;	// 출고수량
	
	@ManyToOne
	@JoinColumn(name="REL_NO")
	private RelListAndEmp relListAndEmp;	// 출고번호

	public RelBkListAndRelList() {
	}

	public RelBkListAndRelList(int relBkCode, Book book, int relBkAmount, RelListAndEmp relListAndEmp) {
		this.relBkCode = relBkCode;
		this.book = book;
		this.relBkAmount = relBkAmount;
		this.relListAndEmp = relListAndEmp;
	}

	public int getRelBkCode() {
		return relBkCode;
	}

	public void setRelBkCode(int relBkCode) {
		this.relBkCode = relBkCode;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getRelBkAmount() {
		return relBkAmount;
	}

	public void setRelBkAmount(int relBkAmount) {
		this.relBkAmount = relBkAmount;
	}

	public RelListAndEmp getRelListAndEmp() {
		return relListAndEmp;
	}

	public void setRelListAndEmp(RelListAndEmp relListAndEmp) {
		this.relListAndEmp = relListAndEmp;
	}

	@Override
	public String toString() {
		return "RelBkListAndRelList [relBkCode=" + relBkCode + ", book=" + book + ", relBkAmount=" + relBkAmount
				+ ", relListAndEmp=" + relListAndEmp + "]";
	}
	
	
	
}

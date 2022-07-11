package com.bukkeubook.book.secretary.model.dto;

public class BoardCateDTO {
	
//	TBL_BOARD_CATE 전사게시판 카테고리 테이블
	
//	CATE_NO	NUMBER
//	CATE_NAME	NVARCHAR2(31 CHAR)
	
	private int no;
	private String name;
	
	public BoardCateDTO() {
	}

	public BoardCateDTO(int no, String name) {
		this.no = no;
		this.name = name;
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

	@Override
	public String toString() {
		return "BoardCateDTO [no=" + no + ", name=" + name + "]";
	}
	
	

}

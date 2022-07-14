package com.bukkeubook.book.document.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Document")
@Table(name = "TBL_DOCUMENT")
@SequenceGenerator(
		name = "DOCUMENT_SEQ_DOC_NO",
		sequenceName = "SEQ_DOC_NO",
		initialValue = 1,
		allocationSize = 1
)
public class Document {
	
//	DOC_NO	문서번호	NUMBER
//	EMP_NO	사원번호	NUMBER
//	DOC_TITLE	제목	VARCHAR2(500 BYTE)
//	TAG_CNT	태그내용	CLOB
//	CNT	작성내용	CLOB
//	WR_DATE	작성일자	DATE
//	DOC_STATUS	문서상태	VARCHAR2(30 BYTE)
//	FORM_NO	문서양식번호	NUMBER
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "DOCUMENT_SEQ_DOC_NO"
	)
	@Column(name = "DOC_NO")
	private int docNo;
	
	@Column(name = "EMP_NO")
	private int empNo;
	
	@Column(name = "DOC_TITLE")
	private String docTitle;
	
	@Column(name = "TAG_CNT")
	@Lob
	private String tagCnt;
	
	@Column(name = "CNT")
	@Lob
	private String cnt;
	
	@Column(name = "WR_DATE")
	private String wrDate;
	
	@Column(name = "DOC_STATUS")
	private String docStatus;
	
	@Column(name = "FORM_NO")
	private int formNo;

	public Document() {
		super();
	}

	public Document(int docNo, int empNo, String docTitle, String tagCnt, String cnt, String wrDate, String docStatus,
			int formNo) {
		super();
		this.docNo = docNo;
		this.empNo = empNo;
		this.docTitle = docTitle;
		this.tagCnt = tagCnt;
		this.cnt = cnt;
		this.wrDate = wrDate;
		this.docStatus = docStatus;
		this.formNo = formNo;
	}

	public int getDocNo() {
		return docNo;
	}

	public void setDocNo(int docNo) {
		this.docNo = docNo;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getTagCnt() {
		return tagCnt;
	}

	public void setTagCnt(String tagCnt) {
		this.tagCnt = tagCnt;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public String getWrDate() {
		return wrDate;
	}

	public void setWrDate(String wrDate) {
		this.wrDate = wrDate;
	}

	public String getDocStatus() {
		return docStatus;
	}

	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}

	public int getFormNo() {
		return formNo;
	}

	public void setFormNo(int formNo) {
		this.formNo = formNo;
	}

	@Override
	public String toString() {
		return "Document [docNo=" + docNo + ", empNo=" + empNo + ", docTitle=" + docTitle + ", tagCnt=" + tagCnt
				+ ", cnt=" + cnt + ", wrDate=" + wrDate + ", docStatus=" + docStatus + ", formNo=" + formNo + "]";
	}

	
}

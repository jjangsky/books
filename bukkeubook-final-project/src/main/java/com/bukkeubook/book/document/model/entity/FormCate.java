package com.bukkeubook.book.document.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "FormCate")
@Table(name = "TBL_FORM_CATE")
public class FormCate{

//	FORM_NO	NUMBER
//	FORM_NAME	NVARCHAR2(255 CHAR)
//	FORM_CONTENT	NVARCHAR2(2000 CHAR)
	
	@Id
	@Column(name = "FORM_NO")
	private int formNo;
	
	@Column(name = "FORM_NAME")
	private String formName;
	
	@Column(name = "FORM_CONTENT")
	private String formContent;

	public FormCate() {
		super();
	}

	public FormCate(int formNo, String formName, String formContent) {
		super();
		this.formNo = formNo;
		this.formName = formName;
		this.formContent = formContent;
	}

	public int getFormNo() {
		return formNo;
	}

	public void setFormNo(int formNo) {
		this.formNo = formNo;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getFormContent() {
		return formContent;
	}

	public void setFormContent(String formContent) {
		this.formContent = formContent;
	}

	@Override
	public String toString() {
		return "FormCate [formNo=" + formNo + ", formName=" + formName + ", formContent=" + formContent + "]";
	}
	
	
	
}

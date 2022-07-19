package com.bukkeubook.book.manage.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ProfPhotoAndEmp implements Serializable {

	private static final long serialVersionUID = 6053247747175670513L;
	
//	TBL_EMP 프로필 사진 테이블
//	
//	PHOTO_NO			NUMBER
//	PHOTO_ORIG_NAME		VARCHAR2(255 BYTE)
//	PHOTO_SAVED_NAME	VARCHAR2(255 BYTE)
//	PHOTO_SAVED_PATH	VARCHAR2(255 BYTE)
//	EMP_NO				NUMBER
	
	@Id
	@JoinColumn(name = "PHOTO_NO")
	private int photoNo;
	
	@Column(name = "PHOTO_ORIG_NAME")
	private String photoOrigName;
	
	@Column(name = "PHOTO_SAVED_NAME")
	private String photoSavedName;
	
	@Column(name = "PHOTO_SAVED_PATH")
	private String photoSavedPath;
	
	@ManyToOne
	@JoinColumn(name = "EMP_NO")
	private EmpAndDept empWithDept;

	public ProfPhotoAndEmp() {
	}

	public ProfPhotoAndEmp(int photoNo, String photoOrigName, String photoSavedName, String photoSavedPath,
			EmpAndDept empWithDept) {
		this.photoNo = photoNo;
		this.photoOrigName = photoOrigName;
		this.photoSavedName = photoSavedName;
		this.photoSavedPath = photoSavedPath;
		this.empWithDept = empWithDept;
	}

	public int getPhotoNo() {
		return photoNo;
	}

	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}

	public String getPhotoOrigName() {
		return photoOrigName;
	}

	public void setPhotoOrigName(String photoOrigName) {
		this.photoOrigName = photoOrigName;
	}

	public String getPhotoSavedName() {
		return photoSavedName;
	}

	public void setPhotoSavedName(String photoSavedName) {
		this.photoSavedName = photoSavedName;
	}

	public String getPhotoSavedPath() {
		return photoSavedPath;
	}

	public void setPhotoSavedPath(String photoSavedPath) {
		this.photoSavedPath = photoSavedPath;
	}

	public EmpAndDept getEmpWithDept() {
		return empWithDept;
	}

	public void setEmpWithDept(EmpAndDept empWithDept) {
		this.empWithDept = empWithDept;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ProfPhotoAndEmp [photoNo=" + photoNo + ", photoOrigName=" + photoOrigName + ", photoSavedName="
				+ photoSavedName + ", photoSavedPath=" + photoSavedPath + ", empWithDept=" + empWithDept + "]";
	}
	
	
}

package com.bukkeubook.book.manage.model.dto;

import java.io.Serializable;

public class ProfPhotoDTO implements Serializable{

	private static final long serialVersionUID = 2722032151555713650L;
	
//	PHOTO_NO	NUMBER						파일번호
//	PHOTO_ORIG_NAME	VARCHAR2(255 BYTE)		파일본명
//	PHOTO_SAVED_NAME	VARCHAR2(255 BYTE)	변경된파일명
//	PHOTO_STATE	VARCHAR2(31 BYTE)			파일상태
//	PHOTO_SAVED_PATH	VARCHAR2(255 BYTE)	저장경로
//	THUMB_NAME	VARCHAR2(255 BYTE)			썸네일파일명
//	THUMB_PATH	VARCHAR2(255 BYTE)			썸네일파일저장경로
//	PHOTO_EXT	VARCHAR2(15 BYTE)			확장자
//	EMP_NO	NUMBER							사원번호
	
	private int photoNo;
	private String photoOrigName;
	private String photoSavedName;
	private String photoState;
	private String photoSavedPath;
	private String thumbName;
	private String thumbPath;
	private String photoExt;
	private int empNo;
	public ProfPhotoDTO() {
		super();
	}
	public ProfPhotoDTO(int photoNo, String photoOrigName, String photoSavedName, String photoState,
			String photoSavedPath, String thumbName, String thumbPath, String photoExt, int empNo) {
		super();
		this.photoNo = photoNo;
		this.photoOrigName = photoOrigName;
		this.photoSavedName = photoSavedName;
		this.photoState = photoState;
		this.photoSavedPath = photoSavedPath;
		this.thumbName = thumbName;
		this.thumbPath = thumbPath;
		this.photoExt = photoExt;
		this.empNo = empNo;
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
	public String getPhotoState() {
		return photoState;
	}
	public void setPhotoState(String photoState) {
		this.photoState = photoState;
	}
	public String getPhotoSavedPath() {
		return photoSavedPath;
	}
	public void setPhotoSavedPath(String photoSavedPath) {
		this.photoSavedPath = photoSavedPath;
	}
	public String getThumbName() {
		return thumbName;
	}
	public void setThumbName(String thumbName) {
		this.thumbName = thumbName;
	}
	public String getThumbPath() {
		return thumbPath;
	}
	public void setThumbPath(String thumbPath) {
		this.thumbPath = thumbPath;
	}
	public String getPhotoExt() {
		return photoExt;
	}
	public void setPhotoExt(String photoExt) {
		this.photoExt = photoExt;
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ProfPhotoDTO [photoNo=" + photoNo + ", photoOrigName=" + photoOrigName + ", photoSavedName="
				+ photoSavedName + ", photoState=" + photoState + ", photoSavedPath=" + photoSavedPath + ", thumbName="
				+ thumbName + ", thumbPath=" + thumbPath + ", photoExt=" + photoExt + ", empNo=" + empNo + "]";
	}
	
	
	
}

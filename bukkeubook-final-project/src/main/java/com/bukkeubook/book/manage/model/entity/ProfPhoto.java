package com.bukkeubook.book.manage.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="ProfPhoto")
@Table(name = "TBL_PROF_PHOTO")
@SequenceGenerator(
		name = "PROF_PHOTO_SEQ_GENERATOR",  
		sequenceName = "SEQ_PHOTO_NO",
		initialValue = 1,
		allocationSize = 1
)
public class ProfPhoto {
	
//	PHOTO_NO	NUMBER	파일번호
//	PHOTO_ORIG_NAME	VARCHAR2(255 BYTE)	파일본명
//	PHOTO_SAVED_NAME	VARCHAR2(255 BYTE)	변경된파일명
//	PHOTO_STATE	VARCHAR2(31 BYTE)	파일상태
//	PHOTO_SAVED_PATH	VARCHAR2(255 BYTE)	저장경로
//	THUMB_NAME	VARCHAR2(255 BYTE)	썸네일파일명
//	THUMB_PATH	VARCHAR2(255 BYTE)	썸네일파일저장경로
//	PHOTO_EXT	VARCHAR2(15 BYTE)	확장자
//	EMP_NO	NUMBER	사원번호
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "PROF_PHOTO_SEQ_GENERATOR"
	)
	@Column(name = "SAL_NO")
	private int photoNo;
	
	@Column(name = "PHOTO_ORIG_NAME")
	private String photoOrigName;
	
	@Column(name = "PHOTO_SAVED_NAME")
	private String photoSavedName;
	
	@Column(name = "PHOTO_STATE")
	private String photoState;
	
	@Column(name = "PHOTO_SAVED_PATH")
	private String photoSavedPath;
	
	@Column(name = "THUMB_NAME")
	private String thumbName;
	
	@Column(name = "THUMB_PATH")
	private String thumbPath;
	
	@Column(name = "PHOTO_EXT")
	private String photoExt;
	
	@Column(name = "EMP_NO")
	private int empNo;

	public ProfPhoto() {
		super();
	}

	public ProfPhoto(int photoNo, String photoOrigName, String photoSavedName, String photoState, String photoSavedPath,
			String thumbName, String thumbPath, String photoExt, int empNo) {
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

	@Override
	public String toString() {
		return "ProfPhoto [photoNo=" + photoNo + ", photoOrigName=" + photoOrigName + ", photoSavedName="
				+ photoSavedName + ", photoState=" + photoState + ", photoSavedPath=" + photoSavedPath + ", thumbName="
				+ thumbName + ", thumbPath=" + thumbPath + ", photoExt=" + photoExt + ", empNo=" + empNo + "]";
	}
	
	

}

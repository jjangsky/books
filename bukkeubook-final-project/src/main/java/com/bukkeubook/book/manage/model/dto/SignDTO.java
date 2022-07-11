package com.bukkeubook.book.manage.model.dto;

import java.io.Serializable;

public class SignDTO implements Serializable{

	private static final long serialVersionUID = 695473486482200469L;
//	SIGN_NAME	VARCHAR2(255 BYTE)		파일본명
//	SIGN_SAVED_NAME	VARCHAR2(255 BYTE)	변경된파일명
//	SIGN_STATE	VARCHAR2(31 BYTE)		파일상태
//	SIGN_PATH	VARCHAR2(255 BYTE)		저장경로
//	SIGN_EXT	VARCHAR2(15 BYTE)		확장자
//	EMP_NO	NUMBER	사원번호
	
	private String signName;
	private String signSavedName;
	private String signState; 
	private String signPath;
	private String signExt;
	private int empNo;
	public SignDTO() {
		super();
	}
	public SignDTO(String signName, String signSavedName, String signState, String signPath, String signExt,
			int empNo) {
		super();
		this.signName = signName;
		this.signSavedName = signSavedName;
		this.signState = signState;
		this.signPath = signPath;
		this.signExt = signExt;
		this.empNo = empNo;
	}
	public String getSignName() {
		return signName;
	}
	public void setSignName(String signName) {
		this.signName = signName;
	}
	public String getSignSavedName() {
		return signSavedName;
	}
	public void setSignSavedName(String signSavedName) {
		this.signSavedName = signSavedName;
	}
	public String getSignState() {
		return signState;
	}
	public void setSignState(String signState) {
		this.signState = signState;
	}
	public String getSignPath() {
		return signPath;
	}
	public void setSignPath(String signPath) {
		this.signPath = signPath;
	}
	public String getSignExt() {
		return signExt;
	}
	public void setSignExt(String signExt) {
		this.signExt = signExt;
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
		return "SignDTO [signName=" + signName + ", signSavedName=" + signSavedName + ", signState=" + signState
				+ ", signPath=" + signPath + ", signExt=" + signExt + ", empNo=" + empNo + "]";
	}
	
	
	

}

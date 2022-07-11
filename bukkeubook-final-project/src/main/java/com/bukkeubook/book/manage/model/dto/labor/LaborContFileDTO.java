package com.bukkeubook.book.manage.model.dto.labor;

import java.io.Serializable;

public class LaborContFileDTO implements Serializable {
	
	private static final long serialVersionUID = 4956459758570618817L;
	
//	C_FILE_NO	NUMBER	No		1	파일번호
//	C_FILE_ORIG_NAME	VARCHAR2(255 BYTE)	No		2	파일본명
//	C_FILE_SAVED_NAME	VARCHAR2(255 BYTE)	No		3	변경된파일명
//	C_FILE_STATE	VARCHAR2(31 BYTE)	No		4	파일상태
//	C_FILE_PATH	VARCHAR2(255 BYTE)	No		5	저장경로
//	C_FILE_EXT	VARCHAR2(15 BYTE)	No		6	확장자
//	CONT_NO	NUMBER	No		7	근로계약번호
	
	private int cfileNo;				// 파일번호
	private String cfileOrigName;		// 파일본명
	private String cfileSavedName;		// 변경된파일명
	private String cfileState;			// 파일상태
	private String cfilePath;			// 저장경로
	private String cfileExt;			// 확장자
	private int contNo;					// 근로계약번호
	
	public LaborContFileDTO() {
	}
	
	public LaborContFileDTO(int cfileNo, String cfileOrigName, String cfileSavedName, String cfileState,
			String cfilePath, String cfileExt, int contNo) {
		this.cfileNo = cfileNo;
		this.cfileOrigName = cfileOrigName;
		this.cfileSavedName = cfileSavedName;
		this.cfileState = cfileState;
		this.cfilePath = cfilePath;
		this.cfileExt = cfileExt;
		this.contNo = contNo;
	}
	
	public int getCfileNo() {
		return cfileNo;
	}
	public void setCfileNo(int cfileNo) {
		this.cfileNo = cfileNo;
	}
	public String getCfileOrigName() {
		return cfileOrigName;
	}
	public void setCfileOrigName(String cfileOrigName) {
		this.cfileOrigName = cfileOrigName;
	}
	public String getCfileSavedName() {
		return cfileSavedName;
	}
	public void setCfileSavedName(String cfileSavedName) {
		this.cfileSavedName = cfileSavedName;
	}
	public String getCfileState() {
		return cfileState;
	}
	public void setCfileState(String cfileState) {
		this.cfileState = cfileState;
	}
	public String getCfilePath() {
		return cfilePath;
	}
	public void setCfilePath(String cfilePath) {
		this.cfilePath = cfilePath;
	}
	public String getCfileExt() {
		return cfileExt;
	}
	public void setCfileExt(String cfileExt) {
		this.cfileExt = cfileExt;
	}
	public int getContNo() {
		return contNo;
	}
	public void setContNo(int contNo) {
		this.contNo = contNo;
	}
	
	@Override
	public String toString() {
		return "LaborContFileDTO [cfileNo=" + cfileNo + ", cfileOrigName=" + cfileOrigName + ", cfileSavedName="
				+ cfileSavedName + ", cfileState=" + cfileState + ", cfilePath=" + cfilePath + ", cfileExt=" + cfileExt
				+ ", contNo=" + contNo + "]";
	}
	
}
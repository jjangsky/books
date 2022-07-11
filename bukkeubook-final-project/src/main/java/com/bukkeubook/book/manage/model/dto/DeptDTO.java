package com.bukkeubook.book.manage.model.dto;

import java.io.Serializable;

public class DeptDTO implements Serializable{

	private static final long serialVersionUID = 6137359876998934863L;
	
	private int deptCode;			// 부서코드
	private String deptName;		// 부서명
	private String deptRepPhone;	// 대표번호
	
	/*
	 * DEPT_CODE NUMBER No 1 부서코드 DEPT_NAME NVARCHAR2(31 CHAR) No 2 부서명
	 * DEPT_REP_PHONE VARCHAR2(15 BYTE) No 3 대표번호
	 */
	
	public DeptDTO() {
	}
	
	public DeptDTO(int deptCode, String deptName, String deptRepPhone) {
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.deptRepPhone = deptRepPhone;
	}
	
	public int getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(int deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptRepPhone() {
		return deptRepPhone;
	}
	public void setDeptRepPhone(String deptRepPhone) {
		this.deptRepPhone = deptRepPhone;
	}
	
	@Override
	public String toString() {
		return "DeptDTO [deptCode=" + deptCode + ", deptName=" + deptName + ", deptRepPhone=" + deptRepPhone + "]";
	}
	
}

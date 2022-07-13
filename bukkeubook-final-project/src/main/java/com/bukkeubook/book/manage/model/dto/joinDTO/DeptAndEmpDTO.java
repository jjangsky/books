package com.bukkeubook.book.manage.model.dto.joinDTO;

import java.util.ArrayList;
import java.util.List;

public class DeptAndEmpDTO {
	
//	DEPT_CODE		NUMBER				부서코드
//	DEPT_NAME		NVARCHAR2(31 CHAR)	부서명
//	DEPT_REP_PHONE	VARCHAR2(15 BYTE)	대표번호
	
	private int deptCode;			// 부서코드
	private String deptName;		// 부서명
	private String deptRepPhone;	// 대표번호
	private List<EmpAndDeptDTO> empList = new ArrayList<>();

	public DeptAndEmpDTO() {
		super();
	}
	
	public DeptAndEmpDTO(int deptCode, String deptName, String deptRepPhone, List<EmpAndDeptDTO> empList) {
		super();
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.deptRepPhone = deptRepPhone;
		this.empList = empList;
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
	public List<EmpAndDeptDTO> getEmpList() {
		return empList;
	}
	public void setEmpList(List<EmpAndDeptDTO> empList) {
		this.empList = empList;
	}
	
	@Override
	public String toString() {
		return "DeptAndEmpDTO [deptCode=" + deptCode + ", deptName=" + deptName + ", deptRepPhone=" + deptRepPhone
				+ ", empList=" + empList + "]";
	}
	
	
}

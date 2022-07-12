package com.bukkeubook.book.manage.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_EMP")
public class EmpAndDept implements Serializable{

	private static final long serialVersionUID = -9199965832947312024L;
	
//	EMP_NO		NUMBER					사원번호
//	EMP_NAME	NVARCHAR2(31 CHAR)		사원명
//	DEPT_CODE	NUMBER					부서코드 (V)
//	DEPT_NAME	NVARCHAR2(31 CHAR)		부서명	
	
	@Id
	@Column(name = "EMP_NO")
	private int empNo;
	
	@Column(name = "EMP_NAME")
	private String empName;
	
	@ManyToOne
	@JoinColumn(name = "DEPT_CODE")
	private Dept dept;

	public EmpAndDept() {
		super();
	}

	public EmpAndDept(int empNo, String empName, Dept dept) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.dept = dept;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "EmpAndDept [empNo=" + empNo + ", empName=" + empName + ", dept=" + dept + "]";
	}
	
	
}

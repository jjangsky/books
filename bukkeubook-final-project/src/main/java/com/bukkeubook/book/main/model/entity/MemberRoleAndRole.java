//package com.bukkeubook.book.main.model.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import com.bukkeubook.book.member.model.dto.RoleDTO;
//@Entity
//@Table(name = "TBL_MEMBER_ROLE")
//public class MemberRoleAndRole {
////	EMP_NO	NUMBER		사원번호
////	ROLE_CODE	NUMBER	권한코드
//	
//	@Id
//	@Column(name = "ROLE_CODE")
//	private int roleCode;
//	
//	@ManyToOne
//    @JoinColumn(name = "ROLE_CODE")
//	private Role role;
//
//	public MemberRoleAndRole() {
//	}
//
//	public MemberRoleAndRole(int roleCode, Role role) {
//		this.roleCode = roleCode;
//		this.role = role;
//	}
//
//	public int getRoleCode() {
//		return roleCode;
//	}
//
//	public void setRoleCode(int roleCode) {
//		this.roleCode = roleCode;
//	}
//
//	public Role getRole() {
//		return role;
//	}
//
//	public void setRole(Role role) {
//		this.role = role;
//	}
//
//	@Override
//	public String toString() {
//		return "MemberRoleAndRole [roleCode=" + roleCode + ", role=" + role + "]";
//	}
//
//	
//	
//	
//    
//}

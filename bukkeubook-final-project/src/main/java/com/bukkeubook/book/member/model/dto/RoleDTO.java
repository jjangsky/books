package com.bukkeubook.book.member.model.dto;

public class RoleDTO {
//	ROLE_CODE	NUMBER				// 권한코드
//	ROLE_NAME	VARCHAR2(63 BYTE)	// 권한명
//	ROLE_DESC	VARCHAR2(255 BYTE)	// 권한설명
	
	private int code;
	private String name;
	private String desc;
	
	public RoleDTO() {
	}
	public RoleDTO(int code, String name, String desc) {
		this.code = code;
		this.name = name;
		this.desc = desc;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "RoleDTO [code=" + code + ", name=" + name + ", desc=" + desc + "]";
	}
	
	
}

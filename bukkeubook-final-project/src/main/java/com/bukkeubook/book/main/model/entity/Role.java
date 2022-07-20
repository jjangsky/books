package com.bukkeubook.book.main.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_ROLE")
public class Role {
//	ROLE_CODE	NUMBER				// 권한코드
//	ROLE_NAME	VARCHAR2(63 BYTE)	// 권한명
//	ROLE_DESC	VARCHAR2(255 BYTE)	// 권한설명
	
	@Id
	@Column(name = "ROLE_CODE")
	private int code;
	
	@Column(name = "ROLE_NAME")
	private String name;
	
	@Column(name = "ROLE_DESC")
	private String desc;
	
	
}

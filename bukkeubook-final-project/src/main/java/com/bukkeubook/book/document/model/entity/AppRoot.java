package com.bukkeubook.book.document.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "AppRoot")
@Table(name = "TBL_APP_ROOT")
public class AppRoot{

//	APP_PATH_NO	NUMBER
//	APP_ROOT_DESC	VARCHAR2(255 BYTE)
	
	@Id
	@Column(name = "APP_PATH_NO")
	private int appPathNo;
	
	@Column(name = "APP_ROOT_DESC")
	private String appRootDesc;

	public AppRoot() {
		super();
	}

	public AppRoot(int appPathNo, String appRootDesc) {
		super();
		this.appPathNo = appPathNo;
		this.appRootDesc = appRootDesc;
	}

	public int getAppPathNo() {
		return appPathNo;
	}

	public void setAppPathNo(int appPathNo) {
		this.appPathNo = appPathNo;
	}

	public String getAppRootDesc() {
		return appRootDesc;
	}

	public void setAppRootDesc(String appRootDesc) {
		this.appRootDesc = appRootDesc;
	}

	@Override
	public String toString() {
		return "AppRoot [appPathNo=" + appPathNo + ", appRootDesc=" + appRootDesc + "]";
	}
	
	
	
}

package com.bukkeubook.book.document.model.dto;

import java.io.Serializable;

public class AppRootDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5572878668404675699L;
	
//	APP_PATH_NO	NUMBER
//	APP_ROOT_DESC	VARCHAR2(255 BYTE)
	
	private int appPathNo;
	private String appRootDesc;
	public AppRootDTO() {
		super();
	}
	public AppRootDTO(int appPathNo, String appRootDesc) {
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AppRootDTO [appPathNo=" + appPathNo + ", appRootDesc=" + appRootDesc + "]";
	}
	
	
}

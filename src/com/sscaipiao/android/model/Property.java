package com.sscaipiao.android.model;

public class Property {
	
	
	public static final String ID = "id";
	public static final String NEEDGUIDE = "needGuide";
	public static final String ACCOUNT = "account";
	public static final String PASSWORDE = "password";
	
	private Long id = 0l;
	private int needGuide = 0;
	private String account = "";
	private String password = "";
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public int getNeedGuide() {
		return needGuide;
	}
	public void setNeedGuide(int needGuide) {
		this.needGuide = needGuide;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	


}

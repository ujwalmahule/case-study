package com.ujwal.mentorondemand.model;

import java.io.Serializable;

public class LoginUser implements Serializable {

	private static final long serialVersionUID = -654167352334307048L;

	private String userName;
	private String password;
	
	public LoginUser() {
		// TODO Auto-generated constructor stub
	}
	
	public LoginUser(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

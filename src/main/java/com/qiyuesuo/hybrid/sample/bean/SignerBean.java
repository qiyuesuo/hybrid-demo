package com.qiyuesuo.hybrid.sample.bean;

public class SignerBean {

	private String type;
	private String authId;
	
	public SignerBean(String authId,String type) {
		this.authId = authId;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public String getAuthId() {
		return authId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}
}

package com.qiyuesuo.hybrid.sample.response;

public class UserPerosnalResult {

	private String mode;
	private String bizId;
	private String authId;
	private Integer status;

	public String getMode() {
		return mode;
	}

	public String getBizId() {
		return bizId;
	}

	public String getAuthId() {
		return authId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}

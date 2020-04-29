package com.qiyuesuo.hybrid.properties;

public enum ResponseCode {

	SUCCESS(0);

	private Integer code;

	private ResponseCode(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}

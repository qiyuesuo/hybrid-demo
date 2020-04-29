package com.qiyuesuo.hybrid.sample.response;

public class HybridResponse<T> {
	private Integer code;
	private String message;
	private T result;
	
	public HybridResponse(Integer code, String message, T result) {
		this.code = code;
		this.message = message;
		this.result = result;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
}

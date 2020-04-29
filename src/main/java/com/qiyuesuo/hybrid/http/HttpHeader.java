package com.qiyuesuo.hybrid.http;

public class HttpHeader {

	private String contentType;
	private Long timestamp;
	private String signature;
	
	public HttpHeader(Long timestamp, String signature) {
		this.timestamp = timestamp;
		this.signature = signature;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public String getSignature() {
		return signature;
	}
}

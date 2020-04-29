package com.qiyuesuo.hybrid.sample.request;

import com.qiyuesuo.hybrid.http.HttpParameter;

public class UserPersonalResultParam implements BaseRquestParam{
	
	private String authId;
	private String bizId;
	
	@Override
	public HttpParameter getHttpParameter() {
		HttpParameter parameter = HttpParameter.httpGetParamer();
		parameter.addParam("authId", authId);
		parameter.addParam("bizId", bizId);
		return parameter;
	}

	public String getAuthId() {
		return authId;
	}

	public String getBizId() {
		return bizId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

}

package com.qiyuesuo.hybrid.sample.request;

import com.qiyuesuo.hybrid.http.HttpParameter;

public class CompanyAuthStatusParam implements BaseRquestParam {

	private String authId;
	private String bizId;
	private String companyName;

	@Override
	public HttpParameter getHttpParameter() {
		HttpParameter parameter = HttpParameter.httpGetParamer();
		parameter.addParam("authId", authId);
		parameter.addParam("bizId", bizId);
		parameter.addParam("companyName", companyName);
		return parameter;
	}

	public String getAuthId() {
		return authId;
	}

	public String getBizId() {
		return bizId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}

package com.qiyuesuo.hybrid.sample.request;

import com.qiyuesuo.hybrid.http.HttpParameter;
import com.qiyuesuo.hybrid.json.JSONUtils;
import com.qiyuesuo.hybrid.sample.bean.ApplicantBean;
import com.qiyuesuo.hybrid.sample.bean.ParamSwitcher;

public class CompanyAuthUrlParam implements BaseRquestParam{

	private String bizId;
	private ApplicantBean applicant;
	private String corpName;
	private String registerNo;
	private String legalPerson;
	private Boolean edit;
	private String callbackUrl;
	private Boolean h5Page;
	private Boolean closeButton;
	
	@Override
	public HttpParameter getHttpParameter() {
		ParamSwitcher switcher = ParamSwitcher.newInstance("bizId", bizId).add("applicant", applicant).add("corpName", corpName)
				.add("registerNo", registerNo).add("legalPerson", legalPerson).add("edit", edit).add("callbackUrl", callbackUrl)
				.add("h5Page", h5Page).add("closeButton", closeButton);
		HttpParameter parameter = HttpParameter.httpPostParamer();
		parameter.setJsonParams(JSONUtils.toJson(switcher));
		return parameter;
	}

	public String getBizId() {
		return bizId;
	}

	public ApplicantBean getApplicant() {
		return applicant;
	}

	public String getCorpName() {
		return corpName;
	}

	public String getRegisterNo() {
		return registerNo;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public Boolean getEdit() {
		return edit;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public Boolean getH5Page() {
		return h5Page;
	}

	public Boolean getCloseButton() {
		return closeButton;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public void setApplicant(ApplicantBean applicant) {
		this.applicant = applicant;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public void setEdit(Boolean edit) {
		this.edit = edit;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public void setH5Page(Boolean h5Page) {
		this.h5Page = h5Page;
	}

	public void setCloseButton(Boolean closeButton) {
		this.closeButton = closeButton;
	}

}

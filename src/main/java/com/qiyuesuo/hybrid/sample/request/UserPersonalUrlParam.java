package com.qiyuesuo.hybrid.sample.request;

import java.util.List;

import com.qiyuesuo.hybrid.http.HttpParameter;
import com.qiyuesuo.hybrid.json.JSONUtils;
import com.qiyuesuo.hybrid.sample.bean.ParamSwitcher;

public class UserPersonalUrlParam implements BaseRquestParam {

	private String bizId;
	private String mode;
	private String contact;
	private String username;
	private String idCardNo;
	private String paperType;
	private String bankNo;
	private String bankMobile;
	private String callbackUrl;
	private String callbackPage;
	private List<String> modifyFields;
	private List<String> otherModes;

	@Override
	public HttpParameter getHttpParameter() {
		HttpParameter parameter = HttpParameter.httpPostParamer();
		ParamSwitcher switcher = ParamSwitcher.newInstance("bizId", bizId).add("mode", mode).add("contact", contact).add("username", username)
				.add("idCardNo", idCardNo).add("paperType", paperType).add("bankNo", bankNo).add("bankMobile", bankMobile)
				.add("callbackUrl", callbackUrl).add("callbackPage", callbackPage).add("modifyFields", modifyFields).add("otherModes", otherModes);
		parameter.setJsonParams(JSONUtils.toJson(switcher));
		return parameter;
	}

	public String getBizId() {
		return bizId;
	}

	public String getMode() {
		return mode;
	}

	public String getContact() {
		return contact;
	}

	public String getUsername() {
		return username;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public String getPaperType() {
		return paperType;
	}

	public String getBankNo() {
		return bankNo;
	}

	public String getBankMobile() {
		return bankMobile;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public String getCallbackPage() {
		return callbackPage;
	}

	public List<String> getModifyFields() {
		return modifyFields;
	}

	public List<String> getOtherModes() {
		return otherModes;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public void setBankMobile(String bankMobile) {
		this.bankMobile = bankMobile;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public void setCallbackPage(String callbackPage) {
		this.callbackPage = callbackPage;
	}

	public void setModifyFields(List<String> modifyFields) {
		this.modifyFields = modifyFields;
	}

	public void setOtherModes(List<String> otherModes) {
		this.otherModes = otherModes;
	}

}

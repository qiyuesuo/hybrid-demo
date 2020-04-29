package com.qiyuesuo.hybrid.sample.request;

import java.util.List;

import com.qiyuesuo.hybrid.http.HttpParameter;
import com.qiyuesuo.hybrid.json.JSONUtils;
import com.qiyuesuo.hybrid.sample.bean.ParamSwitcher;
import com.qiyuesuo.hybrid.sample.bean.SignAppearanceBean;
import com.qiyuesuo.hybrid.sample.bean.SignerBean;
import com.qiyuesuo.hybrid.sample.bean.StamperBean;

public class PersonalPageSignParam implements BaseRquestParam {

	private Long documentId;
	private SignerBean signer;
	private List<String> signValidateMethods;
	private Boolean visible;
	private SignAppearanceBean appearance;
	private String callbackUrl;
	private String redirectUrl;
	private List<StamperBean> stampers;

	@Override
	public HttpParameter getHttpParameter() {
		HttpParameter parameter = HttpParameter.httpPostParamer();
		ParamSwitcher switcher = ParamSwitcher.newInstance("documentId", documentId).add("signer", signer)
				.add("signValidateMethods", signValidateMethods).add("visible", visible).add("appearance", appearance).add("callbackUrl", callbackUrl)
				.add("redirectUrl", redirectUrl).add("stampers", stampers);
		parameter.setJsonParams(JSONUtils.toJson(switcher));
		return parameter;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public SignerBean getSigner() {
		return signer;
	}

	public List<String> getSignValidateMethods() {
		return signValidateMethods;
	}

	public SignAppearanceBean getAppearance() {
		return appearance;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public List<StamperBean> getStampers() {
		return stampers;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public void setSigner(SignerBean signer) {
		this.signer = signer;
	}

	public void setSignValidateMethods(List<String> signValidateMethods) {
		this.signValidateMethods = signValidateMethods;
	}

	public void setAppearance(SignAppearanceBean appearance) {
		this.appearance = appearance;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public void setStampers(List<StamperBean> stampers) {
		this.stampers = stampers;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
}

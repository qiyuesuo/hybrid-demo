package com.qiyuesuo.hybrid.sample.request;

import java.util.ArrayList;
import java.util.List;

import com.qiyuesuo.hybrid.http.HttpParameter;
import com.qiyuesuo.hybrid.json.JSONUtils;
import com.qiyuesuo.hybrid.sample.bean.ParamSwitcher;
import com.qiyuesuo.hybrid.sample.bean.SignAppearanceBean;
import com.qiyuesuo.hybrid.sample.bean.StamperBean;

public class PlatformPageSignParam implements BaseRquestParam{
	private Long documentId;
	private Boolean visible;
	private SignAppearanceBean appearance;
	private List<StamperBean> stampers;
	private String callbackUrl;
	private String redirectUrl;
	

	@Override
	public HttpParameter getHttpParameter() {
		ParamSwitcher switcher = ParamSwitcher.newInstance("documentId", documentId).add("visible", visible).add("appearance", appearance)
				.add("stampers", stampers).add("callbackUrl", callbackUrl).add("redirectUrl", redirectUrl);
		HttpParameter parameter = HttpParameter.httpPostParamer();
		parameter.setJsonParams(JSONUtils.toJson(switcher));
		return parameter;
	}

	public List<StamperBean> addStamper(StamperBean stamper) {
		if(stampers == null) {
			this.stampers = new ArrayList<StamperBean>();
		}
		stampers.add(stamper);
		return stampers;
	}

	public Long getDocumentId() {
		return documentId;
	}


	public Boolean getVisible() {
		return visible;
	}


	public SignAppearanceBean getAppearance() {
		return appearance;
	}


	public List<StamperBean> getStampers() {
		return stampers;
	}


	public String getCallbackUrl() {
		return callbackUrl;
	}


	public String getRedirectUrl() {
		return redirectUrl;
	}


	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}


	public void setVisible(Boolean visible) {
		this.visible = visible;
	}


	public void setAppearance(SignAppearanceBean appearance) {
		this.appearance = appearance;
	}


	public void setStampers(List<StamperBean> stampers) {
		this.stampers = stampers;
	}


	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}


	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}

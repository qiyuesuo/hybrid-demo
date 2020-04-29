package com.qiyuesuo.hybrid.sample.request;

import java.util.ArrayList;
import java.util.List;

import com.qiyuesuo.hybrid.http.HttpParameter;
import com.qiyuesuo.hybrid.json.JSONUtils;
import com.qiyuesuo.hybrid.sample.bean.ParamSwitcher;
import com.qiyuesuo.hybrid.sample.bean.SignAppearanceBean;
import com.qiyuesuo.hybrid.sample.bean.StamperBean;

public class PlatformInterfaceSignParam implements BaseRquestParam {

	private Long documentId;
	private SignAppearanceBean appearance;
	private List<StamperBean> stampers;

	@Override
	public HttpParameter getHttpParameter() {
		ParamSwitcher switcher = ParamSwitcher.newInstance("documentId", this.documentId).add("appearance", appearance).add("stampers", stampers);
		HttpParameter parameter = HttpParameter.httpPostParamer();
		parameter.setJsonParams(JSONUtils.toJson(switcher));
		return parameter;
	}

	public List<StamperBean> addStamper(StamperBean stamper) {
		if (stampers == null) {
			this.stampers = new ArrayList<StamperBean>();
		}
		stampers.add(stamper);
		return stampers;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public SignAppearanceBean getAppearance() {
		return appearance;
	}

	public List<StamperBean> getStampers() {
		return stampers;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public void setAppearance(SignAppearanceBean appearance) {
		this.appearance = appearance;
	}

	public void setStampers(List<StamperBean> stampers) {
		this.stampers = stampers;
	}

}

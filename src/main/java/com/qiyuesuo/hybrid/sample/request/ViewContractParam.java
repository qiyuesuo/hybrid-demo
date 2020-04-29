package com.qiyuesuo.hybrid.sample.request;

import com.qiyuesuo.hybrid.http.HttpParameter;
import com.qiyuesuo.hybrid.json.JSONUtils;
import com.qiyuesuo.hybrid.sample.bean.ParamSwitcher;

public class ViewContractParam implements BaseRquestParam {

	private Long documentId;

	public ViewContractParam(Long documentId) {
		this.documentId = documentId;
	}

	@Override
	public HttpParameter getHttpParameter() {
		HttpParameter parameter = HttpParameter.httpPostParamer();
		ParamSwitcher switcher = ParamSwitcher.newInstance("documentId", documentId);
		parameter.setJsonParams(JSONUtils.toJson(switcher));
		return parameter;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

}

package com.qiyuesuo.hybrid.sample.request;

import com.qiyuesuo.hybrid.http.HttpParameter;

public class DownloadContractParam implements BaseRquestParam {

	private Long documentId;

	public DownloadContractParam(Long documentId) {
		this.documentId = documentId;
	}

	@Override
	public HttpParameter getHttpParameter() {
		HttpParameter parameter = HttpParameter.httpGetParamer();
		parameter.addParam("documentId", documentId);
		return parameter;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

}

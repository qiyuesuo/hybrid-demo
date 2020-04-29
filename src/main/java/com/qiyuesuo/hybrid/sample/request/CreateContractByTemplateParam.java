package com.qiyuesuo.hybrid.sample.request;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import com.qiyuesuo.hybrid.http.HttpMethod;
import com.qiyuesuo.hybrid.http.HttpParameter;
import com.qiyuesuo.hybrid.http.StreamFile;
import com.qiyuesuo.hybrid.json.JSONUtils;
import com.qiyuesuo.hybrid.sample.bean.TemplateBean;

public class CreateContractByTemplateParam implements BaseRquestParam {

	private ByteBuffer fileBuffer;
	private String title;
	private List<TemplateBean> templateParams;
	private String fileType;

	@Override
	public HttpParameter getHttpParameter() {
		HttpParameter parameter = new HttpParameter(HttpMethod.POST);
		if (fileBuffer != null) {
			parameter.addFile("file", new StreamFile(new ByteArrayInputStream(this.fileBuffer.array())));
		}
		parameter.addParam("title", this.title);
		parameter.addParam("fileType", this.fileType);
		if (templateParams != null && templateParams.size() != 0) {
			parameter.addParam("templateParams", JSONUtils.toJson(templateParams));
		}
		return parameter;
	}
	
	public List<TemplateBean> addParam(TemplateBean param){
		if(templateParams == null) {
			templateParams = new ArrayList<TemplateBean>();
		}
		templateParams.add(param);
		return templateParams;
	}

	public ByteBuffer getFileBuffer() {
		return fileBuffer;
	}

	public String getTitle() {
		return title;
	}

	public List<TemplateBean> getTemplateParams() {
		return templateParams;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileBuffer(ByteBuffer fileBuffer) {
		this.fileBuffer = fileBuffer;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTemplateParams(List<TemplateBean> templateParams) {
		this.templateParams = templateParams;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}

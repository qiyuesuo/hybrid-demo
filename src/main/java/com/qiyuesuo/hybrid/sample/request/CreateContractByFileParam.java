package com.qiyuesuo.hybrid.sample.request;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

import com.qiyuesuo.hybrid.http.HttpMethod;
import com.qiyuesuo.hybrid.http.HttpParameter;
import com.qiyuesuo.hybrid.http.StreamFile;

public class CreateContractByFileParam implements BaseRquestParam{

	private ByteBuffer fileBuffer;
	private String title;
	private String fileType;

	public ByteBuffer getFileBuffer() {
		return fileBuffer;
	}

	public String getTitle() {
		return title;
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

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Override
	public HttpParameter getHttpParameter() {
		HttpParameter parameter = new HttpParameter(HttpMethod.POST);
		if(fileBuffer!=null) {
			parameter.addFile("file", new StreamFile(new ByteArrayInputStream(this.fileBuffer.array())));
		}
		parameter.addParam("title", this.title);
		parameter.addParam("fileType", this.fileType);
		return parameter;
	}
}

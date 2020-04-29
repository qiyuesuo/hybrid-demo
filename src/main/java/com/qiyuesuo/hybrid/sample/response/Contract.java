package com.qiyuesuo.hybrid.sample.response;

import java.util.List;

public class Contract {

	private Long documentId;
	private String subject;
	private String platformName;
	private String status;
	private String publishTime;
	private List<Document> documents;

	public Long getDocumentId() {
		return documentId;
	}

	public String getSubject() {
		return subject;
	}

	public String getPlatformName() {
		return platformName;
	}

	public String getStatus() {
		return status;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

}

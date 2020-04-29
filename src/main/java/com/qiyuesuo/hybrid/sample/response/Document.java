package com.qiyuesuo.hybrid.sample.response;

public class Document {

	private Long id;
	private String title;
	private Integer version;
	private String createTime;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Integer getVersion() {
		return version;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}

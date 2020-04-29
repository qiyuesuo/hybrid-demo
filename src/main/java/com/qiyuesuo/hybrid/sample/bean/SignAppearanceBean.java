package com.qiyuesuo.hybrid.sample.bean;

public class SignAppearanceBean {

	private Long sealId;// 印章Id
	private String data;// 印章图片类型
	private String sealSpec;// 印章规格

	public SignAppearanceBean(Long sealId) {
		this.sealId = sealId;
	}

	public SignAppearanceBean(String data, String sealSpec) {
		this.data = data;
		this.sealSpec = sealSpec;
	}

	public Long getSealId() {
		return sealId;
	}

	public String getData() {
		return data;
	}

	public String getSealSpec() {
		return sealSpec;
	}

	public void setSealId(Long sealId) {
		this.sealId = sealId;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setSealSpec(String sealSpec) {
		this.sealSpec = sealSpec;
	}
}

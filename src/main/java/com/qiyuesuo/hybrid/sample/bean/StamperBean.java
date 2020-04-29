package com.qiyuesuo.hybrid.sample.bean;

public class StamperBean {

	private String type;
	private SignAppearanceBean appearance;
	private Double offsetX;
	private Double offsetY;
	private Integer page;
	private String keyword;
	private Integer keywordIndex;
	
	public String getType() {
		return type;
	}
	public SignAppearanceBean getAppearance() {
		return appearance;
	}
	public Double getOffsetX() {
		return offsetX;
	}
	public Double getOffsetY() {
		return offsetY;
	}
	public Integer getPage() {
		return page;
	}
	public String getKeyword() {
		return keyword;
	}
	public Integer getKeywordIndex() {
		return keywordIndex;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setAppearance(SignAppearanceBean appearance) {
		this.appearance = appearance;
	}
	public void setOffsetX(Double offsetX) {
		this.offsetX = offsetX;
	}
	public void setOffsetY(Double offsetY) {
		this.offsetY = offsetY;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void setKeywordIndex(Integer keywordIndex) {
		this.keywordIndex = keywordIndex;
	}
}

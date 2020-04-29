package com.qiyuesuo.hybrid.sample.request;

import com.qiyuesuo.hybrid.http.HttpParameter;
import com.qiyuesuo.hybrid.json.JSONUtils;
import com.qiyuesuo.hybrid.sample.bean.ParamSwitcher;

public class CompanySealParam implements BaseRquestParam{

	private String sealSpec;
	private String sealCategory;
	private String content;
	private String innerContent;
	private String foot;
	private String head;
	private String enterpriseCode;
	private Boolean sealLogo;
	
	@Override
	public HttpParameter getHttpParameter() {
		HttpParameter parameter = HttpParameter.httpPostParamer();
		ParamSwitcher switcher = ParamSwitcher.newInstance("sealSpec", sealSpec).add("sealCategory", sealCategory).add("content", content)
				.add("innerContent", innerContent).add("foot", foot).add("head", head).add("enterpriseCode", enterpriseCode).add("sealLogo", sealLogo);
		parameter.setJsonParams(JSONUtils.toJson(switcher));
		return parameter;
	}

	public String getSealSpec() {
		return sealSpec;
	}

	public String getSealCategory() {
		return sealCategory;
	}

	public String getContent() {
		return content;
	}

	public String getInnerContent() {
		return innerContent;
	}

	public String getFoot() {
		return foot;
	}

	public String getHead() {
		return head;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public Boolean getSealLogo() {
		return sealLogo;
	}

	public void setSealSpec(String sealSpec) {
		this.sealSpec = sealSpec;
	}

	public void setSealCategory(String sealCategory) {
		this.sealCategory = sealCategory;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setInnerContent(String innerContent) {
		this.innerContent = innerContent;
	}

	public void setFoot(String foot) {
		this.foot = foot;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public void setSealLogo(Boolean sealLogo) {
		this.sealLogo = sealLogo;
	}

}

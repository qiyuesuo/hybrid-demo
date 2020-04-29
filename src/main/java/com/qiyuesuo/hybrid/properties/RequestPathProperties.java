package com.qiyuesuo.hybrid.properties;

/**
 * 接口请求地址枚举类，更多接口地址参考接口清单文档
 */
public enum RequestPathProperties {

	/*
	 * 通过文件创建合同
	 */
	CREATE_CONTRACT_BYFILE("/open/contract/createbyfile"),
	/*
	 * 通过模板文件创建合同
	 */
	CREATE_CONTRACT_BYTEMPLATE("/open/contract/createbytemplate"),
	/*
	 * 对接方静默签署
	 */
	PLATFORM_INTERFACE_SIGN("/open/contract/platformsign"),
	/*
	 * 对接方签署页面连接
	 */
	PLATFORM_PAGE_SIGN("/open/contract/platformsignurl"),
	/*
	 * 合同预览页面
	 */
	CONTRACT_VIEW_PAGE("/open/contract/viewurl"),
	/*
	 * 下载合同
	 */
	DOWNLOAD_CONTRACT("/open/contract/download"),
	/*
	 * 完成合同
	 */
	COMPLETE_CONTRACT("/open/contract/complete"),
	/*
	 * 签署链接
	 */
	SIGN_PAGE("/open/contract/signurl"),
	/*
	 * 生成公司公章外观
	 */
	GERENATE_COMPANY_SEAL("/open/seal/companyimage"),
	/*
	 * 生成个人认证链接
	 */
	PERSONAL_AUTH_PAGE("/open/auth/personurl"),
	/*
	 * 获取个人认证结果
	 */
	PERSONAL_AUTH_STATUS("/open/auth/personalstatus"),
	/*
	 * 公司认证链接
	 */
	COMPANY_AUTH_PAGE("/open/auth/companyurl"),
	/*
	 * 公司认证状态
	 */
	COMPANY_AUTH_STATUS("/open/auth/companystatus"),;

	private String path;

	private RequestPathProperties(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}

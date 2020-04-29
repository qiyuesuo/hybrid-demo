package com.qiyuesuo.hybrid.http;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.qiyuesuo.hybrid.json.JSONUtils;
import com.qiyuesuo.hybrid.utils.StringUtils;

/**
 * 参数对象封装,包含文本参数和文件参数
 */
public class HttpParameter {

	private Map<String, String> params = new HashMap<String, String>();
	private Map<String, FileItem> files = new HashMap<String, FileItem>();
	private Map<String, List<FileItem>> listFiles = new HashMap<String, List<FileItem>>();
	private HttpMethod httpMethod;
	private String jsonParams;

	public HttpParameter(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	/**
	 * 创建POST请求参数
	 * 
	 * @return
	 */
	public static HttpParameter httpPostParamer() {
		return new HttpParameter(HttpMethod.POST);
	}

	/**
	 * 创建GET请求参数
	 * 
	 * @return
	 */
	public static HttpParameter httpGetParamer() {
		return new HttpParameter(HttpMethod.GET);
	}

	public HttpParameter addParam(String key, Object value) {
		if (StringUtils.isNotBlank(key) && value != null) {
			this.params.put(key, String.valueOf(value));
		}
		return this;
	}

	/**
	 * 添加文本参数
	 * 
	 * @param name  参数名
	 * @param value 参数值
	 * @return
	 */
	public HttpParameter setParams(Map<String, Object> paramMap) {
		if (paramMap == null) {
			return this;
		}
		Set<Entry<String, Object>> entrySet = paramMap.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			this.addParam(entry.getKey(), entry.getValue());
		}
		return this;
	}

	/**
	 * 添加文件参数
	 * 
	 * @param name 参数名
	 * @param file 文件对象
	 * @return
	 */
	public HttpParameter addFile(String name, FileItem file) {
		this.files.put(name, file);
		return this;
	}

	public HttpParameter addListFiles(String name, List<FileItem> fileItems) {
		this.listFiles.put(name, fileItems);
		return this;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	/**
	 * 将参数拼接为a=1&b=2...格式
	 * 
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public String getQueryString(String charset) throws IOException {
		if (params == null || params.isEmpty()) {
			return null;
		}
		StringBuilder query = new StringBuilder();
		Set<Entry<String, String>> entries = params.entrySet();

		for (Entry<String, String> entry : entries) {
			String name = entry.getKey();
			String value = entry.getValue();
			// 忽略参数名或参数值为空的参数
			if (StringUtils.isNoneEmpty(name, value)) {
				query.append("&");
				query.append(name).append("=").append(URLEncoder.encode(value, charset));
			}
		}

		return query.substring(1);
	}

	/**
	 * 参数是否是包含附件
	 */
	public boolean isMultipart() {
		return !files.isEmpty() || !listFiles.isEmpty();
	}

	/**
	 * 是否json请求
	 */
	public boolean isJson() {
		return jsonParams != null && jsonParams.length() > 0;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public Map<String, FileItem> getFiles() {
		return files;
	}

	public Map<String, List<FileItem>> getListFiles() {
		return listFiles;
	}

	public String getJsonParams() {
		return jsonParams;
	}

	public void setJsonParams(String jsonParams) {
		this.jsonParams = jsonParams;
	}

	@Override
	public String toString() {
		return "HttpParamers " + JSONUtils.toJson(this);
	}
}

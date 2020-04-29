package com.qiyuesuo.hybrid.http;

import java.io.OutputStream;

import com.qiyuesuo.hybrid.exception.HybridException;
import com.qiyuesuo.hybrid.utils.MD5;

public class HybridClient {

	/*
	 * 混合云地址
	 */
	private String hybridHost;
	/*
	 * Http连接超时时间，默认15s
	 */
	private int connectTimeout = 1500;
	/*
	 * Http连接读取时间，默认30s
	 */
	private int readTimeout = 3000;
	/*
	 * 混合云请求校验Token，配置后需要根据混合云请求协议请求接口
	 */
	private String requestToken;

	/**
	 * 混合云连接客户端构造方法
	 * @param hybridHost 混合云部署地址
	 */
	public HybridClient(String hybridHost) {
		this.hybridHost = hybridHost;
	}

	public HybridClient(String hybridHost, int connectTimeout, int readTimeout) {
		this.hybridHost = hybridHost;
		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
	}

	/**
	 * 混合云连接客户端构造方法
	 * @param hybridHost 混合云部署地址
	 * @param requestToken 混合云部署配置的requestToken
	 */
	public HybridClient(String hybridHost, String requestToken) {
		this.hybridHost = hybridHost;
		this.requestToken = requestToken;
	}

	public HybridClient(String hybridHost, String requestToken, int connectTimeout, int readTimeout) {
		this.hybridHost = hybridHost;
		this.requestToken = requestToken;
		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
	}

	public String doService(String url, HttpParameter parameters) {
		Long timestamp = System.currentTimeMillis();
		String plainText = this.requestToken + timestamp;
		String signature = MD5.toMD5(plainText);
		HttpHeader header = new HttpHeader(timestamp, signature);
		url = hybridHost + url;
		try {
			if (parameters.isJson()) {
				return HttpClient.doServiceWithJson(url, parameters.getJsonParams(), header, connectTimeout, readTimeout);
			}
			return HttpClient.doService(url, parameters, header, connectTimeout, readTimeout);
		} catch (Exception e) {
			throw new HybridException("请求混合云服务器失败，失败原因：" + e.getMessage(), e);
		}

	}

	public void doDownload(String url, HttpParameter parameters, OutputStream outputStream) {
		Long timestamp = System.currentTimeMillis();
		String plainText = this.requestToken + timestamp;
		String signature = MD5.toMD5(plainText);
		HttpHeader header = new HttpHeader(timestamp, signature);
		url = hybridHost + url;
		try {
			HttpClient.doDownload(url, parameters, header, connectTimeout, readTimeout, outputStream);
		} catch (Exception e) {
			throw new HybridException("请求混合云服务器失败，失败原因：" + e.getMessage(), e);
		}
	}
}

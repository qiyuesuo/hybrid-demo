package com.qiyuesuo.hybrid.utils;

/**
 * BASE64编码解码工具包
 */
public class Base64Utils {

	public static final String ENCODING = "UTF-8";

	/**
	 * BASE64字符串解码为二进制数据
	 * 
	 * @param base64
	 * @return
	 * @throws Exception
	 */
	public static byte[] decode(String base64) throws Exception {
		// return Base64.getDecoder().decode(base64.getBytes());
		return Base64.decode(base64.getBytes(ENCODING));
	}

	/**
	 * 二进制数据编码为BASE64字符串
	 * 
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static String encode(byte[] bytes) throws Exception {
		// return new String(Base64.getEncoder().encode(bytes));
		return new String(Base64.encode(bytes), ENCODING);
	}

}

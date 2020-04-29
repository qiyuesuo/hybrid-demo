package com.qiyuesuo.hybrid.utils;

import java.util.Formatter;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加解密工具类
 */
public class CryptUtils {

	/**
	 * 字节转换为 16 进制字符串
	 * 
	 * @param b 字节
	 * @return
	 */
	public static String byte2Hex(byte b) {
		String hex = Integer.toHexString(b);
		if (hex.length() > 2) {
			hex = hex.substring(hex.length() - 2);
		}
		while (hex.length() < 2) {
			hex = "0" + hex;
		}
		return hex;
	}

	/**
	 * 字节数组转换为 16 进制字符串
	 * 
	 * @param bytes 字节数组
	 * @return
	 */
	public static String byte2Hex(byte[] bytes) {
		Formatter formatter = new Formatter();
		for (byte b : bytes) {
			formatter.format("%02x", b);
		}
		String hash = formatter.toString();
		formatter.close();
		return hash;
	}

	/**
	 * AES解密（业务分类回调）
	 * 
	 * @param encrypt
	 * @param secret
	 * @return
	 * @throws Exception
	 */
	public static String aesDerypt(String encrypt, String secret) throws Exception {
		byte[] decryptBytes = null;
		String decryptStr = null;
		Cipher cipher = Cipher.getInstance(Algorithm.AES.getKey());
		byte[] bk = secret.getBytes("UTF-8");
		SecretKey secretKey = new SecretKeySpec(bk, Algorithm.AES.getKey());
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] scrBytes = Base64Utils.decode(encrypt);
		decryptBytes = cipher.doFinal(scrBytes);
		if (decryptBytes != null) {
			decryptStr = new String(decryptBytes, "UTF-8");
		}
		return decryptStr;
	}
}

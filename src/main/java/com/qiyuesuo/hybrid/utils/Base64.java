package com.qiyuesuo.hybrid.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Base64 {
	private static final Encoder encoder = new Encoder();
	public static byte[] encode(byte[] data) {
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		try {
			encoder.encode(data, 0, data.length, bOut);
		} catch (IOException e) {
			throw new RuntimeException("encoding base64 exception: " + e);
		}
		byte[] ret=bOut.toByteArray();
		return ret;
	}

	public static byte[] decode(byte[] data) {
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		try {
			String ss=new String(data);
			encoder.decode(ss, bOut);
		} catch (IOException e) {
			throw new RuntimeException("decoding base64 exception: " + e);
		}

		return bOut.toByteArray();
	}
}


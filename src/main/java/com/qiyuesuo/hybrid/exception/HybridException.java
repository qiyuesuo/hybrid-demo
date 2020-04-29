package com.qiyuesuo.hybrid.exception;

public class HybridException extends RuntimeException{

	private static final long serialVersionUID = 2695524613853665550L;
	
	public HybridException(String message, Throwable cause) {
		super(message, cause);
	}

	public HybridException(String message) {
		super(message);
	}

}

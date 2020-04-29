package com.qiyuesuo.hybrid.sample.bean;

import java.util.HashMap;

import com.qiyuesuo.hybrid.utils.StringUtils;

public class ParamSwitcher extends HashMap<String,Object> {
	private static final long serialVersionUID = 1L;


	public static ParamSwitcher newInstance(String key, Object value) {
		ParamSwitcher switcher = new ParamSwitcher();
		if(StringUtils.isNotBlank(key) && value != null) {
			switcher.put(key, value);
		}
		return switcher;
	}
	
	
	public ParamSwitcher add(String key, Object value) {
		if(StringUtils.isNotBlank(key) && value != null) {
			super.put(key, value);
		}
		return this;
	}
	
}

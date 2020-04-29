package com.qiyuesuo.hybrid.json;

import java.util.List;
import java.util.Map;

import com.qiyuesuo.hybrid.exception.HybridException;
import com.qiyuesuo.hybrid.sample.response.HybridResponse;
import com.qiyuesuo.hybrid.utils.MapUtils;

/**
 * Json处理工具类
 */
public class JSONUtils {
	
	public static String toJson(Object object){
		JSONObject jsonObject = new JSONObject(object);
		return jsonObject.toString();
	}
	
	public static String toJson(Map<?, ?> map){
		JSONObject jsonObject = new JSONObject(map);
		return jsonObject.toString();
	}
	
	public static String toJson(List<?> list){
		JSONArray json = new JSONArray();
		
		for(Object object:list){
			JSONObject jsonObject = new JSONObject(object);
			json.put(jsonObject);
		}
		
		return json.toString();
	}

	public static Map<String, Object> toMap(Object object){
		JSONObject jsonObject = new JSONObject(object);
		return jsonObject.toMap();
	}
	
	public static <T> HybridResponse<T> toHybridResponse(String json) {
		return toHybridResponse(json, null);
	}
	
	public static <T> HybridResponse<T> toHybridResponse(String json, Class<T> targetClass) {
		JSONObject jsonObject = new JSONObject(json);
		Map<String, Object> map = jsonObject.toMap();
		
		Integer code = (Integer) map.get("code");
		String message = (String) map.get("message");
		Map<String, Object> resultMap = map.get("result") != null ? (Map)map.get("result") : null;
		
		try {
			T result = targetClass == null || resultMap == null ? null : MapUtils.toObject(resultMap, targetClass);
			return new HybridResponse<>(code, message, result);
		} catch (Exception e) {
			throw new HybridException("解析结果错误", e);
		}
	}
}

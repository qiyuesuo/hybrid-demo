package com.qiyuesuo.hybrid.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MapUtils {
	
	/**
	 * map转对象
	 * @param beanClass
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static <T> T toObject(Map<String, Object> map, Class<T> beanClass) throws Exception{
		T bean = null;
		try {
			//对象实例化
			bean = beanClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(map == null || map.isEmpty()) {
			return bean;
		}
		//循环map集合
		for(String methodName : map.keySet()) {
			Object value = map.get(methodName);
			if(value == null) {
				continue;
			}
			Field beanField = null;
			// 获取对象的所有方法
			Field[] fields = beanClass.getDeclaredFields();
			for(Field field : fields) {
				if(field.getName().equals(methodName)) {
					beanField = field;
					break;
				}
			}
			if(beanField == null) {
				continue;
			}
			// 获取类属性的类型，将value类型转换
			String methodType = beanField.getType().getName();
			
			if(beanField.getType().isAssignableFrom(String.class)) {
				value = value.toString();
			} else if(beanField.getType().isAssignableFrom(Long.class)) {  
				value = Long.parseLong(value.toString());  
	        } else if(beanField.getType().isAssignableFrom(Integer.class)) {  
	        	value = Integer.parseInt(value.toString());  
	        } else if(beanField.getType().isAssignableFrom(Float.class)) {  
	        	value = Float.parseFloat(value.toString());  
	        } else if(beanField.getType().isAssignableFrom(Double.class)) {  
	        	value = Double.parseDouble(value.toString());  
	        } else if(beanField.getType().isAssignableFrom(Boolean.class)) {
	        	value = Boolean.parseBoolean(value.toString());
	        } else if(beanField.getType().isAssignableFrom(Date.class)){
	        	value = TimeUtils.parse(value.toString());
	        } else if(beanField.getType().isEnum()) {
	        	// 枚举类型进行转换
	        	value = convertToEnum(methodType, value);
	        } else if(beanField.getType().isAssignableFrom(List.class)) {
	        	// 字符串筛选出数组中的对象名
	        	String className = beanField.getGenericType().toString();
	        	className = className.replaceAll("java.util.List", "");
	        	className = className.replaceAll("<", "");
	        	className = className.replaceAll(">", "");
	        	Class<?> tempClass = Class.forName(className);
        		List<Object> objects = new ArrayList<Object>();
        		// 数组类型进行循环递归
        		if(value != null) {
        			List<Map> maps = (List<Map>) value;
        			for(Map tempMap : maps) {
        				Object object = toObject(tempMap, tempClass);
        				objects.add(object);
	        		}
        		}
        		
        		value = objects;
	        } else {
	        	// 定义的类
        		Class<?> tempClass = beanField.getType();
        		Map<String, Object> tempMap = (Map<String, Object>) value;
        		value = toObject(tempMap, tempClass);
	        }
			try {
				String methodName1 = "set" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
				Method method = beanClass.getMethod(methodName1, beanField.getType());
				method.invoke(bean, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bean;
	}
	
	private static Enum convertToEnum(String enumName, Object value) {
		Class<Enum> clazz = null;
		try {
			clazz = (Class<Enum>)Class.forName(enumName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (clazz.isAssignableFrom(value.getClass())) {
            // we just checked it!
            @SuppressWarnings("unchecked")
            Enum myE = (Enum) value;
            return myE;
        }
        return Enum.valueOf(clazz, value.toString());
	}
	
	/**
	 * map集合转对象集合
	 * @param <T>
	 * @param beanClass
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> toObjectList(List<Map<String, Object>> maps, Class<T> beanClass) throws Exception{
		if(maps == null || maps.size() < 0) {
			return null;
		}
		List<T> beanList = new ArrayList<T>();
		for(Map<String, Object> tempMap : maps) {
			T bean = toObject(tempMap, beanClass);
			beanList.add(bean);
		}
		return beanList;
	}
	
}

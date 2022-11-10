package com.laptrinhjavaweb.utils;

import java.util.Map;

public class MapUtil {
    public static Object getValue(Map<String,Object> params, String key){
        return params.containsKey(key)?params.get(key):null;
    }

    /* trong project ko dùng
    public static <T> T getObject(Map<String, Object> param, String key, Class<T> tClass) {
		//key là field trên param
		// Class<T> tClass là lấy Class của kiểu dữ liệu nguyên thủy: Integer.Class, String.Class ...
		Object object = param.getOrDefault(key, null);// truyền key vào để lấy giá trị, Object do: String, Integer...ko cố định
		if (object != null) {
			if (tClass.getTypeName().equals("java.lang.Long")) {
				object = object != "" ? Long.valueOf(object.toString()) : null;
			} else if (tClass.getTypeName().equals("java.lang.Integer")) {
				object = object != "" ? Integer.parseInt(object.toString()) : null;
			}else if (tClass.getTypeName().equals("java.lang.String")) {
				object = object.toString();
			}
			return tClass.cast(object);
		}
		return null;
	}
     */
}

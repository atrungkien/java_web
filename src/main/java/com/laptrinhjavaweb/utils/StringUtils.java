package com.laptrinhjavaweb.utils;

public class StringUtils {
	public static boolean isNullOrEmpty(String value) {
		if(value != "" && value != null) {
			return false;
		}
		return true;
	}
}

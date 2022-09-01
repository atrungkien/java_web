package com.laptrinhjavaweb.utils;

public class IsNullOrEmtyUtils {
	public static boolean IsNullOrEmty(Object object) {
		if (object != null && object != "") {
			return true;
		}
		return false;
	}
}

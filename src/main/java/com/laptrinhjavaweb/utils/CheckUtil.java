package com.laptrinhjavaweb.utils;

public class CheckUtil {
    public static boolean IsNullOrEmty(Object object) {
        if (object != null && object != "") {
            return true;
        }
        return false;
    }
}

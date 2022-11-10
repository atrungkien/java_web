package com.laptrinhjavaweb.utils;

import com.laptrinhjavaweb.constant.SystemConstant;


//trong project ko dùng
public class CheckUtil {
    public static boolean isNotBlank(Object input) {
        return (input != null && (!input.equals(SystemConstant.EMPTY_STRING)));
    }

}

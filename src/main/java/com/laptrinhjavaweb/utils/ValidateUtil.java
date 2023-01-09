package com.laptrinhjavaweb.utils;

import com.laptrinhjavaweb.constant.SystemConstant;

import java.util.Collection;

public class ValidateUtil {
    public static boolean isValid(Object object) {
        boolean isTrue = (object!=null && !(object.toString()).equals(SystemConstant.EMPTY_STRING));
        if(isTrue) {
            if (object instanceof String) {
                return true;
            }else if (object instanceof Integer) {
                return (Integer.parseInt(object.toString())>=0);
            }else if (object instanceof Long) {
                return (Long.valueOf(object.toString())>0);
            }else if (object instanceof Collection) {
                return (!((Collection<?>) object).isEmpty());//ep kieu ve collection bat ky
            }

        }
        return false;
    }
}

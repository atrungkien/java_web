package com.laptrinhjavaweb.utils;

public class ParseIntUtil {
    public static Integer getValue(Object value){
        try{
            return Integer.parseInt((String) value);
        }catch (Exception e){
            return null;
        }
    }

    /* NumberUtils  trong project ko dùng
    public static boolean isInteger(String input) {
		if (input == null && input.equals(SystemConstant.STRING_EMPTY)) {
			return false;
		}
		try {
			Integer result = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
     */
}

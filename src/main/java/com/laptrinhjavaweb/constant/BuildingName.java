package com.laptrinhjavaweb.constant;

import java.util.HashMap;
import java.util.Map;

public class BuildingName {
	public static Map<String,String> getBuildingName(){
		Map<String,String> result =  new HashMap<String,String>();
		result.put(SystemConstant.QUAN_1_CODE, SystemConstant.QUAN_1);
		result.put(SystemConstant.QUAN_2_CODE, SystemConstant.QUAN_2);
		result.put(SystemConstant.QUAN_3_CODE, SystemConstant.QUAN_3);
		result.put(SystemConstant.QUAN_4_CODE, SystemConstant.QUAN_4);
		return result;
	}

}

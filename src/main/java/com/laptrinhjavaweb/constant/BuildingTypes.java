package com.laptrinhjavaweb.constant;

import java.util.HashMap;
import java.util.Map;

public class BuildingTypes {
	public static Map<String,String> getBuildingTypes(){
		Map<String,String> result =  new HashMap<String,String>();
		result.put(SystemConstant.NGUYEN_CAN_CODE, SystemConstant.NGUYEN_CAN);
		result.put(SystemConstant.NOI_THAT_CODE, SystemConstant.NOI_THAT);
		result.put(SystemConstant.TANG_TRET_CODE, SystemConstant.TANG_TRET);
		return result;
	}
	
}


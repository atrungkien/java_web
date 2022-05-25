package com.laptrinhjavaweb.constant;

import java.util.HashMap;
import java.util.Map;

public class BuildingSeach {
		public static Map<String,String> getSeach(){
		Map<String,String> params =  new HashMap<String,String>();
		params.put("name","name");
		params.put("numberofbasement","numberofbasement");
		params.put("district","district");
		params.put("buildingArea","buildingArea");
		params.put("street","street");
		params.put("buildingTypes","buildingTypes");
		params.put("costRentFrom","costRentFrom");
		params.put("costRentTo","costRentTo");
		params.put("areaRentFrom","areaRentFrom");
		params.put("areaRentTo","areaRentTo");
		params.put("staffId","staffId");
		return params;
	}
}

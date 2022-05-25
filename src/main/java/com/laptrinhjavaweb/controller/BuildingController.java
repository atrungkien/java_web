package com.laptrinhjavaweb.controller;

import java.util.List;

import com.laptrinhjavaweb.model.output.BuildingSeachOutput;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.service.impl.BuildingServiceImpl;

public class BuildingController {
	
	private BuildingService buildingService = new BuildingServiceImpl();
	 
	public List<BuildingSeachOutput> findBuilding(String name,String district,
			String buildingArea,String street,String ward,String numberOfBasement,
			String buildingTypes, String costRentFrom,String costRentTo,String areaRentFrom,
			String areaRentTo,String staffId) {
		List<BuildingSeachOutput> results = buildingService.findBuilding(name, district, buildingArea, street,
				ward, numberOfBasement, buildingTypes, costRentFrom, costRentTo, areaRentFrom, areaRentTo, staffId);
		return results;
	}
}

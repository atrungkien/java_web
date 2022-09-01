package com.laptrinhjavaweb.controller;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.model.dto.BuildingDto;
import com.laptrinhjavaweb.model.dto.BuildingRole;
import com.laptrinhjavaweb.model.input.AssignmentInput;
import com.laptrinhjavaweb.model.input.BuildingSeachInput;
import com.laptrinhjavaweb.model.output.BuildingSeachOutput;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.service.impl.BuildingServiceImpl;

public class BuildingController {
	
	private BuildingService buildingService = new BuildingServiceImpl();

	public List<BuildingSeachOutput> findBuilding(Map<String, Object> params, List<String> rentTypes){
		List<BuildingSeachOutput> results = buildingService.findBuilding(params, rentTypes);
		return results;
	}
	
	

}

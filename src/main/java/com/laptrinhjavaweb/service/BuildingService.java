package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.model.output.BuildingSeachOutput;


public interface BuildingService {
	List<BuildingSeachOutput> findBuilding(Map<String, Object> params, List<String> rentTypes);
}

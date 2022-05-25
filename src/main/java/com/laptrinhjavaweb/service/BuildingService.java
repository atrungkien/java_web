package com.laptrinhjavaweb.service;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.model.output.BuildingSeachOutput;

@Service
public interface BuildingService {
	public List<BuildingSeachOutput> findBuilding(String name,String district,
			String buildingArea,String street,String ward,String numberOfBasement,
			String buildingTypes, String costRentFrom,String costRentTo,String areaRentFrom,
			String areaRentTo,String staffId);

}

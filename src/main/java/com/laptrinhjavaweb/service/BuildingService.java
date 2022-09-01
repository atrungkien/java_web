package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.model.dto.BuildingDto;
import com.laptrinhjavaweb.model.input.AssignmentInput;
import com.laptrinhjavaweb.model.input.BuildingSeachInput;
import com.laptrinhjavaweb.model.output.BuildingSeachOutput;
import com.laptrinhjavaweb.repository.entity.RentAreaEntity;


public interface BuildingService {
	List<BuildingSeachOutput> findBuilding(Map<String, Object> params, List<String> rentTypes);
}

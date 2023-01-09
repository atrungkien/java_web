package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.repose.DistrictResponse;

import java.util.List;

public interface DistrictService {
    List<DistrictResponse> getAll();
    List<DistrictResponse> getAllByBuilding(BuildingDTO buildingDTO);
}

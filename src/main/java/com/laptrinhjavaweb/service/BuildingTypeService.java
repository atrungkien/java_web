package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.response.BuildingTypeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BuildingTypeService {
    List<BuildingTypeResponse> getAll();
    List<BuildingTypeResponse> getAllByBuilding(BuildingDTO buildingDTO);
}

package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.response.DistrictResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DistrictService {
    List<DistrictResponse> getAll();
    List<DistrictResponse> getAllByBuilding(BuildingDTO buildingDTO);
}

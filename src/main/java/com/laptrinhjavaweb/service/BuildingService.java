package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.StaffAssignmentDTO;
import com.laptrinhjavaweb.dto.request.BuildingDelRequest;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequest;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import javassist.NotFoundException;

import java.util.List;
import java.util.Map;

public interface BuildingService {

    List<BuildingResponse> findAll(Map<String, Object> params, List<String> rentTypes);

    List<BuildingResponse> findAll(BuildingSearchRequest buildingSearchRequest);

    BuildingDTO findById(Long id);

    void assignmentBuildingToStaffs(StaffAssignmentDTO staffAssignmentDTO);

    void deleteBuildings(BuildingDelRequest buildingDelRequest);

    BuildingDTO save(BuildingDTO buildingDTO);
}

package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingDelRequest;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequest;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import javassist.NotFoundException;

import java.util.List;
import java.util.Map;

public interface BuildingSevice {

    List<BuildingResponse> findAll(BuildingSearchRequest buildingSearchRequest);

    BuildingDTO findById(Long id);

    void assignmentBuilding(List<Long> staffIds, Long buildingID);

    void deleteWithCascade(BuildingDelRequest buildingDelRequest);

    BuildingDTO save(BuildingDTO buildingDTO);
}

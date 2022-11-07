package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingDelRequest;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequest;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.BuildingSevice;
import com.laptrinhjavaweb.service.RentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingSeviceImpl implements BuildingSevice {

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<BuildingResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingResponse> buildingResponses = new ArrayList<>();
        for (BuildingEntity item : buildingRepository.findAll(buildingSearchRequest)) {
            buildingResponses.add(buildingConverter.convertToResponse(item));
        }
        return buildingResponses;
    }

    @Override
    @Transactional
    public BuildingDTO findById(Long id) {
        return id != null ? buildingConverter.convertToBuildingDTO(buildingRepository.findById(id)) : new BuildingDTO();
    }

    @Override
    @Transactional
    public void assignmentBuilding(List<Long> staffIds, Long buildingID) {
        BuildingEntity buildingEntity = buildingRepository.findOne(buildingID);
        if (staffIds != null){
            buildingEntity.setUserEntities(userRepository.findAll(staffIds));
        }
        buildingRepository.save(buildingEntity);
    }

    @Override
    @Transactional
    public void deleteWithCascade(BuildingDelRequest buildingDelRequest) {
        if (!buildingDelRequest.getBuildingIds().isEmpty()) {
            buildingRepository.deleteByIdIn(buildingDelRequest.getBuildingIds());
        }
    }

    @Override
    @Transactional
    public BuildingDTO save(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.convertToBuildingEntity(buildingDTO);
        return buildingConverter.convertToBuildingDTO(buildingRepository.save(buildingEntity));
    }
}

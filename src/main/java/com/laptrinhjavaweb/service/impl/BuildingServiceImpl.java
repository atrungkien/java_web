package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequest;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public List<BuildingResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingResponse> buildingResponses = new ArrayList<>();
        BuildingSearchBuilder buildingSearchBuilder = toBuildingSearchBuilder(buildingSearchRequest);
        for (BuildingEntity item : buildingRepository.findAll(buildingSearchBuilder)) {
            buildingResponses.add(buildingConverter.toBuildingResponse(item));
        }
        return buildingResponses;
    }

    @Override
    public BuildingDTO findById(Long id) {
        return id != null ? buildingConverter.toBuildingDTO(buildingRepository.findById(id)) : new BuildingDTO();
    }

        private BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest buildingSearchRequest) {
            try {
                BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                        .name(buildingSearchRequest.getName())
                        .floorArea(buildingSearchRequest.getFloorArea())
                        .district(buildingSearchRequest.getDistrictCode())
                        .ward(buildingSearchRequest.getWard())
                        .street(buildingSearchRequest.getStreet())
                        .numberOfBasement(buildingSearchRequest.getNumberOfBasement())
                        .direction(buildingSearchRequest.getDirection())
                        .level(buildingSearchRequest.getLevel())
                        .rentAreaFrom(buildingSearchRequest.getRentAreaFrom())
                        .rentAreaTo(buildingSearchRequest.getRentAreaTo())
                        .rentPriceFrom(buildingSearchRequest.getRentPriceFrom())
                        .rentPriceTo(buildingSearchRequest.getRentPriceTo())
                        .managerName(buildingSearchRequest.getManagerName())
                        .managerPhone(buildingSearchRequest.getManagerPhone())
                        .staffID(buildingSearchRequest.getStaffID())
                        .rentTypes(buildingSearchRequest.getRentTypes())
                        .build();
                return buildingSearchBuilder;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

}

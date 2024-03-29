package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.dto.request.AssignmentBuildingRequest;
import com.laptrinhjavaweb.dto.request.BuildingDelRequest;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.exception.MyException;
import com.laptrinhjavaweb.repository.AssignmentBuildingRepository;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.service.RentAreaService;
import com.laptrinhjavaweb.utils.MapUtil;
import com.laptrinhjavaweb.utils.ParseIntUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private RentAreaConverter rentAreaConverter;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RentAreaService rentAreaService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Override
    public List<BuildingResponse> findAll(Map<String, Object> params, List<String> rentTypes) {

        List<BuildingResponse> buildingResponses = new ArrayList<>();
        BuildingSearchBuilder buildingSearchBuilder = toBuildingSearchBuilder(params, rentTypes);
        for (BuildingEntity item : buildingRepository.findAll(buildingSearchBuilder)) {
            buildingResponses.add(buildingConverter.toBuildingResponse(item));
        }
        return buildingResponses;
    }

    @Override
    public BuildingDTO findById(Long id) {
        return id != null ? buildingConverter.toBuildingDTO(buildingRepository.findById(id)) : new BuildingDTO();
    }

    @Override
    public BuildingDTO save(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
        try {
            BuildingEntity buildingEntityGetIDafterSave = buildingRepository.save(buildingEntity);
            if (buildingDTO.getRentArea() != null) {
                List<RentAreaDTO> rentAreaDTOS = rentAreaConverter.toRentAreaDTOs(buildingEntityGetIDafterSave.getId(), buildingDTO);
                rentAreaService.saveAllByBuilding(rentAreaDTOS, buildingDTO);
            }
            return buildingConverter.toBuildingDTO(buildingEntityGetIDafterSave);
        } catch (Exception e) {
            System.out.println("Error BuildingService");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public void assignmentBuilding(AssignmentBuildingRequest assignmentBuildingRequest, Long buildingID) {
        List<Long> staffIdOldS = assignmentBuildingRepository.findByBuildingEntity_Id(buildingID)
                .stream().map(item -> item.getId()).collect(Collectors.toList());
        List<Long> staffIdNews = assignmentBuildingRequest.getStaffIDs();
        if (staffIdNews.isEmpty()) {
            if (staffIdOldS.isEmpty()) {
                return;
            }
            assignmentBuildingRepository.deleteByIdIn(staffIdOldS);
        } else {
            staffIdNews.forEach(item -> {
                if (!staffIdOldS.contains(item)) {
                    try {
                        assignmentBuildingRepository.save(
                                new AssignmentBuildingEntity(
                                        Optional.ofNullable(buildingRepository.findOne(buildingID)).orElseThrow(() -> new NotFoundException("Not foulding Building")),
                                        Optional.ofNullable(userRepository.findOne(item)).orElseThrow(() -> new NotFoundException("Not found user")))
                        );
                    } catch (NotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            staffIdOldS.forEach(item -> {
                if (!staffIdNews.contains(item))
                    assignmentBuildingRepository.delete(item);
            });
        }
    }
    @Override
    @Transactional
    public void delete(BuildingDelRequest buildingDelRequest) throws NotFoundException {
        if (buildingDelRequest.getBuildingIds().size() > 0) {
            Long count = buildingRepository.countByIdIn(buildingDelRequest.getBuildingIds());//check tim thay du tra count du
            if (count != buildingDelRequest.getBuildingIds().size())
                throw new NotFoundException("Not found Building");
            rentAreaRepository.deleteByBuildingEntity_IdIn(buildingDelRequest.getBuildingIds());
            assignmentBuildingRepository.deleteByBuildingEntity_IdIn(buildingDelRequest.getBuildingIds());
            buildingRepository.deleteByIdIn(buildingDelRequest.getBuildingIds());
        }
    }

    private BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> rentTypes) {
        try {
            Map<String, Object> paramsPsd = toLowKey(params);
            BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                    .name((String) MapUtil.getValue(paramsPsd, "name"))
                    .floorArea(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "floorarea")))
                    .district((String) MapUtil.getValue(paramsPsd, "districtcode"))
                    .ward((String) MapUtil.getValue(paramsPsd, "ward"))
                    .street((String) MapUtil.getValue(paramsPsd, "street"))
                    .numberOfBasement(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "numberofbasement")))
                    .direction((String) MapUtil.getValue(paramsPsd, "direction"))
                    .level((String) MapUtil.getValue(paramsPsd, "level"))
                    .rentAreaFrom(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "rentareafrom")))
                    .rentAreaTo(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "rentareato")))
                    .rentPriceFrom(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "rentpricefrom")))
                    .rentPriceTo(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "rentpriceto")))
                    .managerName((String) MapUtil.getValue(paramsPsd, "managername"))
                    .managerPhone((String) MapUtil.getValue(paramsPsd, "managerphone"))
                    .staffID(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "staffid"))).rentTypes(rentTypes)
                    .build();
            return buildingSearchBuilder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<String, Object> toLowKey(Map<String, Object> params) {
        Map<String, Object> results = new HashMap<>();
        for (Map.Entry<String, Object> item : params.entrySet()) {
            results.put(item.getKey().toLowerCase(), item.getValue());
        }
        return results;
    }

    public void validate(int a) throws MyException {
        try {
            if (a == 1)
                throw new MyException("loi test");
        } catch (MyException e) {
            throw e;
        }

    }
}

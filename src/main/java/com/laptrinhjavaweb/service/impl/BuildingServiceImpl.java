package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.dto.StaffAssignmentDTO;
import com.laptrinhjavaweb.dto.request.BuildingDelRequest;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequest;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
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
        return id != null ? buildingConverter.toBuildingDTO(buildingRepository.findById(id).get()) : new BuildingDTO();
    }

    @Override
    public AssignmentBuildingEntity findByBuildingIDByAssignmentID(Long id) {
        return null;
    }

    @Override
    public BuildingDTO save(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
        BuildingEntity buildingEntityGetIDafterSave = buildingRepository.save(buildingEntity);
        if (buildingDTO.getRentArea() != null) {
            List<RentAreaDTO> rentAreaDTOS = rentAreaConverter.toRentAreaDTOs(buildingEntityGetIDafterSave.getId(), buildingDTO);
            rentAreaService.saveAllByBuilding(rentAreaDTOS, buildingDTO);
        }
        return buildingConverter.toBuildingDTO(buildingEntityGetIDafterSave);
    }

    @Override
    public void assignmentBuildingToStaffs(StaffAssignmentDTO staffAssignmentDTO) {
        List<UserEntity> assignees = userRepository.findAssignees(staffAssignmentDTO.getBuildingId());
        BuildingEntity buildingEntity = buildingRepository.findById(staffAssignmentDTO.getBuildingId()).orElse(null);


        assignees.forEach(assignee -> {
            if (staffAssignmentDTO.getStaffIds().stream().noneMatch(staffId -> staffId.equals(assignee.getId()))) {
                AssignmentBuildingEntity assignmentBuildingEntity = (AssignmentBuildingEntity) assignmentBuildingRepository.findAssignmentBuilding(assignee.getId(), staffAssignmentDTO.getBuildingId());
                assignmentBuildingRepository.delete(assignmentBuildingEntity);
            }
        });

        staffAssignmentDTO.getStaffIds().forEach(staffId -> {
            if (assignees.stream().noneMatch(assignee -> assignee.getId().equals(staffId))) {
                UserEntity staff = userRepository.findById(staffId).orElse(null);
                AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
                assignmentBuildingEntity.setBuildings(buildingEntity);
                assignmentBuildingEntity.setAssignee(staff);
                assignmentBuildingRepository.save(assignmentBuildingEntity);
            }
        });
    }


    @Override
    public void deleteBuildings(BuildingDelRequest buildingDelRequest) {
        buildingDelRequest.getBuildingIds().forEach(buildingId ->{
            List<AssignmentBuildingEntity> assignmentBuildingEntityList = assignmentBuildingRepository.findAssignmentBuildingByBuildingId(buildingId);
            if (assignmentBuildingEntityList != null){
                assignmentBuildingRepository.deleteAll(assignmentBuildingEntityList);
            }
            List<RentAreaEntity> rentAreaEntityList = rentAreaRepository.findRentAreasByBuildingId(buildingId);
            if (rentAreaEntityList != null){
                rentAreaRepository.deleteAll(rentAreaEntityList);
            }
            buildingRepository.deleteById(buildingId);
        });
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
                throw new MyException("Error");
        } catch (MyException e) {
            throw e;
        }

    }

}

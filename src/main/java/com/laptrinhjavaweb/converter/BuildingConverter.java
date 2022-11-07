package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.DistrictEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingResponse convertToResponse (BuildingEntity buildingEntity){
        BuildingResponse buildingResponse = new BuildingResponse();
        buildingResponse =modelMapper.map(buildingEntity,BuildingResponse.class);
        String districtName ="";
        for (DistrictEnum item : DistrictEnum.values() ){
            districtName = item.getDistrictValue();
            break;
        }
        buildingResponse.setAddress(buildingEntity.getStreet() + " _ " + buildingEntity.getWard() + " _ " + districtName);
        return null;
    }

    public BuildingDTO convertToBuildingDTO (BuildingEntity buildingEntity){
        BuildingDTO buildingDTO = new BuildingDTO();
        BuildingDTO result = modelMapper.map(buildingEntity, BuildingDTO.class);
        List<String> rentAreas = new ArrayList<>();
        for (RentAreaEntity item : buildingEntity.getRentAreaEntities()) {
            rentAreas.add(String.valueOf(item.getValue()));
        }

        String rentAreaStr = String.join(",",rentAreas);
        buildingDTO.setRentArea(rentAreaStr);
        return result;
    }

    public BuildingEntity convertToBuildingEntity(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        if (buildingDTO.getType() != null) {
            String type = String.join(",", buildingDTO.getType());
            buildingEntity.setType(type);
        }
        if (buildingDTO.getRentArea() != null) {
            List<RentAreaEntity> rentAreaEntityNews = new ArrayList<>();
            String[] rentAreaValues = buildingDTO.getRentArea().trim().split(",");
            for (String item : rentAreaValues) {
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setBuildingEntity(buildingEntity);
                rentAreaEntity.setValue(Integer.parseInt(item));
                rentAreaEntityNews.add(rentAreaEntity);
            }
            buildingEntity.setRentAreaEntities(rentAreaEntityNews);
        }
        return buildingEntity;
    }

}

package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.response.DistrictResponse;
import com.laptrinhjavaweb.enums.DistrictEnum;
import com.laptrinhjavaweb.service.DistrictService;

import java.util.ArrayList;
import java.util.List;

public class DistrictServiceImpl implements DistrictService {

    @Override
    public List<DistrictResponse> getAll() {
        List<DistrictResponse> districtResponses = new ArrayList<>();
        for (DistrictEnum item :DistrictEnum.values()) {
            DistrictResponse districtResponse = new DistrictResponse();
            districtResponse.setCode(item.name());
            districtResponse.setName(item.getDistrictValue());
            districtResponses.add(districtResponse);
        }
        return districtResponses;
    }

    @Override
    public List<DistrictResponse> getAllByBuilding(BuildingDTO buildingDTO) {

        return null;
    }
}

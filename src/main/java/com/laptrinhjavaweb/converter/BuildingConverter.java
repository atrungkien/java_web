package com.laptrinhjavaweb.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.model.output.BuildingSeachOutput;
import com.laptrinhjavaweb.model.output.BuildingUserOutput;
import com.laptrinhjavaweb.repository.DistrictRepository;
import com.laptrinhjavaweb.repository.entity.AssignmentbuildingEntity;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.entity.DistrictEntity;
import com.laptrinhjavaweb.repository.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.entity.UserEntity;
import com.laptrinhjavaweb.repository.impl.DistrictRepositoryImpl;

public class BuildingConverter {
	

	public BuildingSeachOutput toBuildingSearchs(List<BuildingEntity> buildingEntities,List<RentAreaEntity> rentArea,DistrictEntity districtEntity) {
		BuildingSeachOutput buildingSeachOutput = new BuildingSeachOutput();
		for (BuildingEntity item : buildingEntities) {
			buildingSeachOutput.setName(item.getName());
			buildingSeachOutput.setFloorArea(item.getFloorarea());
			buildingSeachOutput.setRentPrice(item.getRentprice());
			buildingSeachOutput.setAddress(item.getStreet() + " - " + item.getWard() + "-" + districtEntity.getName());
			buildingSeachOutput.setRentArea(getRentArea(rentArea));
			buildingSeachOutput.setManagername(item.getManagername());
			buildingSeachOutput.setManagerphone(item.getManagerphone());
	}
		return buildingSeachOutput;
}
		
		private String getRentArea(List<RentAreaEntity> rentArea) {
			List<String> result = new ArrayList<>();
			for (RentAreaEntity item : rentArea) {
				result.add(Integer.toString(item.getValue()));
			}
			return String.join(",", result);
		}

}	

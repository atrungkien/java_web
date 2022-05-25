package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.constant.BuildingName;
import com.laptrinhjavaweb.constant.BuildingSeach;
import com.laptrinhjavaweb.constant.BuildingTypes;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.output.BuildingSeachOutput;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.impl.BuildingRepositoryImpl;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.utils.StringUtils;
@Service
public class BuildingServiceImpl implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository = new BuildingRepositoryImpl();
		
		@Override
		public List<BuildingSeachOutput> findBuilding(String name,String district,
				String buildingArea,String street,String ward,String numberOfBasement,
				String buildingTypes, String costRentFrom,String costRentTo,String areaRentFrom,
				String areaRentTo,String staffId) {
			
			List<BuildingSeachOutput> result = new ArrayList<>();
			
			List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(name, district, buildingArea, 
					street, ward, numberOfBasement, buildingTypes, costRentFrom, costRentTo, areaRentFrom,
					areaRentTo, staffId);
			
			for (BuildingEntity item : buildingEntities) {
				BuildingSeachOutput buildingSeachOutput = new BuildingSeachOutput();
				buildingSeachOutput.setName(item.getName());
				buildingSeachOutput.setCostDescription(item.getCostDescription());
				buildingSeachOutput.setCostRent(item.getCostRent());
				buildingSeachOutput.setFloorarea(item.getFloorarea());
				buildingSeachOutput.setManagerName(item.getManagerName());
				buildingSeachOutput.setManagerPhone(item.getManagerPhone());
				buildingSeachOutput.setOvertimeCost(item.getOvertimeCost());
				buildingSeachOutput.setServiceCost(item.getServiceCost());
				buildingSeachOutput.setBuildingTypes(convertBuildingTypes(item.getBuildingTypes()));
				buildingSeachOutput.setDistricid(convertBuildingname(item.getDistrictid()));
				buildingSeachOutput.setAddress(item.getStreet() + " - " + item.getWard());
				result.add(buildingSeachOutput);
			}
			return result;
		}
		
		
		private String convertBuildingTypes(String type) {
			StringBuilder converType = new StringBuilder();
	 		  Map<String,String> result = BuildingTypes.getBuildingTypes();
	 		if (!StringUtils.isNullOrEmpty(type)) {
	 			String [] Atype = type.split(",");
	 			for(String item : Atype) {
	 				converType.append(result.get(item));
	 			}
	 	  	  }
		return converType.toString();
		}
		
		private String convertBuildingname(String buildingname) {
			StringBuilder converName = new StringBuilder();
	 		  Map<String,String> result = BuildingName.getBuildingName();
	 		if (!StringUtils.isNullOrEmpty(buildingname)) {
	 			String [] AName = buildingname.split(",");
	 			for(String item : AName) {
	 				converName.append(result.get(item));
	 			}
	 	  	  }
		return converName.toString();
		}
		
		private String convertBuildingSeach(String buildingSeach) {
			StringBuilder converSeach = new StringBuilder();
	 		  Map<String,String> result = BuildingSeach.getSeach();
	 		if (!StringUtils.isNullOrEmpty(buildingSeach)) {
	 			String [] ASeach = buildingSeach.split(",");
	 			for(String item : ASeach) {
	 				converSeach.append(result.get(item));
	 			}
	 	  	  }
		return converSeach.toString();
		}
		
		
}
		
		

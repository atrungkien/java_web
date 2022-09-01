package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.model.output.BuildingSeachOutput;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.DistrictRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.entity.DistrictEntity;
import com.laptrinhjavaweb.repository.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.impl.BuildingRepositoryImpl;
import com.laptrinhjavaweb.repository.impl.DistrictRepositoryImpl;
import com.laptrinhjavaweb.repository.impl.RentAreaRepositoryImpl;
import com.laptrinhjavaweb.service.BuildingService;

public class BuildingServiceImpl implements BuildingService {
	private BuildingRepository buildingRepository = new BuildingRepositoryImpl();
	private DistrictRepository districtRepository = new DistrictRepositoryImpl();
	private RentAreaRepository rentAreaRepository = new RentAreaRepositoryImpl();
	private BuildingConverter buildingConverter = new BuildingConverter();

	@Override
	public List<BuildingSeachOutput> findBuilding(Map<String, Object> params,List<String> rentTypes) {
		List<BuildingSeachOutput> results = new ArrayList<>();
		List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(params,rentTypes);	
				for (BuildingEntity item : buildingEntities) {
					DistrictEntity districtEntity = districtRepository.findById(item.getId());
					List<RentAreaEntity> rentArea = rentAreaRepository.findByBuildingId(item.getId());
					BuildingSeachOutput buildingSeachOutput = buildingConverter.toBuildingSearchs(buildingEntities,rentArea, districtEntity);
					
				}
				return results;
	}

}

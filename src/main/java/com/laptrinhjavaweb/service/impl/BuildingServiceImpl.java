package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRepository = new BuildingRepositoryImpl();
	@Autowired
	private DistrictRepository districtRepository = new DistrictRepositoryImpl();
	@Autowired
	private RentAreaRepository rentAreaRepository = new RentAreaRepositoryImpl();
	@Autowired
	private BuildingConverter buildingConverter = new BuildingConverter();

	@Override
	public List<BuildingSeachOutput> findBuilding(Map<String, Object> params,List<String> rentTypes) {
		List<BuildingSeachOutput> results = new ArrayList<>();
		List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(params,rentTypes);	
				for (BuildingEntity item : buildingEntities) {
					
					DistrictEntity districtEntity = districtRepository.findByDistrictID(item.getDistrictid());
					List<RentAreaEntity> rentArea = rentAreaRepository.findByBuildingId(item.getId());
					BuildingSeachOutput buildingSeachOutput = buildingConverter.toBuildingSearchs(buildingEntities,rentArea, districtEntity);
					results.add(buildingSeachOutput);
					
				}
				return results;
	}

}

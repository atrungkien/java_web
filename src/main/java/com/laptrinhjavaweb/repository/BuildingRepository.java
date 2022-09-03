package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;

public interface BuildingRepository {
	List<BuildingEntity> findBuilding(Map<String, Object> params,List<String> rentTypes);

}

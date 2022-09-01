package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.repository.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.entity.UserEntity;

public interface BuildingRepository extends JdbcRepository<BuildingEntity>{
	List<BuildingEntity> findBuilding(Map<String, Object> params,List<String> rentTypes);
}

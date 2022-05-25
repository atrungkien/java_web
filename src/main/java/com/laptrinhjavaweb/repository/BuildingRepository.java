package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.repository.entity.BuildingEntity;

@Repository
public interface BuildingRepository {
	List<BuildingEntity> findBuilding(String name,String district,
			String buildingArea,String street,String ward,String numberOfBasement,
			String buildingTypes, String costRentFrom,String costRentTo,String areaRentFrom,
			String areaRentTo,String staffId);
}

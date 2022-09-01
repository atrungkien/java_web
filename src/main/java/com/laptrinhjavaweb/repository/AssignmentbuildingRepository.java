package com.laptrinhjavaweb.repository;

import java.util.List;

import com.laptrinhjavaweb.repository.entity.AssignmentbuildingEntity;


public interface AssignmentbuildingRepository extends JdbcRepository<AssignmentbuildingEntity> {
	List<AssignmentbuildingEntity> findStaffByBuildingId(Long buildingId);
	AssignmentbuildingEntity findBySql(Long buildingId, Long staffId);
}

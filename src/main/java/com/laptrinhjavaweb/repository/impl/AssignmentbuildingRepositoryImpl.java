package com.laptrinhjavaweb.repository.impl;

import java.util.List;

import com.laptrinhjavaweb.repository.AssignmentbuildingRepository;
import com.laptrinhjavaweb.repository.entity.AssignmentbuildingEntity;

public class AssignmentbuildingRepositoryImpl  extends SimpleJdbcRepository<AssignmentbuildingEntity> implements AssignmentbuildingRepository {

	@Override
	public List<AssignmentbuildingEntity> findStaffByBuildingId(Long buildingId) {
		StringBuilder sql = new StringBuilder("SELECT * FROM assignmentbuilding where buildingid= "+buildingId+"");
		List<AssignmentbuildingEntity> listAssignment = findByCondition(sql.toString());
		return listAssignment;
	}

	@Override
	public AssignmentbuildingEntity findBySql(Long buildingId, Long staffId) {
		StringBuilder sql = new StringBuilder("SELECT * FROM assignmentbuilding where staffid= "+staffId+" and buildingid = "+buildingId+" ");
		List<AssignmentbuildingEntity> listAssignment = findByCondition(sql.toString());
		return listAssignment.isEmpty() ? null : listAssignment.get(0);
	}
	
}

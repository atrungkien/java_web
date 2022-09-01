package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.model.input.AssignmentInput;
import com.laptrinhjavaweb.model.output.AssignmentOutput;
import com.laptrinhjavaweb.repository.AssignmentbuildingRepository;
import com.laptrinhjavaweb.repository.entity.AssignmentbuildingEntity;
import com.laptrinhjavaweb.repository.impl.AssignmentbuildingRepositoryImpl;
import com.laptrinhjavaweb.service.AssignmentBuildingSevice;

public class AssignmentBuildingServiceImpl implements AssignmentBuildingSevice{
	 
	AssignmentbuildingRepository assignmentbuildingRepository = new AssignmentbuildingRepositoryImpl();
	AssignmentInput assignmentInput = new AssignmentInput();
	
	
	public void assignmentBuilding(Long buildingId, List<Long> staffId) {
		List<Long> ListStaff = findStaffByBuildingId(buildingId);
		
		AssignmentbuildingEntity assignement = new AssignmentbuildingEntity();
		assignement.setBuildingid(buildingId);
		
		for (long id : ListStaff) {
			if (!ListStaff.contains(id)) {
			assignement.setStaffid(id);
			assignmentbuildingRepository.insert(assignement);
			}
		}
		
		for (long id : ListStaff) {
			if (!staffId.contains(id)) {
			AssignmentbuildingEntity assignmentEntity = assignmentbuildingRepository.findBySql(buildingId, id);
			assignmentbuildingRepository.delete(assignmentEntity.getId());
			}
		}
	}
		
	@Override
	public List<Long> findStaffByBuildingId(Long buildingId) {
		List<Long> staffIds = new ArrayList<>();
		List<AssignmentbuildingEntity> assignmentBuildingEntity = assignmentbuildingRepository.findStaffByBuildingId(buildingId);
		if (assignmentBuildingEntity != null) {
			for (AssignmentbuildingEntity item : assignmentBuildingEntity) {
				staffIds.add(item.getStaffid());
			}
			return staffIds;
		}
		return null;
	}
	
	/*
	@Override
	public AssignmentOutput assignBuildingToStaffs(AssignmentInput assignmentInput) {
		
		Long buildingId = assignmentInput.getId();
		List<Long> inputIds = assignmentInput.getStaffIds();
		List<Long> currentIds = findStaffByBuildingId(buildingId);
		
		List<Long> StaffIdsToDelete = findItemOfSourceButNotInTarget(currentIds,inputIds);
		List<Long> StaffIdsToAdd = findItemOfSourceButNotInTarget(inputIds,currentIds);
		
		for (Long id : StaffIdsToDelete) {
			AssignmentbuildingEntity assignmentEntity = assignmentbuildingRepository.findBySql(buildingId, id);
			assignmentbuildingRepository.delete(assignmentEntity.getId());
		}
		
		for (Long id : StaffIdsToAdd) {
			assignmentbuildingRepository.insert(new AssignmentbuildingEntity(id,buildingId));
		}
		
		return new AssignmentOutput(buildingId,currentIds,findStaffByBuildingId(buildingId));
	}

	private List<Long> findItemOfSourceButNotInTarget(List<Long> currentIds, List<Long> inputIds) {
		List<Long> addnew = null;
		for (long id : currentIds) {
			if (!currentIds.contains(id)) {
				addnew.add(id);
			}
		}
		return addnew;
	}*/
	
		
}

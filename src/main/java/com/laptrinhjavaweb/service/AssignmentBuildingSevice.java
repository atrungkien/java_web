package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.input.AssignmentInput;
import com.laptrinhjavaweb.model.output.AssignmentOutput;

public interface AssignmentBuildingSevice {
	void assignmentBuilding(Long buildingId, List<Long> staffId);
	List<Long> findStaffByBuildingId(Long buildingId);
	//AssignmentOutput assignBuildingToStaffs(AssignmentInput assignmentInput);
}

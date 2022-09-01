package com.laptrinhjavaweb.model.output;

import java.util.List;

public class AssignmentOutput {
	
	private List<Long> staffIds;
	private Long buildingId;
	
	public AssignmentOutput() {
		
	}
	
	public AssignmentOutput(Long buildingId, List<Long> currentIds, List<Long> findStaffByBuildingId) {
	
	}
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingid(Long buildingId) {
		this.buildingId = buildingId;
	}
	public List<Long> getStaffIds() {
		return staffIds;
	}
	public void setStaffIds(List<Long> staffIds) {
		this.staffIds = staffIds;
	}
}

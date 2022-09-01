package com.laptrinhjavaweb.repository.entity;
public class AssignmentbuildingEntity extends BaseEntity {
	
	private Long id;
	private Long staffid;
	private Long buildingid;
	
public AssignmentbuildingEntity() {
		
	}
	
	public AssignmentbuildingEntity(Long id, Long buildingId2) {

	}
	public Long getStaffid() {
		return staffid;
	}
	public void setStaffid(Long listStaffId) {
		this.staffid = listStaffId;
	}
	public Long getBuildingid() {
		return buildingid;
	}
	public void setBuildingid(Long buildingid) {
		this.buildingid = buildingid;
	}
	
}

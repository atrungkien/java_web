package com.laptrinhjavaweb.model.output;

public class BuildingSeachOutput {
	private String name;
	private String address;
	private String managerName;
	private String managerPhone;
	private String floorarea;
	private String costDescription;
	private String buildingTypes;
	private Integer costRent;
	private String serviceCost;
	private String overtimeCost;
	private String districid;
	
	
	
	public String getDistricid() {
		return districid;
	}
	public void setDistricid(String districid) {
		this.districid = districid;
	}
	public String getBuildingTypes() {
		return buildingTypes;
	}
	public void setBuildingTypes(String buildingTypes) {
		this.buildingTypes = buildingTypes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		

	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public String getFloorarea() {
		return floorarea;
	}
	public void setFloorarea(String floorarea) {
		this.floorarea = floorarea;
	}
	public String getCostDescription() {
		return costDescription;
	}
	public void setCostDescription(String costDescription) {
		this.costDescription = costDescription;
	}
	public Integer getCostRent() {
		return costRent;
	}
	public void setCostRent(Integer costRent) {
		this.costRent = costRent;
	}
	public String getServiceCost() {
		return serviceCost;
	}
	public void setServiceCost(String serviceCost) {
		this.serviceCost = serviceCost;
	}
	public String getOvertimeCost() {
		return overtimeCost;
	}
	public void setOvertimeCost(String overtimeCost) {
		this.overtimeCost = overtimeCost;
	}
	
}

package com.laptrinhjavaweb.model.dto;

public class BuildingDto {
	private String name;
	private String street;
	private String ward;
	private String managerphone;
	private String managername;
	private Integer numberofbasement;
	private Integer floorarea;
	private Integer rentpriceTo;
	private Integer rentpriceFrom;
	private Integer rentareaFrom;
	private Integer rentareaTo;
	private Long buildingId;
	private Integer[] staffIds = {1,2};
	private String[] district;
	private String[] types;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getManagerphone() {
		return managerphone;
	}
	public void setManagerphone(String managerphone) {
		this.managerphone = managerphone;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public Integer getNumberofbasement() {
		return numberofbasement;
	}
	public void setNumberofbasement(Integer numberofbasement) {
		this.numberofbasement = numberofbasement;
	}
	public Integer getFloorarea() {
		return floorarea;
	}
	public void setFloorarea(Integer floorarea) {
		this.floorarea = floorarea;
	}
	public Integer getRentpriceTo() {
		return rentpriceTo;
	}
	public void setRentpriceTo(Integer rentpriceTo) {
		this.rentpriceTo = rentpriceTo;
	}
	public Integer getRentpriceFrom() {
		return rentpriceFrom;
	}
	public void setRentpriceFrom(Integer rentpriceFrom) {
		this.rentpriceFrom = rentpriceFrom;
	}
	public Integer getRentareaFrom() {
		return rentareaFrom;
	}
	public void setRentareaFrom(Integer rentareaFrom) {
		this.rentareaFrom = rentareaFrom;
	}
	public Integer getRentareaTo() {
		return rentareaTo;
	}
	public void setRentareaTo(Integer rentareaTo) {
		this.rentareaTo = rentareaTo;
	}
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	public Integer[] getStaffIds() {
		return staffIds;
	}
	public void setStaffIds(Integer[] staffIds) {
		this.staffIds = staffIds;
	}
	public String[] getDistrict() {
		return district;
	}
	public void setDistrict(String[] district) {
		this.district = district;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	
	
}

package com.laptrinhjavaweb.model.input;

public class BuildingSeachInput {
	private Long staffid;
	private Long buildingid;
	private String name;
	private String street;
	private String ward;
	private String managerphone;
	private String managername;
	private String renttype;
	private String[] district;
	private String[] types;
	private Integer numberofbasement;
	private Integer floorarea;
	private Integer rentpricedescription;
	private Integer rentprice;
	
	public Long getStaffid() {
		return staffid;
	}
	public void setStaffid(Long staffid) {
		this.staffid = staffid;
	}
	public Long getBuildingid() {
		return buildingid;
	}
	public void setBuildingid(Long buildingid) {
		this.buildingid = buildingid;
	}
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
	public String getRenttype() {
		return renttype;
	}
	public void setRenttype(String renttype) {
		this.renttype = renttype;
	}
	public String[] getDistrict() {
		return district;
	}
	public void setDistrict(String[] district) {
		this.district = district;
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
	public Integer getRentpricedescription() {
		return rentpricedescription;
	}
	public void setRentpricedescription(Integer rentpricedescription) {
		this.rentpricedescription = rentpricedescription;
	}
	public Integer getRentprice() {
		return rentprice;
	}
	public void setRentprice(Integer rentprice) {
		this.rentprice = rentprice;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
}

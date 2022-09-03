package com.laptrinhjavaweb.repository.entity;


public class BuildingEntity{
	
	private Long id;
	private String name;
	private String street;
	private Long districtID;
	private String ward;
	private String serviceFee;
	private String managerphone;
	private String managername;
	private String districtid;
	private Integer numberofbasement;
	private Integer floorarea;
	private Integer rentpricedescription;
	private Integer rentprice;
	private Integer rentArea;
	
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
	public Integer getFloorarea() {
		return floorarea;
	}
	public void setFloorarea(Integer floorarea) {
		this.floorarea = floorarea;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getRentArea() {
		return rentArea;
	}
	public void setRentArea(Integer rentArea) {
		this.rentArea = rentArea;
	}
	public Long getDistrictID() {
		return districtID;
	}
	public void setDistrictID(Long districtID) {
		this.districtID = districtID;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}

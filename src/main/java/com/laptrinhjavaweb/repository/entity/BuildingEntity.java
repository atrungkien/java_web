package com.laptrinhjavaweb.repository.entity;

import com.laptrinhjavaweb.anotation.Column;
import com.laptrinhjavaweb.anotation.Entity;
import com.laptrinhjavaweb.anotation.Table;
@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity{
	
	@Column(name = "name")
	private String name;
	@Column(name = "street")
	private String street;
	@Column(name = "ward")
	private String ward;
	@Column(name = "managerphone")
	private String managerphone;
	@Column(name = "managername")
	private String managername;
	@Column(name = "districtid")
	private String districtid;
	@Column(name = "numberofbasement")
	private Integer numberofbasement;
	@Column(name = "floorarea")
	private Integer floorarea;
	@Column(name = "rentpricedescription")
	private Integer rentpricedescription;
	@Column(name = "rentprice")
	private Integer rentprice;
	@Column(name = "rentArea")
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
}

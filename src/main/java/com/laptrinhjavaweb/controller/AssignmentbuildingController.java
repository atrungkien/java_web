package com.laptrinhjavaweb.controller;

import java.util.List;

import com.laptrinhjavaweb.service.AssignmentBuildingSevice;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.service.impl.AssignmentBuildingServiceImpl;
import com.laptrinhjavaweb.service.impl.BuildingServiceImpl;

public class AssignmentbuildingController {
	private AssignmentBuildingSevice assignmentBuildingSevice = new AssignmentBuildingServiceImpl();
	
	public void assignmentbuilding(Long buildingId, List<Long> listStaffId) {
		assignmentBuildingSevice.assignmentBuilding(buildingId, listStaffId);
	}
	public void findStaffByBuildingId(Long buildingId) {
		List<Long> listStaffId = assignmentBuildingSevice.findStaffByBuildingId(buildingId);
		System.out.print("Danh sách ID nhân viên quản lý tòa nhà buildingId = "+buildingId+": ");
		System.out.println(String.join(", ", listStaffId.toString()));
	}
}

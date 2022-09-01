package com.laptrinhjavaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.model.output.BuildingUserOutput;
import com.laptrinhjavaweb.service.AssignmentBuildingSevice;

@RestController
@RequestMapping("/api")
public class AssignmentbuildingAPI {
	
	@Autowired
	private AssignmentBuildingSevice assignmentBuildingSevice;
	
	@PostMapping("/Staff")
	public List<BuildingUserOutput> getStaff(@RequestBody Long buildingId,
											 @RequestBody List<Long> listStaffId){
			assignmentBuildingSevice.assignmentBuilding(buildingId, listStaffId);
			return getStaff(buildingId, listStaffId);
	}
}

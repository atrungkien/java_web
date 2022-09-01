package com.laptrinhjavaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.model.output.BuildingUserOutput;
import com.laptrinhjavaweb.service.UserService;

@RestController
public class UserAPI {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/api/Staff")
	public List<BuildingUserOutput> getStaff(@PathVariable (value = "buildingid") Long BuildingId){
		
			List<Long> LitStaff = userService.findStaffByBuildingId(BuildingId);
			System.out.println("Danh sách nhân viên đang quản lí tòa nhà : " +BuildingId+ ": " );
			System.out.println(String.join(" , ", LitStaff.toString()));
			return getStaff(BuildingId);
	}
}

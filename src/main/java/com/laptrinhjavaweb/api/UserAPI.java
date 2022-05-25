package com.laptrinhjavaweb.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.model.user.BuildingUser;
@RestController
public class UserAPI {
	@GetMapping(value = "/api/Staff")
	public List<BuildingUser> getStaff(@PathVariable (value = "buildingid" , required = false) Long BuildingId){
		List<BuildingUser> result = new ArrayList<>();
		return result;
	}
}

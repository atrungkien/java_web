package com.laptrinhjavaweb.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.constant.BuildingSeach;
import com.laptrinhjavaweb.model.output.BuildingSeachOutput;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.impl.BuildingRepositoryImpl;
import com.laptrinhjavaweb.service.BuildingService;

//@Controller
@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
	
	@Autowired
	private BuildingService buildingservice;
	Map<String,String> result = BuildingSeach.getSeach();
	@GetMapping
	public List<BuildingSeachOutput> findBuilding (@RequestParam Map<String,String> result)

//	@RequestParam (value = "name" , required = false) String name,
//	@RequestParam (value = "numberofbasement",required = false) String numberOfBasement,
//	@RequestParam(value = "district" , required = false) String district,
//	@RequestParam (value = "buildingArea" , required = false)
//	String buildingArea, @RequestParam (value = "street" ,required = false) String street, 
//	@RequestParam (value = "ward" , required = false) String ward,
//	@RequestParam (value = "buildingTypes" , required = false) String buildingTypes, 
//	@RequestParam (value = "costRentFrom" , required = false) String costRentFrom,
//	@RequestParam (value = "costRentTo" , required = false) String costRentTo,
//	@RequestParam (value = "areaRentFrom" , required = false) String areaRentFrom,
//	@RequestParam (value = "areaRentTo" , required = false) String areaRentTo,
//	@RequestParam (value = "staffId" , required = false) String staffId)
	{
		
		String name = (String) result.get("name");
		String district = (String) result.get("district");
		String buildingArea = (String) result.get("buildingArea");
		String street = (String) result.get("street");
		String ward = (String) result.get("ward");
		String numberOfBasement = (String) result.get("numberOfBasement");
		String buildingTypes = (String) result.get("buildingTypes");
		String costRentFrom = (String) result.get("costRentFrom");
		String costRentTo = (String) result.get("costRentTo");
		String areaRentFrom = (String) result.get("areaRentFrom");
		String areaRentTo = (String) result.get("areaRentTo");
		String staffId = (String) result.get("staffId");
	
		List<BuildingSeachOutput> results = buildingservice.findBuilding
				(name, district, buildingArea, street,
				ward, numberOfBasement, buildingTypes, costRentFrom,
				costRentTo, areaRentFrom, areaRentTo, staffId);
		return results;
	}
	
	@PostMapping
	public @ResponseBody BuildingSeachOutput insertBuilding(@RequestBody BuildingSeachOutput newBuilding){
		BuildingSeachOutput result = new BuildingSeachOutput();
		
		return result;
	}
	

	@PutMapping
	public @ResponseBody BuildingSeachOutput updatetBuilding(@RequestBody BuildingSeachOutput updatetBuilding){
		BuildingSeachOutput result = new BuildingSeachOutput();
		return result;
	}
	
	@GetMapping("/{id}")
	public @ResponseBody BuildingSeachOutput getBuildingDetail(@PathVariable ("id") Long id){
		BuildingSeachOutput result = new BuildingSeachOutput();
		return result;
	}
}

package com.laptrinhjavaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.customexception.FieldRequiredException;
import com.laptrinhjavaweb.model.output.BuildingSeachOutput;
import com.laptrinhjavaweb.service.BuildingService;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
	
	@Autowired
	private BuildingService buildingservice;
	@GetMapping
	public List<BuildingSeachOutput> findBuilding (@RequestParam Map<String, Object> params, List<String> rentTypes)
	{
		List<BuildingSeachOutput> results = buildingservice.findBuilding(params, rentTypes);			
		return results;
	}
	
	
	
	@PostMapping
	public  BuildingSeachOutput createBuilding(@RequestBody BuildingSeachOutput newBuilding){
	
			validateData(newBuilding);
			return newBuilding;
	}
	
	private void validateData(BuildingSeachOutput newBuilding)  {
		if (newBuilding.getName() == null) {
			throw new FieldRequiredException("name is null");
		}
	}

	


	@PutMapping
	public  BuildingSeachOutput updatetBuilding(@RequestBody BuildingSeachOutput updatetBuilding){
		BuildingSeachOutput result = new BuildingSeachOutput();
		return result;
	}
	
	@GetMapping("/{id}")
	public  BuildingSeachOutput getBuildingDetail(@PathVariable ("id") Long id){
		BuildingSeachOutput result = new BuildingSeachOutput();
		return result;
	}
}

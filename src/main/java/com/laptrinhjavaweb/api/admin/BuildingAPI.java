package com.laptrinhjavaweb.api.admin;


import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.dto.response.StaffAssignmentResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/building")

public class BuildingAPI {

    @GetMapping
    public List<BuildingResponse> findAll(@RequestParam(required = false) Map<String, Object> params,
                                          @RequestParam(name = "renttypes", required = false) List<String> rentTypes) {
        return null;
    }

    @PostMapping
    public BuildingDTO save(@RequestBody(required = false) BuildingDTO buildingDTO) {
        return null;
    }

    @GetMapping("/{id}/staff")
    public List<StaffAssignmentResponse> getAllStaffByBuilding(@PathVariable("id") Long id) {
        return null;
    }
}




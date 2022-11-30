package com.laptrinhjavaweb.api.admin;


import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.StaffAssignmentDTO;
import com.laptrinhjavaweb.dto.request.BuildingDelRequest;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.dto.response.ReponseDTO;
import com.laptrinhjavaweb.dto.response.StaffAssignmentResponse;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.service.impl.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<BuildingResponse> findAll(@RequestParam(required = false) Map<String, Object> params,
                                          @RequestParam(name = "renttypes", required = false) List<String> rentTypes) {
        return buildingService.findAll(params, rentTypes);
    }
    @PostMapping
    public BuildingDTO save(@RequestBody(required = false) BuildingDTO buildingDTO,@PathVariable("id") Long id) {
        return buildingService.save(buildingDTO);
    }

    @GetMapping("/{id}/staff")
    public List<StaffAssignmentResponse> getAllStaffByBuilding(@PathVariable("id") Long id) {
        return userService.getAllStaffAssignmentBuilding(id);
    }

//    @GetMapping("/{id}/staff")
//    public ReponseDTO loadStaff(@PathVariable("id") Long id){
//        ReponseDTO result = new ReponseDTO();
//        List<StaffResponseDTO> staffResponseDTOS = userService.findStaffByBuildingId(id);
//        result.setMessage("success");
//        result.setData(staffResponseDTOS);
//        return result;
//    }

    @PostMapping("/{id}/assignment")
    public ReponseDTO assignmentBuilding(@RequestBody StaffAssignmentDTO staffAssignmentDTO) {
        buildingService.assignmentBuildingToStaffs(staffAssignmentDTO);
        ReponseDTO results = new ReponseDTO();
        results.setMessage("success");
        return results;
    }

//    @DeleteMapping
//    public BuildingDelRequest delete(@RequestBody BuildingDelRequest buildingDelRequest) throws NotFoundException {
//        //buildingService.delete(buildingDelRequest);
//        return buildingDelRequest;
//    }
    @DeleteMapping
    public ReponseDTO deleteBuilding(@RequestBody BuildingDelRequest buildingDelRequest){
        buildingService.deleteBuildings(buildingDelRequest);
        ReponseDTO result = new ReponseDTO();
        result.setMessage("success");
        return result;
    }

}


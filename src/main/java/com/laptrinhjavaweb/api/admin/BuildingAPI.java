package com.laptrinhjavaweb.api.admin;


import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingDelRequest;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.dto.response.StaffAssignmentResponse;
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
//    @Autowired
//    private BuildingService buildingService;
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    public List<BuildingResponse> findAll(@RequestParam(required = false) Map<String, Object> params,
//                                          @RequestParam(name = "renttypes", required = false) List<String> rentTypes) {
//        return buildingService.findAll(params, rentTypes);
//    }
}


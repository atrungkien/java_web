package com.laptrinhjavaweb.dto;

import java.util.List;

public class StaffAssignmentDTO {

    private Long buildingId;
    private List<Long> StaffIds;

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public List<Long> getStaffIds() {
        return StaffIds;
    }

    public void setStaffIds(List<Long> staffIds) {
        StaffIds = staffIds;
    }
}

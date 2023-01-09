package com.laptrinhjavaweb.dto.repose;

import com.laptrinhjavaweb.dto.UserDTO;

public class StaffAssignmentResponse extends UserDTO {
    private String checked = "";

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}

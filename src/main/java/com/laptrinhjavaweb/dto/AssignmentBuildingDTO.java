package com.laptrinhjavaweb.dto;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;

public class AssignmentBuildingDTO {

    private UserEntity users;
    private BuildingEntity buildings;

    public UserEntity getUsers() {
        return users;
    }

    public void setUsers(UserEntity users) {
        this.users = users;
    }

    public BuildingEntity getBuildings() {
        return buildings;
    }

    public void setBuildings(BuildingEntity buildings) {
        this.buildings = buildings;
    }
}

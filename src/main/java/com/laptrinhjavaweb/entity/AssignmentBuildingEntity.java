package com.laptrinhjavaweb.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assignmentbuilding")

public class AssignmentBuildingEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "staffid",nullable = false)
    private UserEntity users;

    @ManyToOne
    @JoinColumn(name = "buildingid",nullable = false)
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

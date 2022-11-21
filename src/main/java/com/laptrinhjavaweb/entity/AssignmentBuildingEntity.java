package com.laptrinhjavaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "assignmentbuilding")

public class AssignmentBuildingEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staffid")
    private UserEntity staffs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buildingid")
    private BuildingEntity buildings;

    public UserEntity getUsers() {
        return staffs;
    }

    public void setUsers(UserEntity users) {
        this.staffs = staffs;
    }

    public BuildingEntity getBuildings() {
        return buildings;
    }

    public void setBuildings(BuildingEntity buildings) {
        this.buildings = buildings;
    }
}

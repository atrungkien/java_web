package com.laptrinhjavaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRoleEntiy extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "userid",nullable = false)
    private UserEntity users;

    @ManyToOne
    @JoinColumn(name = "roleid",nullable = false)
    private RoleEntity roles;


    public UserEntity getUsers() {
        return users;
    }

    public void setUsers(UserEntity users) {
        this.users = users;
    }

    public RoleEntity getRoles() {
        return roles;
    }

    public void setRoles(RoleEntity roles) {
        this.roles = roles;
    }
}

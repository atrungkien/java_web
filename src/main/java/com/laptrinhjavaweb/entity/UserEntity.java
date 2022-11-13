package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = -4988455421375043688L;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<AssignmentCustomerEntity> assignmentCustomerss = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "userid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "roleid", nullable = false))
    private List<RoleEntity> roles = new ArrayList<>();


    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<AssignmentCustomerEntity> assignmentCustomers = new ArrayList<>();
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "assignmentcustomer",
//            joinColumns = @JoinColumn(name = "staffid",nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "customerid",nullable = false))
//    private List<CustomerEntity> customerEntities = new ArrayList<>();

//    public List<CustomerEntity> getCustomerEntities() {
//        return customerEntities;
//    }
//
//    public void setCustomerEntities(List<CustomerEntity> customerEntities) {
//        this.customerEntities = customerEntities;
//    }


    public List<AssignmentCustomerEntity> getAssignmentCustomers() {
        return assignmentCustomers;
    }

    public void setAssignmentCustomers(List<AssignmentCustomerEntity> assignmentCustomers) {
        this.assignmentCustomers = assignmentCustomers;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package com.laptrinhjavaweb.entity;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "assignmentcustomer")

public class AssignmentCustomerEntity {
    @ManyToOne
    @JoinColumn(name = "staffid",nullable = false)
    private UserEntity users;

    @ManyToOne
    @JoinColumn(name = "customerid",nullable = false)
    private CustomerEntity customers;

    public UserEntity getUsers() {
        return users;
    }

    public void setUsers(UserEntity users) {
        this.users = users;
    }

    public CustomerEntity getCustomers() {
        return customers;
    }

    public void setCustomers(CustomerEntity customers) {
        this.customers = customers;
    }
}

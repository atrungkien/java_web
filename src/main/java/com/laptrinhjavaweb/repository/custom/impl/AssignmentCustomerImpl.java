package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.AssignmentCustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.custom.AssignmentBuildingCustom;
import com.laptrinhjavaweb.repository.custom.AssignmentCustomerCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class AssignmentCustomerImpl implements AssignmentCustomerCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public AssignmentBuildingCustom findById(Long StaffId) {
        String sql = "select * from assignmentbuilding as as where as.buildingId = " + StaffId + "";
        Query query = entityManager.createNativeQuery(sql.toString(), AssignmentCustomerEntity.class);
        return (AssignmentBuildingCustom) query.getResultList();
    }
}

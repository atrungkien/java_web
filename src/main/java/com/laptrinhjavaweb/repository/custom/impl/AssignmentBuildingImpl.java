package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.custom.AssignmentBuildingCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class AssignmentBuildingImpl implements AssignmentBuildingCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public AssignmentBuildingCustom findById(Long id) {
        String sql = "select * from assignmentbuilding as as where as.buildingId = "+id+"";
        Query query = entityManager.createNativeQuery(sql, UserEntity.class);
        return (AssignmentBuildingCustom) query.getResultList();
    }
}

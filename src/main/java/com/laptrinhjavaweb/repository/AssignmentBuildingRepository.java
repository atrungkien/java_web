package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.repository.custom.AssignmentBuildingCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentBuildingRepository extends AssignmentBuildingCustom, JpaRepository<AssignmentBuildingEntity, Long> {
}

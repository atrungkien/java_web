package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long> {
    //void deleteByUsers_Id(Long staffId);
    AssignmentBuildingEntity findByBuildingIDByAssignmentID(Long id);
    AssignmentBuildingEntity finById(Long id);
    @Query("select ab from AssignmentBuildingEntity ab Where ab.building.id = ?1")
    List<AssignmentBuildingEntity> findAssignmentBuildingByBuildingId(Long buildingId);

    @Query("select ab from AssignmentBuildingEntity ab Where ab.assignee.id = ?1 AND ab.buildings.id = ?2")
    List<AssignmentBuildingEntity> findAssignmentBuilding(Long staffId, Long buildingId);
}

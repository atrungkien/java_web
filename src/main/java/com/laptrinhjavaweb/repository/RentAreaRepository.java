package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.custom.RentAreaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentAreaRepository extends RentAreaRepositoryCustom, JpaRepository<RentAreaEntity,Long> {
    List<RentAreaEntity> findByBuildingEntity(BuildingEntity buildingEntity);
    void deleteByBuildingEntity_Id(Long id);
    void deleteByBuildingEntity_IdIn(List<Long> id);
    @Query("select ra FROM RentAreaEntity ra WHERE ra.building.id = ?1")
    List<RentAreaEntity> findRentAreasByBuildingId(Long buildingId);
}

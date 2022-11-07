package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> , BuildingRepositoryCustom {
    BuildingEntity findById(Long id);
    void deleteByIdIn(List<Long> ids);
}

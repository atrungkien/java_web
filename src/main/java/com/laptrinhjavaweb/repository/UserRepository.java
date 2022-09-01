package com.laptrinhjavaweb.repository;

import java.util.List;

import com.laptrinhjavaweb.repository.entity.UserEntity;

public interface UserRepository  extends JdbcRepository<UserEntity>{
	List<UserEntity> findStaff();
	List<UserEntity> findStaffByBuildingId(Long buildingId);
}

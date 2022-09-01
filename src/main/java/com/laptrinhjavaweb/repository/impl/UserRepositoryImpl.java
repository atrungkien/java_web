package com.laptrinhjavaweb.repository.impl;

import java.util.List;

import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.entity.UserEntity;

public class UserRepositoryImpl extends SimpleJdbcRepository<UserEntity> implements UserRepository {

	@Override
	public List<UserEntity> findStaff() {

		StringBuilder sql = new StringBuilder("SELECT * FROM user");
		sql.append(" INNER JOIN user_role AS ur ON ur.userid= user.id");
		sql.append(" where 1=1");
		sql.append(" AND ur.roleid = 2 ");
		List<UserEntity> listUserEntity = super.findByCondition(sql.toString());
		return listUserEntity;
	
	}

	@Override
	public List<UserEntity> findStaffByBuildingId(Long buildingId) {
		
		StringBuilder sql = new StringBuilder("SELECT * FROM user");
		sql.append(" INNER JOIN assignmentbuilding AS asb ON asb.staffid=user.id");
		sql.append(" where 1=1");
		sql.append(" AND asb.buildingid =" + buildingId + " ");
		List<UserEntity> listUserEntity = super.findByCondition(sql.toString());
		return listUserEntity;
	}
	
}

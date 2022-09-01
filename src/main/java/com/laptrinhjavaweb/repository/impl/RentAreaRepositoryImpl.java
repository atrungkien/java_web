package com.laptrinhjavaweb.repository.impl;

import java.util.List;

import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.entity.RentAreaEntity;

public class RentAreaRepositoryImpl extends SimpleJdbcRepository<RentAreaEntity> implements RentAreaRepository {

	@Override
	public List<RentAreaEntity> findByBuildingId(Long id) {
		String sql = "select * from rentarea where buildingid = " + id + " ";
		return findByCondition(sql);
	}
}

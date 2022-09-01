package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.repository.entity.BuildingEntity;

public interface JdbcRepository<T> {
	List<T> findAll();
	T findById(Long staffid);
	void insert(Object object);
	List<T> findByCondition(String sql);
	void update(Object object);
	void delete(Object  object);
}

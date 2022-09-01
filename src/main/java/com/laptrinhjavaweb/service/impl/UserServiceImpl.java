package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.entity.UserEntity;
import com.laptrinhjavaweb.repository.impl.UserRepositoryImpl;
import com.laptrinhjavaweb.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository = new UserRepositoryImpl();
	
	@Override
	public List<Long> findStaff() {
		List<Long> ListStaffId = new ArrayList<>();
		List<UserEntity> userEntities = userRepository.findStaff();
		if (userEntities != null) {
			for (UserEntity item : userEntities) {
				ListStaffId.add(item.getId());
			}
			return ListStaffId;
		}
		return null;
	}

	@Override
	public List<Long> findStaffByBuildingId(Long buildingID) {
		List<Long> ListStaffId = new ArrayList<>();
		List<UserEntity> userEntities = userRepository.findStaffByBuildingId(buildingID);
		if (userEntities != null) {
			for (UserEntity item : userEntities) {
				ListStaffId.add(item.getId());
			}
			return ListStaffId;
		}
		return null;
	}

}

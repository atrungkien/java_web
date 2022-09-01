package com.laptrinhjavaweb.service;

import java.util.List;

public interface UserService {

	List<Long> findStaff();
	List<Long> findStaffByBuildingId(Long buildingID);

}

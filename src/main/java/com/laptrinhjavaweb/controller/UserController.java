package com.laptrinhjavaweb.controller;

import java.util.List;

import com.laptrinhjavaweb.service.UserService;
import com.laptrinhjavaweb.service.impl.UserServiceImpl;

public class UserController {
	private UserService userService = new UserServiceImpl();
	
	public void findStaff() {
		List<Long> ListStaff = userService.findStaff();
		System.out.println("Toàn bộ danh sách nhân viên : ");
		System.out.println(String.join(" , ", ListStaff.toString()));
	}
	
	public void findStaffByBuildingId(Long buildingID) {
		List<Long> LitStaff = userService.findStaffByBuildingId(buildingID);
		System.out.println("Danh sách nhân viên đang quản lí tòa nhà : " +buildingID+ ": " );
		System.out.println(String.join(" , ", LitStaff.toString()));
	}
	
}

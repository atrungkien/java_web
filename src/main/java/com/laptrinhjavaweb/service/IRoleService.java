package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.RoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface IRoleService {
	List<RoleDTO> findAll();
	Map<String,String> getRoles();
}

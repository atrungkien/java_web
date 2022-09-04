package com.laptrinhjavaweb.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.repository.entity.DistrictEntity;
@Component
public class DistrictMapper {

	public DistrictEntity mapRow(ResultSet rs) {
		try {
			DistrictEntity district = new DistrictEntity();
			district.setId(rs.getLong("id"));
			district.setName(rs.getString("name"));
			district.setCode(rs.getString("code"));
			return district;
		} catch (SQLException e) {
			return null;
		}	
	}

}

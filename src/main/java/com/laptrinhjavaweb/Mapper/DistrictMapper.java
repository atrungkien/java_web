package com.laptrinhjavaweb.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.laptrinhjavaweb.repository.entity.DistrictEntity;

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

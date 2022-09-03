package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.laptrinhjavaweb.Mapper.BuildingMapper;
import com.laptrinhjavaweb.Mapper.DistrictMapper;
import com.laptrinhjavaweb.repository.DistrictRepository;
import com.laptrinhjavaweb.repository.entity.DistrictEntity;
import com.laptrinhjavaweb.repository.entity.RentAreaEntity;
import com.laptrinhjavaweb.utils.ConnectionUtils;

public class DistrictRepositoryImpl  implements DistrictRepository{

	@Autowired
	private DistrictMapper districtMapper;
	
	@Override
	public DistrictEntity findByDistrictID(long id) {
		String sql = "select * from district where id = "+id+" " ;
		List<DistrictEntity>  district = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtils.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				district.add(districtMapper.mapRow(rs));
				conn.commit();
			}
			return district.isEmpty() ? null : district.get(0);
		
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
	}

}

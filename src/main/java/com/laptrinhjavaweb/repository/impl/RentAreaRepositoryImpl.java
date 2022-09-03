package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.Mapper.RentAreaMapper;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.entity.RentAreaEntity;
import com.laptrinhjavaweb.utils.ConnectionUtils;

public class RentAreaRepositoryImpl implements RentAreaRepository {

	RentAreaMapper rentareaMapper;
	
	@Override
	public List<RentAreaEntity> findByBuildingId(Long id) {
		String sql = "select * from district where id = "+id+" " ;
		List<RentAreaEntity>  rentAreaEntities = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtils.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				rentAreaEntities.add(rentareaMapper.mapRow(rs));
				conn.commit();
			}
			return rentAreaEntities;
		
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

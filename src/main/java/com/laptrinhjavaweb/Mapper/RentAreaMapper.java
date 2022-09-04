package com.laptrinhjavaweb.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.repository.entity.RentAreaEntity;


@Component
public class RentAreaMapper {

	public RentAreaEntity mapRow(ResultSet rs) {
		try {
			RentAreaEntity rentarea = new RentAreaEntity();
			rentarea.setId(rs.getLong("id"));
			rentarea.setValue(rs.getInt("value"));
			return rentarea;
		} catch (SQLException e) {
			return null;
		}	
	}

}
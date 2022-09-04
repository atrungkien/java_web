package com.laptrinhjavaweb.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.repository.entity.BuildingEntity;


@Component
public class BuildingMapper  {

	public BuildingEntity mapRow(ResultSet rs) {
		try {
			BuildingEntity buildingEntity = new BuildingEntity();
			buildingEntity.setId(rs.getLong("id"));
			buildingEntity.setName(rs.getString("name"));
			buildingEntity.setStreet(rs.getString("street"));
			buildingEntity.setWard(rs.getString("ward"));
			buildingEntity.setDistrictID(rs.getLong("districtid"));
			buildingEntity.setFloorarea(rs.getInt("floorarea"));
			buildingEntity.setRentpricedescription(rs.getInt("rentpricedescription"));
			buildingEntity.setRentArea(rs.getInt("rentprice"));
			buildingEntity.setServiceFee(rs.getString("servicefee"));
			return buildingEntity;
		} catch (SQLException e) {
			return null;
		}	
	}

}

package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	private String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	private String USER = "root";
	private String PASS = "123456789";
	
    @Override
	public List<BuildingEntity> findBuilding(String name, String district, String buildingArea, String street,
			String ward, String numberOfBasement, String buildingTypes, String costRentFrom, String costRentTo,
			String areaRentFrom, String areaRentTo, String staffId) {
		List<BuildingEntity> results = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
	       stmt = conn.createStatement();
	       String sql = "SELECT * FROM building where name like '"+name+"'";
	       sql += " or district like'%"+district+"%'";
	       sql += " or buildingArea like'%"+buildingArea+"%'";
	       sql += " or street like'%"+street+"%'";
	       sql += " or ward like'%"+ward+"%'";
	       sql += " or ward like'%"+buildingTypes+"%'";
	       sql += " or numberOfBasement like'%"+numberOfBasement+"%'";
	       sql += " or costRentFrom like'%"+costRentFrom+"%'";
	       sql += " or costRentTo like'%"+costRentTo+"%'";
	       sql += " or areaRentFrom like'%"+areaRentFrom+"%'";
	       sql += " or areaRentTo like'%"+areaRentTo+"%'";
	       sql += " or staffId like'%"+staffId+"%'";
	       rs = stmt.executeQuery(sql);
	       while(rs.next()) {
	    	   BuildingEntity buildingEntity = new BuildingEntity();
	    	   buildingEntity.setAddress(rs.getString("address"));
	    	   buildingEntity.setName(rs.getString("name"));
	    	   buildingEntity.setManagerPhone(rs.getString("mnagername"));
	    	   buildingEntity.setManagerName(rs.getString("managerphone"));
	    	   buildingEntity.setFloorarea(rs.getString("floorarea"));
	    	   buildingEntity.setCostRent(rs.getInt("costRent"));
	    	   buildingEntity.setServiceCost(rs.getString("servicecost"));
	    	   buildingEntity.setOvertimeCost(rs.getString("overtimecost"));
	    	   buildingEntity.setBuildingTypes(rs.getString("buildingTypes"));
	    	   results.add(buildingEntity);
	       }
	       return results;
     } catch (SQLException e) {
        e.printStackTrace();
     }finally {
    	 try {
    		if(conn != null) {
    			conn.close();
    		}
    		if(stmt != null) {
    			stmt.close();
    		}
    		if(rs != null) {
    			rs.close();
    		}
    		
    	 }catch (SQLException e) {
    		 System.out.println(e.getMessage());
    	 }
     }
		return null;
  }      
}


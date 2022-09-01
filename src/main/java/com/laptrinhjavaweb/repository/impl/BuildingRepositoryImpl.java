package com.laptrinhjavaweb.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;
import com.laptrinhjavaweb.utils.IsNullOrEmtyUtils;

public class BuildingRepositoryImpl extends SimpleJdbcRepository<BuildingEntity> implements BuildingRepository {
	
	public List<BuildingEntity> findBuilding(Map<String, Object> params, List<String> rentTypes) {
		
		StringBuilder sql = new StringBuilder("SELECT name,street,ward,districid,,floorarea,numberofbasement,managerphone,managername FROM building b");
		StringBuilder joinQuery = new StringBuilder("");
		StringBuilder whereQuery = new StringBuilder(" Where 1=1 ");
		buildQueryWithJoin(params, rentTypes, joinQuery, whereQuery);
		buildQueryWithoutJoin(params, whereQuery);
		
		sql.append(joinQuery).append(whereQuery).append(" group by b.id");
		
		return findByCondition(sql.toString());
	}
	
	private StringBuilder buildQueryWithoutJoin(Map<String, Object> params, StringBuilder whereQuery) {
		String name = (String) params.get("name");
		String floorarea = (String) params.get("floorarea");
		String numberofbasement = (String) params.get("numberofbasement");
		String street = (String) params.get("street");
		String managername = (String) params.get("managername");
		String managerphone = (String) params.get("managerphone");
		String ward = (String) params.get("ward");
		
		if (IsNullOrEmtyUtils.IsNullOrEmty(name)) {
			whereQuery.append(" AND b.name like '%" + name + "%' ");
		}
		if (IsNullOrEmtyUtils.IsNullOrEmty(floorarea)) {
			whereQuery.append(" AND b.floorarea = " + floorarea + "");
		}
		if (IsNullOrEmtyUtils.IsNullOrEmty(numberofbasement)) {
			whereQuery.append(" AND b.numberofbasement = " + numberofbasement + "");
		}
		if (IsNullOrEmtyUtils.IsNullOrEmty(street)){
			whereQuery.append(" AND b.street like '%" + street + "%'");
		}
		if (IsNullOrEmtyUtils.IsNullOrEmty(ward)) {
			whereQuery.append(" AND b.ward like '%" + ward + "%'");
		}
		if(IsNullOrEmtyUtils.IsNullOrEmty(managername)) {
			whereQuery.append(" and managername like '%"+managername+"%'");
	    }
	    if(IsNullOrEmtyUtils.IsNullOrEmty(managerphone)) {
	    	whereQuery.append(" and managerphone '%"+managerphone+"%'");
	     }
	    
	    String rentpricefrom = (String) params.get("rentpricefrom");
		String rentpriceTO = (String) params.get("rentpriceTO");
	    
		if (IsNullOrEmtyUtils.IsNullOrEmty(rentpricefrom) || IsNullOrEmtyUtils.IsNullOrEmty(rentpriceTO)) {
			String from = !IsNullOrEmtyUtils.IsNullOrEmty(rentpricefrom) ? "0" : rentpricefrom.toString();
			String to = !IsNullOrEmtyUtils.IsNullOrEmty(rentpriceTO) ? String.valueOf(Integer.MAX_VALUE): rentpriceTO.toString();
			whereQuery.append(" AND  b.rentprice between " + from + " and " + to + " ");
		}
		return whereQuery;
	}


	private StringBuilder buildQueryWithJoin(Map<String, Object> params, List<String> rentTypes, StringBuilder joinQuery,
			StringBuilder whereQuery) {
			joinQuery.append(" inner join district as dt on b.districtid = dt.id");
				String districtTypes = (String) params.get("districtCode");
				if (IsNullOrEmtyUtils.IsNullOrEmty(districtTypes)) {
					whereQuery.append(" AND d.code like '%"+districtTypes+"%'");
				}
			
		String rentareafrom = (String) params.get("rentareafrom");
		String rentareaTo = (String) params.get("rentareaTo");
		
		if (IsNullOrEmtyUtils.IsNullOrEmty(rentareafrom) || IsNullOrEmtyUtils.IsNullOrEmty(rentareaTo)) {
			String from = !IsNullOrEmtyUtils.IsNullOrEmty(rentareafrom) ? "0" : rentareafrom.toString();
			String to = !IsNullOrEmtyUtils.IsNullOrEmty(rentareaTo) ? String.valueOf(Integer.MAX_VALUE) : rentareaTo.toString();
			joinQuery.append(" JOIN rentarea r ON r.buildingid = b.id");
			whereQuery.append(" AND  r.value between " + from + " and " + to + " ");

		}
		
		List<String> rentType = rentTypes;
		if (IsNullOrEmtyUtils.IsNullOrEmty(rentType)) {
			List<String> buildingTypes = new ArrayList<>();
			for (String type : rentType) {
				buildingTypes.add("'"+type+"'");
			}
			joinQuery.append(" INNER JOIN buildingrenttype AS br ON b.id = br.buildingid " + " INNER JOIN renttype AS rt ON rt.id = br.renttypeid ");
			whereQuery.append(" AND rt.code IN (").append(String.join(",", buildingTypes)).append(")");
		}
		return joinQuery;
	}

}
	
	


package com.laptrinhjavaweb.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;

import com.laptrinhjavaweb.anotation.Column;
import com.laptrinhjavaweb.model.dto.BuildingDto;
import com.laptrinhjavaweb.model.input.BuildingSeachInput;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;

public class ResultsetMapper<T> {
	   public List<T> mapRow(ResultSet rs , Class<T> tClass){
		List<T> results = new ArrayList<>();
		try {
			Field[]  fields = tClass.getDeclaredFields();
	         ResultSetMetaData resultSetMetaData = rs.getMetaData();
		         while(rs.next()){
		        	 T object = tClass.newInstance();
		        	 for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
						String columnName = resultSetMetaData.getColumnName(i + 1);
						Object columnValue = rs.getObject(i+1);
						for (Field field : fields) {
							if (field.isAnnotationPresent(Column.class)) {
								Column column = field.getAnnotation(Column.class);
								if (column.name().equals(columnName) && columnName != null) {
									BeanUtils.setProperty(object, field.getName(), columnValue);
									break;
								}
							}
						}
						Class<?> parentClass = tClass.getSuperclass();
						while (parentClass != null) {
							for (Field field : parentClass.getDeclaredFields()) {
								if (field.isAnnotationPresent(Column.class)) {
									Column column = field.getAnnotation(Column.class);
									if (column.name().equals(columnName) && columnName != null) {
										BeanUtils.setProperty(object, field.getName(), columnValue);		
										break;
									}
								}
							}
							parentClass = parentClass.getSuperclass();
						}
					}
		        	 results.add(object);
		         }
		         return results;
		} catch (SQLException | IllegalAccessException |InstantiationException | InvocationTargetException e) {
			
		}
		return null;
	}
	
}

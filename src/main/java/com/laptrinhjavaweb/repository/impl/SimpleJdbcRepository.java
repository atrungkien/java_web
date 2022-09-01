package com.laptrinhjavaweb.repository.impl;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.anotation.Column;
import com.laptrinhjavaweb.anotation.Entity;
import com.laptrinhjavaweb.anotation.Table;
import com.laptrinhjavaweb.mapper.ResultsetMapper;
import com.laptrinhjavaweb.repository.JdbcRepository;
import com.laptrinhjavaweb.utils.ConnectionUtils;

public class SimpleJdbcRepository<T> implements JdbcRepository<T> {
	
	private Class<T> tClass;
	public SimpleJdbcRepository() {
		tClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	
	@Override
	public List<T> findAll() {
			Connection conn = null;
	        Statement stmt = null;
	        ResultSet rs = null;
			
			 try {
				 conn = ConnectionUtils.getConnection();
		         stmt = conn.createStatement();
		         String tableName = null;
		         if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
					Table table = tClass.getAnnotation(Table.class);
					tableName = table.name();
				}
		         String sql = "SELECT * FROM "+tableName+" ";
		         rs = stmt.executeQuery(sql);
		         ResultsetMapper<T> resultsetMapper = new ResultsetMapper<>();
		         return resultsetMapper.mapRow(rs, tClass);
			      } catch (SQLException e) {
			         System.out.println(e.getMessage());
			      } finally {
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
			    	  }catch (Exception e) {
						System.out.println(e.getMessage());
					}
			      }
			return null;
	}


	@Override
	public void insert(Object object) {
		Connection conn = null;
        PreparedStatement stmt = null;
        try {
        	 conn = ConnectionUtils.getConnection();
        	 StringBuilder sql = creatSQLInsert();
	         stmt = conn.prepareStatement(sql.toString());
	         
	        Class<?> zClass = object.getClass();
	        int parameterIndex = 1;
	        Field[] files = zClass.getDeclaredFields();
	        for (Field field : files) {
	        	field.setAccessible(true);
				stmt.setObject(parameterIndex, field.get(object));
				parameterIndex++;
			}
	        Class<?> parentClass = zClass.getSuperclass();
	        int intdexParent = files.length + 1;
	        while (parentClass != null) {
	        	for (Field field : parentClass.getDeclaredFields()) {
		        	field.setAccessible(true);
					stmt.setObject(intdexParent, field.get(object));
					intdexParent++;
				}
	        	parentClass = parentClass.getSuperclass();
			}
	         stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private StringBuilder creatSQLInsert() {
		String tableName = null;
		if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
			Table table = tClass.getAnnotation(Table.class);
			tableName = table.name();
		
		}
		StringBuilder fields = new StringBuilder("");
		StringBuilder values = new StringBuilder("");
		for (Field filed: tClass.getDeclaredFields()) {
			if (fields.length() > 1) {
				fields.append(",");
				values.append(",");
			}
			if (filed.isAnnotationPresent(Column.class)) {
				Column column = filed.getAnnotation(Column.class);
				fields.append(column.name());
				values.append("?");
			}
		}
		Class<?> parentClass = tClass.getSuperclass();
		while (parentClass != null) {
			for (Field filed: parentClass.getDeclaredFields()) {
				if (fields.length() > 1) {
					fields.append(",");
					values.append(",");
				}
				if (filed.isAnnotationPresent(Column.class)) {
					Column column = filed.getAnnotation(Column.class);
					fields.append(column.name());
					values.append("?");
				}
			}
			parentClass = parentClass.getSuperclass();
		}
		StringBuilder sql = new StringBuilder("insert into "+tableName+" ("+fields+") values ("+values+")");
		return sql;
	}


	@Override
	public T findById(Long staffid) {
		List<T> result = new ArrayList<>();
		Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
		
		 try {
			 conn = ConnectionUtils.getConnection();
	         stmt = conn.createStatement();
	         String tableName = null;
	         if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
				Table table = tClass.getAnnotation(Table.class);
				tableName = table.name();
			}
	         String sql = "SELECT * FROM "+tableName+" where id = " + staffid;
	         rs = stmt.executeQuery(sql);
	         ResultsetMapper<T> resultsetMapper = new ResultsetMapper<>();
	         return resultsetMapper.mapRow(rs, tClass).size() > 0 ? result.get(0) : null;
		      } catch (SQLException e) {
		         System.out.println(e.getMessage());
		      } finally {
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
		    	  }catch (Exception e) {
					System.out.println(e.getMessage());
				}
		      }
		return null;
	}


	@Override
	public List<T> findByCondition(String sql) {
		Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
		
		 try {
			 conn = ConnectionUtils.getConnection();
	         stmt = conn.createStatement();
	         rs = stmt.executeQuery(sql.toString());
	         ResultsetMapper<T> resultsetMapper = new ResultsetMapper<>();
	         return resultsetMapper.mapRow(rs, tClass);
		      } catch (SQLException e) {
		         System.out.println(e.getMessage());
		      } finally {
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
		    	  }catch (Exception e) {
					System.out.println(e.getMessage());
				}
		      }
		return null;
	}


	@Override
	public void update(Object object) {
		Connection conn = null;
        PreparedStatement stmt = null;
        try {
        	 conn = ConnectionUtils.getConnection();
        	 StringBuilder sql = creatSQLUpdate();
	         stmt = conn.prepareStatement(sql.toString());
	         
	        Class<?> zClass = object.getClass();
	        int parameterIndex = 1;
	        Field[] files = zClass.getDeclaredFields();
	        for (Field field : files) {
	        	field.setAccessible(true);
				stmt.setObject(parameterIndex, field.get(object));
				parameterIndex++;
			}
	        String id = null;
	        Class<?> parentClass = zClass.getSuperclass();
	        int intdexParent = files.length + 1;
	        while (parentClass != null) {
	        	for (Field field : parentClass.getDeclaredFields()) {
		        	field.setAccessible(true);
		        	if (field.get(object) != id) {
		        		stmt.setObject(intdexParent, field.get(object));
						intdexParent++;
					}
					if (field.get(object) == id) {
						stmt.setObject(intdexParent, field.get(id));
					}
					
				}
	        	parentClass = parentClass.getSuperclass();
			}
	         stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private StringBuilder creatSQLUpdate() {
		String tableName = null;
		if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
			Table table = tClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuilder fieldAndParams = new StringBuilder("");
		for (Field filed: tClass.getDeclaredFields()) {
			if (fieldAndParams.length() > 1) {
				fieldAndParams.append(",");
			}
			if (filed.isAnnotationPresent(Column.class)) {
				Column column = filed.getAnnotation(Column.class);
				fieldAndParams.append(column.name() + " = ? ");
			}
		}
		Class<?> parentClass = tClass.getSuperclass();
		while (parentClass != null) {
			for (Field filed: parentClass.getDeclaredFields()) {
				if (fieldAndParams.length() > 1) {
					fieldAndParams.append(",");
				}
				if (filed.isAnnotationPresent(Column.class)) {
					Column column = filed.getAnnotation(Column.class);
					fieldAndParams.append(column.name() + " = ? ");
					
				}
			}
			parentClass = parentClass.getSuperclass();
		}
		StringBuilder sql = new StringBuilder("UPDATE  "+tableName+" set "+fieldAndParams+" where id = ?" );
		return sql;
	}


	@Override
	public void delete(Object object) {
		Connection conn = null;
        PreparedStatement stmt = null;
        try {
        	 conn = ConnectionUtils.getConnection();
        	 StringBuilder sql = creatSQLDelate();
	         stmt = conn.prepareStatement(sql.toString());
	         
	        Class<?> zClass = object.getClass();
	        int parameterIndex = 1;
	        Field[] files = zClass.getDeclaredFields();
	        for (Field field : files) {
	        	field.setAccessible(true);
				stmt.setObject(parameterIndex, field.get(object));
				parameterIndex++;
			}
	        String id = null;
	        Class<?> parentClass = zClass.getSuperclass();
	        int intdexParent = files.length + 1;
	        while (parentClass != null) {
	        	for (Field field : parentClass.getDeclaredFields()) {
		        		field.setAccessible(true);
		        		stmt.setObject(intdexParent, field.get(object));
						intdexParent++;

					
				}
	        	parentClass = parentClass.getSuperclass();
			}
	         stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private StringBuilder creatSQLDelate() {
		String tableName = null;
		if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
			Table table = tClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuilder fieldAndParams = new StringBuilder("");
		for (Field filed: tClass.getDeclaredFields()) {
			if (fieldAndParams.length() > 1) {
				fieldAndParams.append(",");
			}
			if (filed.isAnnotationPresent(Column.class)) {
				Column column = filed.getAnnotation(Column.class);
				fieldAndParams.append(column.name() + " = ? ");
			}
		}
		Class<?> parentClass = tClass.getSuperclass();
		while (parentClass != null) {
			for (Field filed: parentClass.getDeclaredFields()) {
				if (fieldAndParams.length() > 1) {
					fieldAndParams.append(",");
				}
				if (filed.isAnnotationPresent(Column.class)) {
					Column column = filed.getAnnotation(Column.class);
					fieldAndParams.append(column.name() + " = ? ");
					
				}
			}
			parentClass = parentClass.getSuperclass();
		}
		StringBuilder sql = new StringBuilder("delete  from "+tableName+" where "+fieldAndParams+"");
		return sql;
		}
}

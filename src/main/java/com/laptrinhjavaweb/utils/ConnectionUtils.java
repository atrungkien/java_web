package com.laptrinhjavaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Repository;

@Repository
public class ConnectionUtils {
	
	private	static String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	private static String USER = "root";
	private static String PASS = "123456789";
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			return connection;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static boolean IsNullOrEmty(String value) {
		if (value != null && value != "") {
			return true;
		}
		return false;
	}
}

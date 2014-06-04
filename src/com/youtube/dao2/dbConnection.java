package com.youtube.dao2;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {

	private static Connection conn = null;
	
	
	public static Connection connect() throws Exception {
		
		if (conn != null && !conn.isClosed()) {
			return conn;
		}
		
		try {
			Class.forName("org.postgresql.Driver");
			
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bluetoothSurvey", "zexin", "1234");
			
			if (conn != null)
				System.out.println("Connected");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	
}
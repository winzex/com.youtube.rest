package com.youtube.dao;  //data access object

import javax.naming.*;
import javax.sql.*;

public class ami {

	private static DataSource database = null;
	private static Context context = null;
	
	public static DataSource btConn() throws Exception {
		
		if (database != null) {
			return database;
		}
		
		try {
			if (context == null) {
				context = new InitialContext();
			}
			
			database = (DataSource) context.lookup("jdbc:postgresql://localhost:5432/bluetoothSurvey");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return database;
	}
	
	
}

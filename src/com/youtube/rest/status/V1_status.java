package com.youtube.rest.status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.youtube.dao2.dbConnection;

@Path("/v1/status/")
public class V1_status {
	
	private static final String api_version = "00.01.00";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p> ";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version:</p>" + api_version;
	}	
	
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {

		Connection conn = null;
		PreparedStatement query = null;
		
		String myString = "";
		String returnString = "";

		try {
			
			
			
			//sql statement
//			String sql = "create table person(id integer, name varchar(30) )";
			
			
			//working
			conn = dbConnection.connect();
			
			java.sql.Statement st;
			st = conn.createStatement();	
			
			String sql = "insert into person (id, name) values (189, 'zexin2')";			
			st.execute(sql);

			
			returnString = "<p>Database Status</p> " +
					"<p>Database Date/Time return: " + myString + "</p>";

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null) conn.close();
			
		}

		
		
		return returnString; 
	}


}

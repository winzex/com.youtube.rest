package com.youtube.rest.inventory;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao2.dbConnection;
import com.youtube.util.ToJSON;

//import com.youtube.dao.Schema308tube;

/**
 * This class is used to manage computer parts inventory.
 * 
 * We have improved upon inventory resource in episode 5 of this tutorial series, 
 * at this point v1/inventory should be deprecated and a date should be set 
 * for this java class to be deleted.
 * 
 * @author 308tube
 */
@Path("/v1/inventory") //removed * wildcard to make this more compatible with tomcat
public class V1_inventory {

	/**
	 * This method will return all computer parts that are listed
	 * in PC_PARTS table.
	 * 
	 * Note: This is a good method for a tutorial but probably should never
	 * has a method that returns everything from a database.  There should be
	 * built in limits.
	 * 
	 * Pre-episode 6, updated because Oracle308tube.java no longer accessible.
	 * 
	 * @return - JSON array string
	 * @throws Exception
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllPcParts() throws Exception {

		String returnString = null;
//		Response rb = null;	
		JSONArray json = new JSONArray();
		Connection conn = null;
		Response rb = null;

		try {
			
			conn = dbConnection.connect();
			
			java.sql.Statement st;
			st = conn.createStatement();	
			
			String sql = "select * from person";			
			st.execute(sql);
		
			ResultSet rs = st.getResultSet();
			
			ToJSON converter = new ToJSON();
			json = converter.toJSONArray(rs);
			
			returnString = json.toString();
			rb = Response.ok(returnString).build();

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return rb;
	}

}
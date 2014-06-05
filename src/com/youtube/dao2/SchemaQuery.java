package com.youtube.dao2;

import java.sql.*;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.util.ToJSON;

/**
 * This java class will hold all the sql queries from episode 5 and onward.
 * V1_inventory.java and V1_status.java will not use this class for its sql code
 * since they were created before episode 5.
 * 
 * Having all sql/database code in one package makes it easier to maintain and audit
 * but increase complexity.
 * 
 * Note: we also used the extends Oracle308tube on this java class to inherit all
 * the methods in Oracle308tube.java
 * 
 * @author 308tube
 */
public class SchemaQuery extends dbConnection {
	
	public int insertIntoPC_PARTS(String ID, 
			String NAME) 
		throws Exception {

		PreparedStatement query = null;
		Connection conn = null;
		
		try {
		/*
			* If this was a real application, you should do data validation here
			* before starting to insert data into the database.
			* 
			* Important: The primary key on PC_PARTS table will auto increment.
			* 		That means the PC_PARTS_PK column does not need to be apart of the 
			* 		SQL insert query below.
			*/
			conn = connect();
			query = conn.prepareStatement("insert into person " +
			"(id, name) " +
			"VALUES ( ?, ?) ");
			
			
			int avilInt = Integer.parseInt(ID);
			query.setInt(1, avilInt);
			query.setString(2, NAME);
						
			query.executeUpdate(); //note the new command for insert statement
			
		} catch(Exception e) {
			e.printStackTrace();
			return 500; //if a error occurs, return a 500
		}
		finally {
			if (conn != null) conn.close();
		}
			
		return 200;
	}


	/**
	 * This method will search for a specific brand from the PC_PARTS table.
	 * By using prepareStatement and the ?, we are protecting against sql injection
	 * 
	 * Never add parameter straight into the prepareStatement
	 * 
	 * @param brand - product brand
	 * @return - json array of the results from the database
	 * @throws Exception
	 */
	public JSONArray queryReturnBrandParts(String brand) throws Exception {

		PreparedStatement query = null;
		Connection conn = null;

		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();

		try {
			conn = connect();
			query = conn.prepareStatement("select id, name " +
											"from person " +
											"where UPPER(name) = ? ");

			query.setString(1, brand.toUpperCase()); //protect against sql injection
			ResultSet rs = query.executeQuery();

			json = converter.toJSONArray(rs);
			query.close(); //close connection
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}

		return json;
	}

	/**
	 * This method will search for the specific brand and the brands item number in
	 * the PC_PARTS table.
	 * 
	 * By using prepareStatement and the ?, we are protecting against sql injection
	 * 
	 * Never add parameter straight into the prepareStatement
	 * 
	 * @param brand - product brand
	 * @param item_number - product item number
	 * @return - json array of the results from the database
	 * @throws Exception
	 */
	public JSONArray queryReturnBrandItemNumber(String brand, int item_number) throws Exception {

		PreparedStatement query = null;
		Connection conn = null;

		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();

		try {
			conn = connect();
			query = conn.prepareStatement("select id, name " +
											"from person " +
											"where UPPER(name) = ? " +
											"and id = ?");

			/*
			 * protect against sql injection
			 * when you have more than one ?, it will go in chronological
			 * order.
			 */
			query.setString(1, brand.toUpperCase()); //first ?
			query.setInt(2, item_number); //second ?
			ResultSet rs = query.executeQuery();

			json = converter.toJSONArray(rs);
			query.close(); //close connection
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}

		return json;
	}
}
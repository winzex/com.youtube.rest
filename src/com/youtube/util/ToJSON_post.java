package com.youtube.util;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.sql.ResultSet;

//import org.owasp.esapi.ESAPI;

/**
 * This utility will convert a database data into JSON format.
 * Note:  this java class requires the ESAPI 1.4.4 jar file
 * ESAPI is used to encode data
 * 
 * @author 308tube
 */
public class ToJSON_post {

	/**
	 * This will convert database records into a JSON Array
	 * Simply pass in a ResultSet from a database connection and it
	 * loop return a JSON array.
	 * 
	 * It important to check to make sure that all DataType that are
	 * being used is properly encoding.
	 * 
	 * varchar is currently the only dataType that is being encode by ESAPI
	 * 
	 * @param rs - database ResultSet
	 * @return - JSON array
	 * @throws Exception
	 */
	public JSONArray toJSONArray(String input) throws Exception {

        JSONArray json = new JSONArray(); //JSON array that will be returned
        String temp = null;

        try {
        	 
        	 String[] pairs = input.split("&");

        	 //loop through the ResultSet
        	 JSONObject obj = new JSONObject();
             for (int i = 0; i < pairs.length; i++) {
            	 String[] param = pairs[i].split("=");
            	 obj.put(param[0], param[1]);
            	 
             }
             json.put(obj);
            	 

        } catch (Exception e) {
            e.printStackTrace();
        }

        return json; //return JSON array
	}
}
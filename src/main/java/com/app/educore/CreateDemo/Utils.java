package com.app.educore.CreateDemo;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Utils {
	

	public static JSONObject postjson(String uri, Map<String, String> requestProperty, JSONObject json) throws Exception {
		
		System.out.println("URl			" + uri);
		System.out.println("Req Headers		" + requestProperty);
		System.out.println("JSON			" + json);
		
		
    	URL url = new URL(uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("content-type", "application/json");
		
		if (requestProperty != null) {
			for(Map.Entry<String, String> entry: requestProperty.entrySet()) {
				if (entry.getValue().equals("null")) {
					connection.setRequestProperty(entry.getKey(), null);	
				} else {
					connection.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}			
		}
		
		if (json != null) {
			OutputStreamWriter wr= new OutputStreamWriter(connection.getOutputStream());
			wr.write(json.toString());
			wr.close();	
		}
		
		InputStream in = null;
		JSONObject resultjson = null;
		try {
			if (connection.getResponseCode() != 204) {
				
				in = new BufferedInputStream(connection.getInputStream());
				
		        String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
		        
		        resultjson = new JSONObject(result);
		        
		        connection.disconnect();
		        in.close();
		        
				System.out.println("Output			" + resultjson);
				System.out.println();
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Code " + connection.getResponseCode());
			System.out.println(connection.getResponseMessage());
			System.exit(0);
		}
		
        return resultjson;
		
	}
	
	
	public static JSONObject createjson(String sheetName, int rowNum, int colstart, int colend) throws Exception {
		
		JSONObject json = new JSONObject();
		/*
		for (int j=colstart;j<=colend;j++) {
			if (Excel.getdata(sheetName, rowNum, j).equals("null")) {
				json.put(Excel.getdata(sheetName, 1, j), JSONObject.NULL);	
			} else {
				json.put(Excel.getdata(sheetName, 1, j), Excel.getdata(sheetName, rowNum, j));
			}
		}
		*/
		
		
		for (int j=colstart;j<=colend;j++) {
		
			String colName = Excel.getdata(sheetName, 1, j);
			String colValue = Excel.getdata(sheetName, rowNum, j);
			
			String subjsoncontainername, subjsonname;
			
			if (colName.indexOf("__") > 0) {
				String[] str = colName.split("__");
				subjsoncontainername = str[0];
				subjsonname = str[1];
				
				if (colValue.equalsIgnoreCase("systemproperty")) {
					colValue = System.getProperty(subjsoncontainername);
				} else if (colValue.equalsIgnoreCase("systemproperty2")) {
					colValue = System.getProperty(subjsonname);
				}
				
			} else {
				subjsonname = colName;
				
				if (colValue.equalsIgnoreCase("systemproperty")) {
					colValue = System.getProperty(subjsonname);
				}
				
			}
			
			
			if (Excel.getdata(sheetName, rowNum, j).equals("null")) {
				json.put(subjsonname, JSONObject.NULL);	
			} else {
				json.put(subjsonname, colValue);
			}
		}
		
		return json;
	}
	
	
	
	
	public static JSONObject createjson1(String sheetName, int rowNum, int colstart, int colend) throws Exception {
		
		JSONObject json = new JSONObject();
		/*
		String colName = Excel.getdata(sheetName, 1, 1);
		if (colName.indexOf("--") > 0) {
			String[] str = colName.split("--");
			String subjsoncontainername = str[0];
			String subjsonname = str[1];
		}
			
		*/
		
		
		for (int j=colstart;j<=colend;j++) {
		
			String colName = Excel.getdata(sheetName, 1, j);
			String subjsoncontainername, subjsonname;
			if (colName.indexOf("__") > 0) {
				String[] str = colName.split("__");
				subjsoncontainername = str[0];
				subjsonname = str[1];
			} else {
				subjsonname = colName;
			}
			if (Excel.getdata(sheetName, rowNum, j).equals("null")) {
				json.put(subjsonname, JSONObject.NULL);	
			} else {
				json.put(subjsonname, Excel.getdata(sheetName, rowNum, j));
			}
		}
		
		return json;
	}
	
	
	
	public static String searchuser(String name) throws Exception {
		
		JSONObject jsoncriteria = new JSONObject();
		JSONObject jsonsort = new JSONObject();
		JSONObject parent = new JSONObject();
		
		jsoncriteria.put("key", "fullName");
		jsoncriteria.put("value", name);
		jsoncriteria.put("operator", "=");
		
		jsonsort.put("property", "updatedOn");
		
		JSONArray ja = new JSONArray();
		ja.put(jsoncriteria);
		
		JSONArray ja2 = new JSONArray();
		ja2.put(jsonsort);
		
		parent.put("criterias", ja);
		parent.put("sorts", ja2);
		parent.put("size", "1");
		
		String url = "http://demo.educoresystems.com/api-v1.0/users/search?RESPONSE_VIEW=User.NameId";
		JSONObject user,o = null;
		try {
			user = Utils.postjson(url, null, parent);
	        o = (JSONObject) user.getJSONArray("contents").get(0);
		} catch(Exception e) {
			System.out.println(parent);
		}
		
		//System.out.println(o.get("id"));
        return o.get("id").toString(); 
	}

}

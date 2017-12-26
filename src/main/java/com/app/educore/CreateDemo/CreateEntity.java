package com.app.educore.CreateDemo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class CreateEntity {
	
	
	Map<String, String> reqProp = new HashMap<String, String>();
	
	public String createschool(int rownNum) throws Exception {
		
		String sheetName = "School";
		//Excel excel = new Excel(sheetName);
		
		String url = Excel.getdata(sheetName, rownNum, 1);
		
		reqProp.put("USER_ID", System.getProperty("userid"));
		
		JSONObject schoolinfo = Utils.createjson(sheetName, rownNum, 2, 6);
		JSONObject contactdetails = Utils.createjson(sheetName, rownNum, 7, 12);
		schoolinfo.put("contactDetailsId", contactdetails);
		
        JSONObject jsonresult = Utils.postjson(url, reqProp, schoolinfo);
        
        return jsonresult.get("id").toString();
        
	}
		
	public String createbranch(int rownNum) throws Exception {
		
    	String sheetName = "Branch";
		
    	String url = Excel.getdata(sheetName, rownNum, 1);
		
		reqProp.put("USER_ID", System.getProperty("userid"));
		reqProp.put("school_id", System.getProperty("schoolid"));
		
		JSONObject branchinfo = Utils.createjson(sheetName, rownNum, 2, 7);
		JSONObject contactdetails = Utils.createjson(sheetName, rownNum, 8, 13);
		branchinfo.put("contactDetailsId", contactdetails);
		
        JSONObject resultjson = Utils.postjson(url, reqProp, branchinfo);
        
        return resultjson.get("id").toString();
        
	}
	

	
	public String createdepartment(int rownNum) throws Exception, Exception {

		String sheetName = "Department";
		
    	String url = Excel.getdata(sheetName, rownNum, 1);
		
    	reqProp.put("BRANCH_ID", System.getProperty("branchid"));
		reqProp.put("SCHOOL_ID", System.getProperty("schoolid"));
		reqProp.put("USER_ID", System.getProperty("userid"));
		reqProp.put("SESSION_YEAR_ID", System.getProperty("sessionyearid"));
		
		JSONObject deptinfo = Utils.createjson(sheetName, rownNum, 2, 3);
		
        JSONObject jsonresult = Utils.postjson(url, reqProp, deptinfo);
        
        return jsonresult.get("id").toString();
        
	}
	
	

	public String createdesignation(int rownNum) throws Exception, Exception {
		
		String sheetName = "Designation";
		
    	String url = Excel.getdata(sheetName, rownNum, 1);
		
    	reqProp.put("BRANCH_ID", System.getProperty("branchid"));
		reqProp.put("SCHOOL_ID", System.getProperty("schoolid"));
		reqProp.put("USER_ID", System.getProperty("userid"));
		reqProp.put("SESSION_YEAR_ID", System.getProperty("sessionyearid"));
		
		JSONObject desginfo = Utils.createjson(sheetName, rownNum, 2, 7);
		
        JSONObject jsonresult = Utils.postjson(url, reqProp, desginfo);
        
        return jsonresult.get("id").toString();
        
	}
	
	
	public String createsessionyear(int rownNum) throws Exception {
		
    	String sheetName = "Session";
    	
    	String url = Excel.getdata(sheetName, rownNum, 1);
		
		reqProp.put("USER_ID", System.getProperty("userid"));
		reqProp.put("school_id", System.getProperty("schoolid"));
		reqProp.put("BRANCH_ID", System.getProperty("branchid"));
		
		JSONObject sessioninfo = Utils.createjson(sheetName, rownNum, 2, 5);
		
        JSONObject resultjson = Utils.postjson(url, reqProp, sessioninfo);
        
        return resultjson.get("id").toString();
        
	}
	
	
	public void startsessionyear() throws Exception {
		
		String url = "http://demo.educoresystems.com/api-v1.0//sessionYears//" + System.getProperty("sessionyearid") + "/start";
		
		reqProp.put("USER_ID", System.getProperty("userid"));
		reqProp.put("school_id", System.getProperty("schoolid"));
		reqProp.put("BRANCH_ID", System.getProperty("branchid"));
		
		Utils.postjson(url, reqProp, null);
	}

	
	public String createstaff(int rownNum) throws Exception {
		
    	String sheetName = "Staff";
    	
    	String url = Excel.getdata(sheetName, rownNum, 1);
		
		reqProp.put("USER_ID", System.getProperty("userid"));
		reqProp.put("school_id", System.getProperty("schoolid"));
		reqProp.put("BRANCH_ID", System.getProperty("branchid"));
		reqProp.put("SESSION_YEAR_ID", System.getProperty("sessionyearid"));
		
		JSONObject staffinfo = Utils.createjson(sheetName, rownNum, 2, 25);
		JSONObject desginfo = Utils.createjson(sheetName, rownNum, 26, 26);
		JSONObject deptinfo = Utils.createjson(sheetName, rownNum, 27, 27);
		staffinfo.put("departmentId", deptinfo);
		staffinfo.put("designationId", desginfo);
		staffinfo.put("roles", new JSONArray());
		staffinfo.put("subjects", new JSONArray());
		
        JSONObject resultjson = Utils.postjson(url, reqProp, staffinfo);
        
        return resultjson.get("id").toString();
        
	}

	
	
	public String createstudent(int rownNum) throws Exception {
		
    	String sheetName = "Student";
    	
    	String url = Excel.getdata(sheetName, rownNum, 1);
		
		reqProp.put("USER_ID", System.getProperty("userid"));
		reqProp.put("school_id", System.getProperty("schoolid"));
		reqProp.put("BRANCH_ID", System.getProperty("branchid"));
		reqProp.put("SESSION_YEAR_ID", System.getProperty("sessionyearid"));
		
		JSONObject studentinfo = Utils.createjson(sheetName, rownNum, 2, 3);
		JSONObject parentinfo = Utils.createjson(sheetName, rownNum, 4, 5);
		JSONObject guardianinfo = Utils.createjson(sheetName, rownNum, 6, 9);
		
		//making blank json
		JSONObject guardiancontactdetailsinfo = Utils.createjson(sheetName, rownNum, 2, 1);
		
		//making blank json
		JSONObject contactdetailsinfo = Utils.createjson(sheetName, rownNum, 2, 1);
		
		guardianinfo.put("contactDetailId", guardiancontactdetailsinfo);
		studentinfo.put("parentId", parentinfo);
		studentinfo.put("guardianId", guardianinfo);
		studentinfo.put("contactDetailId", contactdetailsinfo);
		
        JSONObject resultjson = Utils.postjson(url, reqProp, studentinfo);
        
        return resultjson.get("id").toString();
        
	}


	public String createclass(int rownNum) throws Exception {
		
    	String sheetName = "Class";
    	
    	String url = Excel.getdata(sheetName, rownNum, 1);
		
		reqProp.put("USER_ID", System.getProperty("userid"));
		reqProp.put("school_id", System.getProperty("schoolid"));
		reqProp.put("BRANCH_ID", System.getProperty("branchid"));
		reqProp.put("SESSION_YEAR_ID", System.getProperty("sessionyearid"));
		
		JSONObject classinfo = Utils.createjson(sheetName, rownNum, 2, 8);
		
        JSONObject resultjson = Utils.postjson(url, reqProp, classinfo);
        
        return resultjson.get("id").toString();
        
	}



	public String createsection(int rownNum) throws Exception {
		
    	String sheetName = "Section";
    	
    	String url = Excel.getdata(sheetName, rownNum, 1);
		
		reqProp.put("USER_ID", System.getProperty("userid"));
		reqProp.put("school_id", System.getProperty("schoolid"));
		reqProp.put("BRANCH_ID", System.getProperty("branchid"));
		reqProp.put("SESSION_YEAR_ID", System.getProperty("sessionyearid"));
		
		JSONObject sectioninfo = Utils.createjson(sheetName, rownNum, 2, 7);
		JSONObject courseinfo = Utils.createjson(sheetName, rownNum, 8, 8);
		sectioninfo.put("courseId", courseinfo);
		
        JSONObject resultjson = Utils.postjson(url, reqProp, sectioninfo);
        
        return resultjson.get("id").toString();
        
	}
	
	
	public String createadmission(int rownNum) throws Exception {
		
    	String sheetName = "Admission";
    	
    	String url = Excel.getdata(sheetName, rownNum, 1);
		
		reqProp.put("USER_ID", System.getProperty("userid"));
		reqProp.put("school_id", System.getProperty("schoolid"));
		reqProp.put("BRANCH_ID", System.getProperty("branchid"));
		reqProp.put("SESSION_YEAR_ID", System.getProperty("sessionyearid"));
		
		JSONObject admissioninfo = Utils.createjson(sheetName, rownNum, 2, 5);
		JSONObject studentidinfo = Utils.createjson(sheetName, rownNum, 6, 6);
		
		JSONObject otherinfo = Utils.createjson(sheetName, rownNum, 7, 10);
		JSONArray registrationsinfo1 = new JSONArray();
		JSONArray electivesubject = new JSONArray();
		otherinfo.put("registrations", registrationsinfo1);
		otherinfo.put("electiveSubjects", electivesubject);

		JSONArray registerationsarray = new JSONArray();
		registerationsarray.put(otherinfo);
		
		admissioninfo.put("studentId", studentidinfo);
		admissioninfo.put("registrations", registerationsarray);
		
        JSONObject resultjson = Utils.postjson(url, reqProp, admissioninfo);
        
        return resultjson.get("id").toString();
        
	}

}

package com.app.educore.CreateDemo;

/**
 * Hello world!
 *
*/
public class App 
{
	
    public static void main( String[] args ) throws Exception {
    	
    	Excel.loadData();
    	
    	System.setProperty("userid", "2");
    	
    	String schoolid = new CreateEntity().createschool(2);
    	
		System.setProperty("schoolid", schoolid);
		
    	String userid = Utils.searchuser(Excel.getdata("School", 2, 4));
    	
        System.setProperty("userid", userid);
        
        String branchid = new CreateEntity().createbranch(2);
    	
        System.setProperty("branchid", branchid);
        
    	userid = Utils.searchuser(Excel.getdata("Branch", 2, 5));
    	
        System.setProperty("userid", userid);
        
        String sessionyearid = new CreateEntity().createsessionyear(2);
    	
        System.setProperty("sessionyearid", sessionyearid);
        
        new CreateEntity().startsessionyear();
        
        String deptid = new CreateEntity().createdepartment(2);
        
        System.setProperty("departmentId", deptid);
        
        String desgid = new CreateEntity().createdesignation(2);
        
        System.setProperty("designationId", desgid);
        
        String staffid = new CreateEntity().createstaff(2);
        
        System.setProperty("classTeacherId", staffid);
        
        String classid = new CreateEntity().createclass(2);
        
        System.setProperty("courseId", classid);
        
        String sectionid = new CreateEntity().createsection(2);
        
        System.setProperty("batchId", sectionid);
        
        for (int row =2; row<=Excel.getrowcount("Student"); row++) {
        	
            String studentid = new CreateEntity().createstudent(row);
            
            System.setProperty("studentId", studentid);
            
            String admissionid = new CreateEntity().createadmission(row);
            
            System.setProperty("admissionid", admissionid);
         	
        }
           
    }
    
}

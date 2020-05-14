package com.mycompany.interview;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

	public void addEmployee(String id, String first, String last,String role, String lead,String leadName) {
		
		Connection myCon = null;
		PreparedStatement stm= null;
		
		try {
			
			String url = "jdbc:mysql://localhost:3306/ssolution";
		    myCon = 	DriverManager.getConnection(url, "root","root1234");
		    stm = myCon.prepareStatement("REPLACE into employee values(?,?,?,?,?,?)");
		    stm.setString(1, id);
		    stm.setString(2, first);
		    stm.setString(3, last);
		    stm.setString(4, lead);
		    stm.setString(5, role);
		    stm.setString(6, leadName);
		    stm.executeQuery();
		    System.out.println("Emplpoyee Saved Succesfully");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Unuccesful Connection");
		}
	}
	
//Method to get the manager information from db
	public static List<String> manInfo() {
		
		Connection myCon = null;
		PreparedStatement stm= null;
		ResultSet rs;
		
		try {
			String url = "jdbc:mysql://localhost:3306/ssolution";
		    myCon = 	DriverManager.getConnection(url, "root","root1234");
		    stm = myCon.prepareStatement("select * from employee where employee.supervisor_id = 'yes'");
			rs = stm.executeQuery();
			List<String> name = new ArrayList<String>();
			while (rs.next()){
				String first = rs.getString("first_name");
				String last = rs. getString("last_name");
				String full = (first+ " "+ last);
				name.add(full);
			}
			
			return name;
		}
		
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Fail to fetch supervisor data");
		}
		
		
		return null;
		
	}
	//Takes in a supervisor name and return employee info
	public static ResultSet search(String input) {
		Connection myCon = null;
		PreparedStatement stm= null;
		ResultSet rs;
		
		try {
			String url = "jdbc:mysql://localhost:3306/ssolution";
		    myCon = 	DriverManager.getConnection(url, "root","root1234");
		    
		    stm = myCon.prepareStatement("select employeeid,first_name,last_name from employee where employee.supervisor_name = ?");
			stm.setString(1, input);
		    rs = stm.executeQuery();
		    return rs;
		}catch(Exception e) {
			
		}
		
		return null;
		
	}
}

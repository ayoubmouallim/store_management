package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SignleConnection {

	private static Connection connection=null;

	private SignleConnection() {
		
		String url="jdbc:mysql://localhost:3306/bdd_catalogue";
		
		try {
			connection = DriverManager.getConnection(url,"root","");
			System.out.println("connecter avec succes");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static Connection getConnection() {
		if(connection == null)	
			new SignleConnection();
		 
		return connection;
	}
	
	
	
	
}

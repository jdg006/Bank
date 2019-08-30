package com.revature.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
private static Connection connection;

private static boolean isTest = true;
	
	public static Connection getConnection() throws SQLException {
		
		if(isTest == true) {
			return getH2Connection();
		}
		
		else {
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url = System.getenv("DB_URL");
		String username = System.getenv("DB_USER");
		String password = System.getenv("DB_PASS");
		
		
		if (connection == null || connection.isClosed()) {
		
		connection = DriverManager.getConnection(url, username,password);
		
		}
		
		return connection;
		
		}
	}
	
	public static Connection getH2Connection() {
		
		
		return connection;
		
	}
		
	
}

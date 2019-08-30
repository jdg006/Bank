package com.revature.bank.driver;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.bank.util.ConnectionUtil;

public class Driver {

	public static void main(String[] args) {
		
		Connection connect;
		try {
			connect = ConnectionUtil.getConnection();
			String driver = connect.getMetaData().getDriverName();
			System.out.println(driver);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

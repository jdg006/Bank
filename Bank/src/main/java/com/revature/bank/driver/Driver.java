package com.revature.bank.driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.bank.controller.InputController;
import com.revature.bank.dao.impl.UserDaoImpl;
import com.revature.bank.model.User;
import com.revature.bank.util.ConnectionUtil;

public class Driver {
	
	public static void main(String[] args) {
		
		
		User user = new User(2,"jim","Jimson","bigjim","bigjimpass");
		User user1 = new User(2, "aby", "abyson", "midaby", "midabypass");
		
		UserDaoImpl u = new UserDaoImpl();
		
		
		//System.out.println(u.createUser(user));
		
		//System.out.println(u.updateUser(2, "jon", "jonson", "liljon", "liljonpass"));
		
		//System.out.println(u.createUser(user1));
		
		System.out.println(u.deleteUser(4));
		
		
		System.out.println(u.getUsers());
		System.out.println(u.getUser(1));
		
		
//		System.out.println("Hello there.");
//		InputController.start();

	}

}

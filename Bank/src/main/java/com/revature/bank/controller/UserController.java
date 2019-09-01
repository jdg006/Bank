package com.revature.bank.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.bank.model.Account;
import com.revature.bank.model.AccountAndUser;
import com.revature.bank.model.User;
import com.revature.bank.service.AccountAndUserService;
import com.revature.bank.service.AccountService;
import com.revature.bank.service.UserService;

public class UserController {
	

	
	public static void start() {
		
		System.out.println("If you would like to bank with us you must have a user account. Do you have a user account?"
				+ " Type 'y' for yes, 'n' for no, or 'q' to quit.");
		
		Scanner in = new Scanner(System.in);
		String answer = in.nextLine();
		
		
		if(answer.equalsIgnoreCase("y")) {
			signIn();
		}
		else if (answer.equalsIgnoreCase("n")) {
			createNewUser();
		}
		else if(answer.equalsIgnoreCase("q")) {
			quit();
		}
		else {
			System.out.println("That was not an option. Please try again.");
			start();
		}
		
		in.close();
		
	}
	
	
	public static void signIn() {
		
		User user = null;
		String password = null;
		
		System.out.println("Enter your username to sign in or enter q to quit.");
		Scanner in = new Scanner(System.in);
		String username = in.nextLine().trim();
		
		
		if (username.equalsIgnoreCase("q")) {
			quit();
		}
		else if (checkExistingUsername(username) != null) {
			user = checkExistingUsername(username);
			System.out.println("Enter your password to sign in or q to quit");
			password = in.nextLine();
			if (checkPassword(user, password)) {
				System.out.println("You have successfully logged in.");
				AccountController.setUserInfo(user);
				AccountController.accessAccountFlow();
			}
		}
		else {
		   System.out.println("That username does not exits ");
		   signIn();
		}
		
		in.close();
	}
	
	public static void createNewUser() {
		
		UserService us = new UserService();
		User user;
		String firstName;
		String lastName;
		String username;
		String password;
		
		System.out.println("Enter your first name.");
		
		Scanner in = new Scanner(System.in);
		String answer = in.nextLine();
		
		firstName = answer;

		System.out.println("Enter your last name.");
		
		answer = in.nextLine();
		lastName = answer;
		
		System.out.println("Enter your username.");
		
		answer = in.nextLine();
		username = answer;
		checkNewUsername(username);
		
		System.out.println("Enter your password.");
		
		answer = in.nextLine();
		password = answer;
	    user = new User(0, firstName, lastName, username, password);
		us.createUser(user);
		user = us.getUser(username);
		
		signIn();
		 
		 in.close();
	}
	
	
	public static void checkNewUsername(String username) {
		
		UserService us = new UserService();
		List<String> usernames;
		
		usernames = us.getUsernames();
		
		for (String name : usernames) {
			if (name.matches(username)) {
				Scanner in = new Scanner(System.in);
				System.out.println("Username "+username+" is taken. Please choose another.");
				username = in.nextLine();
				checkNewUsername(username);
				in.close();
			}
		}
	}
	
	public static User checkExistingUsername(String username) {
		
		UserService us = new UserService();
		List<User> users = us.getAllUsers();
		
		for(User user : users) {
			if(user.getUsername().equalsIgnoreCase(username)) {
				return user;
			}
		}
		
		return null;
	}
	
	public static boolean checkPassword(User user, String password) {
		
		if (password.equalsIgnoreCase("q")) {
			quit();
		}
		else if (user.getPassword().matches(password)) {
			return true;
		}
		else {
			System.out.println("That password is not correct. Please try again.");
			System.out.println("Enter your password to sign in or q to quit");
			Scanner in = new Scanner(System.in);
			password = in.nextLine().trim();
			checkPassword(user, password);
		}
		
		return false;
	}
	
	public static void quit() {
		System.out.println("Have a nice day.");
	}
	
}













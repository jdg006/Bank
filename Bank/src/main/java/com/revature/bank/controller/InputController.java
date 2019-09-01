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

public class InputController {
	

	
	public static void start() {
		
		System.out.println("Do you have an account with us?"
				+ " Type 'y' for yes, 'n' for no, or 'q' to quit.");
		
		Scanner in = new Scanner(System.in);
		String answer = in.nextLine();
		
		
		if(answer.equalsIgnoreCase("y")) {
			accessAccountFlow();
		}
		else if (answer.equalsIgnoreCase("n")) {
			createAccountFlow();
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
	
	public static void accessAccountFlow() {
		
		System.out.println("Would you like to access your account(s)? Type 'y' for yes,"
				+ " 'n' for no, or 'q' to quit.");
		
		Scanner in = new Scanner(System.in);
		String answer = in.nextLine();
		
		
		if(answer.equalsIgnoreCase("y")) {
			System.out.println("access account");
		}
		else if(answer.equalsIgnoreCase("n")) {
			createAccountFlow();
		}
		else if(answer.equalsIgnoreCase("q")) {
			quit();
		}
		else {
			System.out.println("That was not an option please try again.");
			createAccountFlow();
		}	
		
		in.close();
		
	}
	
	
	public static void createAccountFlow() {
		
		System.out.println("Would you like to create a new account? Type 'y' for yes,"
						+ " 'n' for no, or 'q' to quit.");
		
		Scanner in = new Scanner(System.in);
		String answer = in.nextLine();
		
		
		if(answer.equalsIgnoreCase("y")) {
			createNewUser();
		}
		else if(answer.equalsIgnoreCase("n")) {
			start();
		}
		else if(answer.equalsIgnoreCase("q")) {
			quit();
		}
		else {
			System.out.println("That was not an option please try again.");
			createAccountFlow();
		}	
		
		in.close();
	}
	
	public static void quit() {
		System.out.println("Have a nice day.");
	}
	
	public static void accessAccount(User user) {
		
		//prompt for username password pair
		
		
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
		checkUsername(username);
		
		System.out.println("Enter your password.");
		
		answer = in.nextLine();
		password = answer;
	    user = new User(0, firstName, lastName, username, password);
		us.createUser(user);
		user = us.getUser(username);
		
		createAccount(user);
		 
		 in.close();
	}
	
	public static void createAccount(User user) {
		
		AccountService as = new AccountService();
		AccountAndUserService aaus = new AccountAndUserService();
		Account account;
		AccountAndUser accountAndUser;
		String acctType;
		int initialDeposit;
		String answer;
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("What kind of account do you want to set up? type 'savings' to set up a savings account."
				+ " type 'checking' to set up a checking account");
		
		answer = in.nextLine();
		
		
		if (answer.contentEquals("savings") || answer.contentEquals("checking")) {
			
			acctType = answer;
			
			System.out.println("How much do you want to open your account with?");
			
			answer = in.nextLine();
			initialDeposit = Integer.parseInt(answer);
			account = new Account(0, acctType, initialDeposit);
			as.createAccount(account);
			
		}
		
		else {
			System.out.println("That's not an option. Please try again.");
			createAccount(user);
		}
		
		 account = as.getLastCreatedAccount();
		 accountAndUser = new AccountAndUser(user.getId(), account.getId());
		 aaus.createAccountAndUser(accountAndUser);
		 
		 System.out.println("You have successfully created an account");
		 accessAccountFlow();
		 
		 in.close();
		
	}
	
	public static void checkAccount(User user) {
		AccountAndUserService aaus = new AccountAndUserService();
		AccountService as = new AccountService();
	}
	
	public static void checkUsername(String username) {
		
		UserService us = new UserService();
		List<String> usernames;
		
		usernames = us.getUsernames();
		
		for (String name : usernames) {
			if (name.matches(username)) {
				Scanner in = new Scanner(System.in);
				System.out.println("Username "+username+" is taken. Please choose another.");
				username = in.nextLine();
				checkUsername(username);
				in.close();
			}
		}
	}
}













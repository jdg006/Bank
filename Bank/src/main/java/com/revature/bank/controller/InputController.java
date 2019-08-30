package com.revature.bank.controller;

import java.util.Scanner;

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
			System.out.println("create account");
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
	
	public static void accessAccount() {
		
		//prompt for username password pair
		
		
	}
	
	public static void createAccount() {
		
		// prompt for information
		
	}
}

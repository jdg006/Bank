package com.revature.bank.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.bank.model.Account;
import com.revature.bank.model.AccountAndUser;
import com.revature.bank.model.User;
import com.revature.bank.service.AccountAndUserService;
import com.revature.bank.service.AccountService;

public class AccountController {
	
	private static User currentUser = null;
	private static Account personalSavings = null;
	private static Account personalChecking = null;
	private static Account jointSavings = null;
	private static Account jointChecking = null;
	
	private static AccountAndUserService aaus = new AccountAndUserService();
	private static AccountService as = new AccountService();
	
public static void accessAccountFlow() {
	System.out.println("What would you like to do now?");
	System.out.println("Enter '1' to view account balances. \n"
				+ "Enter '2' to make a withdraw. \n"
				+ "Enter '3' to make a deposit. \n"
				+ "Enter '4' to make a transfer. \n"
				+ "Enter '5' to add a personal account. \n"
				+ "Enter '6' add a joint account. \n"
				+ "Enter '7' to make an existing account a joint account. \n"
				+ "Enter '8' to close out an account \n"
				+ "Enter '9' to view transaction history."
				+ "Enter 'q' to quit.");
		
		Scanner in = new Scanner(System.in);
		String answer = in.nextLine().trim();
		
		switch(answer) {
		case "1":
			viewBalance();
			break;
		case "2":
			withdraw();
			break;
		case "3":
			deposit();
			break;
		case "4":
			break;
		case "5":
			break;
		case "6":
			break;
		case "7":
			break;
		case "9":
			break;
		case "q":
			break;
		default :
			System.out.println("That is not an option. Please try again");
			accessAccountFlow();
		}
		
		//in.close();
		
	}

	public static void setUserInfo(User user) {
		currentUser = user;
		List<AccountAndUser> allAccountAndUsers; 
		List<Account> accounts = new ArrayList<>();
		Account account = null;
		
		
		allAccountAndUsers = aaus.getAccountAndUserByUserId(currentUser.getId());
		
		for (AccountAndUser accountAndUser : allAccountAndUsers) {
			 account = as.getAccount(accountAndUser.getAccountId());
			 accounts.add(account);
		}
		
		for (Account acct: accounts) {
			if (acct.getType().matches("checking")) {
				personalChecking = acct;
			}
			else if(acct.getType().matches("savings")) {
				personalSavings = acct;
			}
			else if(acct.getType().matches("joint checking")) {
				jointChecking = acct;
			}
			else if(acct.getType().matches("joint savings")) {
				jointSavings = acct;
			}
		}
		
	}
	
	public static Account chooseAccount(String action) {
		
		System.out.println("which account do you want to "+action+"?");
		System.out.println(
				  "Enter '1' to "+action+" your personal checking account. \n"
				+ "Enter '2' to "+action+" your personal savings account. \n"
				+ "Enter '3' to "+action+" your joint checking account. \n"
				+ "Enter '4' to "+action+" your joint savings account. \n"
				+ "Enter '5' to go back to account options. \n"
				+ "Enter 'q' to quit.");
		
		Scanner in = new Scanner(System.in);
		String answer = in.nextLine().trim();
		
		switch(answer) {
		
		case "1":
			
			if (personalChecking != null) {
				return personalChecking;
			}
			else {
				System.out.println("You don't have a personal checking account");
				chooseAccount(action);
			}
			break;
			
		case "2":
			
			if (personalSavings != null) {
				return personalSavings;
			}
			else {
				System.out.println("You don't have a personal savings account");
				chooseAccount(action);
			}
			
			break;
			
		case "3":
			
			if (jointChecking != null) {
				return jointChecking;
			}
			else {
				System.out.println("You don't have a joint checking account");
				chooseAccount(action);
			}
			
			break;
			
		case "4":
			
			if (jointSavings != null) {
				return jointSavings;
			}
			else {
				System.out.println("You don't have a joint savings account");
				chooseAccount(action);
			}
			
			break;
			
		case "5":
			
			accessAccountFlow();
			
			break;
			
		case "q":
			
			quit();
			
			break;
			
		default:
			
			System.out.println("That is not an option. Please try again");
			chooseAccount(action);
		}
		
		in.close();
		
		return null;
	}
	
	public static void viewBalance() {
		Account acct = chooseAccount("view the balance of");
		System.out.println("Your balance is "+acct.getBalance());
		viewBalance();
		
	}
	
	public static void withdraw() {
		Scanner in = new Scanner(System.in);
		float amount;
		Account acct = chooseAccount("withdraw from");
		System.out.println("You have "+acct.getBalance()+" in this account. How much would you like to withdraw?");
		String answer = in.nextLine();
		amount = isFloat(answer);
		
		if (acct.getBalance() > amount) {
			
			float newBalance = acct.getBalance() - amount;
			as.updateAccount(acct.getId(), newBalance, acct.getType());
			System.out.println("You have withdrawn "+amount+" from your "+acct.getType()+" account.\n"
					+ "Your new balance is "+newBalance+".");
		}
		else {
			System.out.println("You do not have enough money in your account to withdraw tah much. \n"
					+ "Either pick another account or a smaller amount to withdraw.");
			withdraw();
		}
		
		setUserInfo(currentUser);
		withdraw();
		//in.close();
	}
	
	public static void deposit() {
		Scanner in = new Scanner(System.in);
		float amount;
		Account acct = chooseAccount("deposit into");
		System.out.println("You have "+acct.getBalance()+" in this account. How much would you like to deposit?");
		String answer = in.nextLine();
		amount = isFloat(answer);
		
		float newBalance = acct.getBalance() + amount;
		as.updateAccount(acct.getId(), newBalance, acct.getType());
		System.out.println("You have deposited "+amount+" into your "+acct.getType()+" account.\n"
				+ "Your new balance is "+newBalance+".");
		setUserInfo(currentUser);
		deposit();
		//in.close();
	}
	
	public static void transfer() {
		// transfer from one account to another
	}
	
	public static void addPersonalAccount() {
		//create an add personal account max 1 type per user
	}
	
	public static void addJointAccount() {
		// create new joint accountcheck to see if they already have one
	}
	
	public static void makeExistingAccountJoint() {
		// edit account type to make existing account joint
		// edit join table to reflect new type
	}
	
	public static void closeAccount() {
		//close out accout 
		//return value to user
	}
	
	public static void viewTransactionHistory() {
		//requires alterations to db, dao, daoImpl, services.
	}
	
	public static float isFloat(String input) {
		if(input.matches("[0-9]+")) {
			return Float.parseFloat(input);
		}
		else {
			System.out.println("Thats an invalid input. Try again.");
			Scanner in = new Scanner(System.in);
			input = in.nextLine().trim();
			return isFloat(input);
		}
	}
	
	public static void quit() {
		System.out.println("Have a nice day");
		System.exit(0);
	}
	
	



public static void createAccountFlow() {
		
		System.out.println("Would you like to create a new account? Type 'y' for yes,"
						+ " 'n' for no, or 'q' to quit.");
		
		Scanner in = new Scanner(System.in);
		String answer = in.nextLine();
		
		
		if(answer.equalsIgnoreCase("y")) {
			//createNewUser();
		}
		else if(answer.equalsIgnoreCase("n")) {
			//start();
		}
		else if(answer.equalsIgnoreCase("q")) {
			//quit();
		}
		else {
			System.out.println("That was not an option please try again.");
			createAccountFlow();
		}	
		
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
			checkAccount(user, answer);
			
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
		// accessAccountFlow();
		 
		 in.close();
		
	}
	
	public static void checkAccount(User user, String acctType) {
		AccountAndUserService aaus = new AccountAndUserService();
		AccountService as = new AccountService();
		List<AccountAndUser> accountAndUsers = aaus.getAccountAndUserByUserId(user.getId());
		List<Account> accounts = null;
		Account account;
		boolean personalChecking = false;
		boolean personalSavings = false;
		boolean jointChecking = false;
		boolean jointSavings = false;
		
		for (AccountAndUser accountAndUser: accountAndUsers) {
			account = as.getAccount(accountAndUser.getAccountId());
			accounts.add(account);
		}
		
		
		
		for (Account acct : accounts) {
			
		}
		
		
		
		
	}
	

}

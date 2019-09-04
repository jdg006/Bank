package com.revature.bank.controller;

import java.text.NumberFormat;
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
	
	private static AccountAndUserService aaus = new AccountAndUserService();
	private static AccountService as = new AccountService();
	
	private static Scanner in = new Scanner(System.in);
	
	public static void accessAccountFlow() {
		
		if (personalSavings == null && personalChecking == null) {
			addPersonalAccount();
		}
		System.out.println("What would you like to do now?\n"
				+ "Enter '1' to view account balances. \n"
				+ "Enter '2' to make a withdraw. \n"
				+ "Enter '3' to make a deposit. \n"
				+ "Enter '4' to make a transfer. \n"
				+ "Enter '5' to add a personal account. \n"
				+ "Enter '6' to log out. \n"
				+ "Enter 'q' to quit.");
			
		String answer = in.nextLine().trim();
			
		switch(answer) {
		case "1":
			viewBalance();
			in.close();
			return;
		case "2":
			withdraw();
			return;
		case "3":
			deposit();
			return;
		case "4":
			transfer();
			return;
		case "5":
			addPersonalAccount();
			break;
		case "6":
			UserController.logOut();
			break;
		case "q":
			quit();
			break;
		default :
		System.out.println("That is not an option. Please try again");
		accessAccountFlow();
		}
		in.close();
	}

	public static void setUserInfo(User user) {
		
		personalSavings = null;
		personalChecking = null;
		
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
		}
	}
	
	public static Account chooseAccount(String action) {
		
		System.out.println("which account do you want to "+action+"?");
		System.out.println(
				  "Enter '1' to "+action+" your personal checking account. \n"
				+ "Enter '2' to "+action+" your personal savings account. \n"
				+ "Enter '3' to go back to account options. \n");
		
		String answer = in.nextLine().trim();
		
		switch(answer) {
		
		case "1":
			
			if (personalChecking != null) {
				return personalChecking;
				
			}
			else {
				System.out.println("You don't have a personal checking account");
				return chooseAccount(action);
			}
			
		case "2":
			
			if (personalSavings != null) {
				return personalSavings;
			}
			else {
				System.out.println("You don't have a personal savings account");
				return chooseAccount(action);
			}
			
		case "3":
				accessAccountFlow();
				break;
		default:
			
			System.out.println("That is not an option. Please try again");
			return chooseAccount(action);
		}
		
		return null;
	}
	
	public static void viewBalance() {
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		Account acct = chooseAccount("view the balance of");
		String formattedBalance = formatter.format(acct.getBalance());
		System.out.println("Your balance is "+formattedBalance);
		viewBalance();
		
	}
	
	public static void withdraw() {
		double amount;
		Account acct = chooseAccount("withdraw from");
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String formattedBalance = formatter.format(acct.getBalance());
		System.out.println("You have "+formattedBalance+" in this account. How much would you like to withdraw?");
		String answer = in.nextLine();
		amount = isDouble(answer);
		
		if (acct.getBalance() > amount) {
			
			double newBalance = acct.getBalance() - amount;
			as.updateAccount(acct.getId(), newBalance, acct.getType());
			String formattedAmount = formatter.format(amount);
			String formattedNewBalance = formatter.format(newBalance);
			System.out.println("You have withdrawn "+formattedAmount+" from your "+acct.getType()+" account.\n"
					+ "Your new balance is "+formattedNewBalance+".");
		}
		else {
			System.out.println("You do not have enough money in your account to withdraw that much. \n"
					+ "Either pick another account or a smaller amount to withdraw.");
			withdraw();
		}
		
		setUserInfo(currentUser);
		withdraw();
	}
	
	public static void deposit() {
		double amount;
		Account acct = chooseAccount("deposit into");
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String formattedBalance = formatter.format(acct.getBalance());
		System.out.println("You have "+formattedBalance+" in this account. How much would you like to deposit?");
		String answer = in.nextLine();
		amount = isDouble(answer);
		
		double newBalance = acct.getBalance() + amount;
		as.updateAccount(acct.getId(), newBalance, acct.getType());
		String formattedAmount = formatter.format(amount);
		String formattedNewBalance = formatter.format(newBalance);
		System.out.println("You have deposited "+formattedAmount+" into your "+acct.getType()+" account.\n"
				+ "Your new balance is "+formattedNewBalance+".");
		setUserInfo(currentUser);
		deposit();
	}
	
	public static void transfer() {
		
		System.out.println("Enter the username of the person who you would like to transfer money to \n or 'menu' to return to the main menu");
		String username = in.nextLine().trim();
		if(username.equalsIgnoreCase("menu")) {
			accessAccountFlow();
		}
		User receiver = UserController.checkExistingUsername(username);
		if(receiver == null) {
			System.out.println("Thats not a username of anyone at this bank. Please try again.");
			transfer();
			return;
		}
		
		List<Account> accounts = as.getAccountsByUserId(receiver.getId());
		System.out.println("Which account do you want to send money to?\n"
				+ "Type 'savings' to select their savings account.\n"
				+ "Type 'checking' to select their checking account.");
		String acctType = in.nextLine().trim();
		Account receivingAccount = checkIfUserHasAccountType(accounts, acctType);
		
		System.out.println("Enter an amount to transfer.");
		double amount = isDouble(in.nextLine().trim());
		
		Account sender = chooseAccount("transfer from");
		
		while(sender.getBalance() < amount) {
			System.out.println("The balance of that account is too low. Choose another.");
			sender = chooseAccount("transfer from");
		}
		
		as.transfer(sender.getId(), receivingAccount.getId(), amount);
		sender = as.getAccount(sender.getId());
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String formattedBalance = formatter.format(sender.getBalance());
		
		System.out.println("Your transfer has been completed.\n"
				+ "you have "+ formattedBalance+" left in your account.");
		
		accessAccountFlow();
		return;
	}
	
	public static Account checkIfUserHasAccountType(List<Account> accounts, String acctType) {
		
		for(Account account: accounts) {
			if (account.getType().equalsIgnoreCase(acctType)) {
				return account;
			}
		}
		
		System.out.println("This user does not have that type of account.\n Please enter a diferent account type.");
		acctType = in.nextLine().trim();
		return checkIfUserHasAccountType(accounts, acctType);
	}
	
	public static void addPersonalAccount() {
		
		if (personalSavings != null && personalChecking != null) {
			System.out.println("You already have the maximum number of personal checking and savings accounts.");
			accessAccountFlow();
		}
		
		Account account;
		AccountAndUser accountAndUser;
		String acctType = null;
		double initialDeposit;
		String answer;
		
		System.out.println("What kind of account do you want to set up? type 'savings' to set up a personal savings account.\n"
				+ " type 'checking' to set up a personal checking account.");
		
		if(personalSavings != null || personalChecking != null) {
			 System.out.println("type 'p' to return to the previous menu.");
		}
		
		answer = in.nextLine();
		
		if(personalSavings != null || personalChecking != null) {
			if(answer.equalsIgnoreCase("p")) {
				accessAccountFlow();
			}
		}
		
		 if(answer.equalsIgnoreCase("savings")) {
			if(personalSavings == null) {
				acctType = answer;
			}
			else {
				System.out.println("You already have a personal savings account. \n"
						+ " Either choose another account type or go back to the previous menu.");
				addPersonalAccount();
			}
		}
		else if(answer.equalsIgnoreCase("checking")) {
			if(personalChecking == null) {
				acctType = answer;
			}
			else {
				System.out.println("You already have a personal checking account. \n"
						+ " Either choose another account type or go back to the previous menu.");
				addPersonalAccount();
			}
		}
		else {
			System.out.println("That's not an option. Please try again.");
			addPersonalAccount();
		}
		
		System.out.println("How much do you want to open your account with?");
		
		answer = in.nextLine();
		initialDeposit = isDouble(answer);
		account = new Account(0, acctType, initialDeposit);
		as.createAccount(account);
		account = as.getLastCreatedAccount();
		accountAndUser = new AccountAndUser(currentUser.getId(), account.getId());
		aaus.createAccountAndUser(accountAndUser);
		System.out.println("You have successfully created an account");
		setUserInfo(currentUser);
		accessAccountFlow();
	}
	
	public static double isDouble(String input) {
		if(input.matches("\\.")) {
			System.out.println("Thats an invalid input. Try again.");
			input = in.nextLine().trim();
			return isDouble(input);
		}
		else if(input.matches("[0-9.]+")) {
			String[] decimalDivider;
			decimalDivider = input.split("\\.");
			if(decimalDivider.length < 2) {
				return Double.parseDouble(input);	
			}
			if (decimalDivider.length > 2 || decimalDivider[1].length() > 2) {
				System.out.println("Thats an invalid input. Try again.");
				input = in.nextLine().trim();
				return isDouble(input);
			}
			else {
				return Double.parseDouble(input);	
			}
		}
		else {
			System.out.println("Thats an invalid input. Try again.");
			input = in.nextLine().trim();
			return isDouble(input);
		}
	}
	
	public static void quit() {
		System.out.println("Have a nice day");
		in.close();
		System.exit(0);
		
	}
}

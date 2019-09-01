package com.revature.bank.driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.bank.controller.InputController;
import com.revature.bank.dao.impl.AccountAndUserDaoImpl;
import com.revature.bank.dao.impl.AccountDaoImpl;
import com.revature.bank.dao.impl.UserDaoImpl;
import com.revature.bank.model.Account;
import com.revature.bank.model.AccountAndUser;
import com.revature.bank.model.User;
import com.revature.bank.util.ConnectionUtil;

public class Driver {
	
	public static void main(String[] args) {
		
		
//		User user = new User(2,"jim","Jimson","bigjim","bigjimpass");
//		User user1 = new User(2, "aby", "abyson", "midaby", "midabypass");
//		
//		UserDaoImpl u = new UserDaoImpl();
//		AccountDaoImpl a = new AccountDaoImpl();
//		AccountAndUserDaoImpl aau = new AccountAndUserDaoImpl();
//		
//		Account account = new Account(1, "checking", (float) 1000.00);
//		
//		AccountAndUser accountAndUser = new AccountAndUser(2,4);
		
		//System.out.println(u.createUser(user));
		//System.out.println(u.updateUser(2, "jon", "jonson", "liljon", "liljonpass"));
		//System.out.println(u.createUser(user1));
		//System.out.println(u.deleteUser(4));
		//System.out.println(a.createAccount(account));
		//System.out.println(a.updateAccount(2, 2000, "checking"));
		//System.out.println(a.deleteAccountById(3));
		//System.out.println(u.getUsers());
		//System.out.println(u.getUser(1));
		//System.out.println(a.getAccounts());
		//System.out.println(a.getAccountById(1));
		//System.out.println("Hello there.");
		//System.out.println(aau.getAllAccountAndUser());
		//System.out.println(aau.getAccountAndUser(1,1));
		//System.out.println(aau.createAccountAndUser(accountAndUser));
		//System.out.println(aau.deleteAccountAndUser(2, 4));
		
		InputController.start();
	}

}

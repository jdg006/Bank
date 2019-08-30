package com.revature.bank.driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.bank.controller.InputController;
import com.revature.bank.util.ConnectionUtil;

public class Driver {
	
	public static void main(String[] args) {
		
		System.out.println("Hello there.");
		InputController.start();

	}

}

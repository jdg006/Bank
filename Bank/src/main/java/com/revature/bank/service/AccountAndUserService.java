package com.revature.bank.service;

import java.util.List;

import com.revature.bank.dao.AccountAndUserDao;
import com.revature.bank.dao.impl.AccountAndUserDaoImpl;
import com.revature.bank.model.AccountAndUser;

public class AccountAndUserService {
	
	private AccountAndUserDao aaud = new AccountAndUserDaoImpl();
	
	public AccountAndUserService () {
		
	}
	
	public List<AccountAndUser> getAllAccountAndUser(){
		
		return aaud.getAllAccountAndUser();
		
	}
	
	public List<AccountAndUser> getAccountAndUserByUserId(int userId){
		
		return aaud.getAccountAndUserByUserId(userId);
		
	}
	
	public List<AccountAndUser> getAccountAndUserByAccountId(int accountId){
		
		return aaud.getAccountAndUserByAccountId(accountId);
		
	}
	
	public AccountAndUser getAccountAndUser(int userId, int accountId){
			
		return aaud.getAccountAndUser(userId, accountId);
			
		}
	
	public boolean createAccountAndUser(AccountAndUser aau) {
		
		return aaud.createAccountAndUser(aau);
		
	}
	
	
	public boolean deleteAccountAndUser(int userId, int accountId) {
		
		return aaud.deleteAccountAndUser(userId, accountId);
		
	}
	
}

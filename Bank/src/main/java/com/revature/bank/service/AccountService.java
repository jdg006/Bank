package com.revature.bank.service;

import java.util.List;

import com.revature.bank.dao.AccountDao;
import com.revature.bank.dao.impl.AccountDaoImpl;
import com.revature.bank.model.Account;


public class AccountService {
	
	private AccountDao ad = new AccountDaoImpl();
	
public List<Account> getAllAccounts(){
		
		return ad.getAccounts();
		
	}
	
	public Account getAccountAndUser(int id){
			
		return ad.getAccountById(id);
			
		}
	
	public boolean createAccount(Account a) {
		
		return ad.createAccount(a);
		
	}
	
	public boolean updateAccount(int id, float balance, String type) {
		
		return ad.updateAccount(id, balance, type);
		
	}
	
	
	public boolean deleteAccount(int id) {
		
		return ad.deleteAccountById(id);
		
	}

}

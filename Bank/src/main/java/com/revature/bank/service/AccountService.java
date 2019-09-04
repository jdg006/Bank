package com.revature.bank.service;

import java.util.List;

import com.revature.bank.dao.AccountDao;
import com.revature.bank.dao.impl.AccountDaoImpl;
import com.revature.bank.model.Account;


public class AccountService {
	
	private AccountDao ad = new AccountDaoImpl();
	
	public AccountService() {
		
	}
	
	public List<Account> getAllAccounts(){
		
		return ad.getAccounts();
		
	}
	
	public Account getAccount(int id){
			
		return ad.getAccountById(id);
			
	}
	
	public Account getLastCreatedAccount() {
		
		return ad.getLastCreatedAccount();
		
	}
	
	public boolean createAccount(Account a) {
		
		return ad.createAccount(a);
		
	}
	
	public boolean updateAccount(int id, double balance, String type) {
		
		return ad.updateAccount(id, balance, type);
		
	}
	
	
	public boolean deleteAccount(int id) {
		
		return ad.deleteAccountById(id);
		
	}
	
	public boolean transfer(int senderId, int receiverId, double amount) {
		return ad.transfer(senderId, receiverId, amount);
	}
	
	public List<Account> getAccountsByUserId(int userId){
		return ad.getAccountsByUserId(userId);
	}

}

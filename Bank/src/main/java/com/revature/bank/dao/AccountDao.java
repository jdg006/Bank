package com.revature.bank.dao;

import java.util.List;

import com.revature.bank.model.Account;

public interface AccountDao {
	
	public List<Account> getAccounts();
	public Account getAccountById(int id);
	public Account getLastCreatedAccount();
	public boolean createAccount(Account account);
	public boolean updateAccount(int id, double balance, String type);
	public boolean deleteAccountById(int id);
	public boolean transfer(int senderId, int receiverId, double amount);
	public List<Account> getAccountsByUserId(int userId);

}

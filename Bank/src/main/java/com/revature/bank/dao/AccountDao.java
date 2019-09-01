package com.revature.bank.dao;

import java.util.List;

import com.revature.bank.model.Account;

public interface AccountDao {
	
	public List<Account> getAccounts();
	public Account getAccountById(int id);
	public boolean createAccount(Account account);
	public boolean updateAccount(int id, float balance, String type);
	public boolean deleteAccountById(int id);

}

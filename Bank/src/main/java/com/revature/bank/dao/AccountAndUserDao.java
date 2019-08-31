package com.revature.bank.dao;

import java.util.List;

import com.revature.bank.model.AccountAndUser;

public interface AccountAndUserDao {
	
	public List <AccountAndUser> getAllAccountAndUser();
	public AccountAndUser getAccountAndUser(int userId, int accountId); 
	public boolean createAccountAndUser(int userId, int accountId);
	public boolean updateAccountAndUser(int userId, int accountId);
	public boolean deleteAccountAndUser(int userId, int accountId);

}

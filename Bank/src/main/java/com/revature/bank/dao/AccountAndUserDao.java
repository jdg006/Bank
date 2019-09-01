package com.revature.bank.dao;

import java.util.List;

import com.revature.bank.model.AccountAndUser;

public interface AccountAndUserDao {
	
	public List <AccountAndUser> getAllAccountAndUser();
	public List <AccountAndUser> getAccountAndUserByUserId(int userId);
	public List <AccountAndUser> getAccountAndUserByAccountId(int accountId);
	public AccountAndUser getAccountAndUser(int userId, int accountId);
	public boolean createAccountAndUser(AccountAndUser accountAndUser);
	public boolean updateAccountAndUser(int userId, int accountId);
	public boolean deleteAccountAndUser(int userId, int accountId);

}

package com.revature.bank.model;

public class AccountAndUser {
	
	private int userId;
	private int accountId;
	
	public AccountAndUser(int userId, int accountId) {
		super();
		this.userId = userId;
		this.accountId = accountId;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountAndUser other = (AccountAndUser) obj;
		if (accountId != other.accountId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccountsAndUsers [userId=" + userId + ", accountId=" + accountId + "]";
	}
	
}

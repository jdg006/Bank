package com.revature.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.dao.AccountDao;
import com.revature.bank.model.Account;
import com.revature.bank.util.ConnectionUtil;

public class AccountDaoImpl implements AccountDao{

	@Override
	public List<Account> getAccounts() {
		
		String sql = "select * from account";
		
		List <Account> accounts =  new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);){
			
			while(rs.next()) {
				
				int accountId = rs.getInt("id");
				String type = rs.getString("type_of");
				float balance = rs.getFloat("balance");
				
				Account a = new Account(accountId, type, balance);
	
				accounts.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return accounts;
	}

	@Override
	public Account getAccountById(int id) {
		String sql = "select * from account where id = ?";
		
		Account account = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int accountId = rs.getInt("id");
				String type = rs.getString("type_of");
				float balance = rs.getFloat("balance");
				
				
				 account = new Account(accountId, type, balance);
	
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}
	
	public Account getLastCreatedAccount () {
		
		String sql = "select * from account order by id desc limit 1";
		
Account account = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int accountId = rs.getInt("id");
				String type = rs.getString("type_of");
				float balance = rs.getFloat("balance");
				
				
				 account = new Account(accountId, type, balance);
	
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
		
	}

	@Override
	public boolean createAccount(Account account) {
		String sql = "insert into account (type_of, balance) values (?,?)";
		boolean wasCreated = false;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
	
			ps.setString(1, account.getType());
			ps.setFloat(2, account.getBalance());
			
			if (ps.executeUpdate() == 1) {
				wasCreated = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wasCreated;
	}

	@Override
	public boolean updateAccount(int id, float balance, String type) {
		String sql = "update account set balance = ?, type_of = ? where id = ?";
		boolean wasUpdated = false;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setFloat(1, balance);
			ps.setString(2, type);
			ps.setInt(3, id);
			
			if(ps.executeUpdate() == 1) {
				wasUpdated = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wasUpdated;
	}

	@Override
	public boolean deleteAccountById(int id) {
		String sql = "delete from account where id = ?";
		boolean wasDeleted = false;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1, id);
			
			if (ps.executeUpdate() == 1) {
				wasDeleted = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasDeleted;
	}
	
	

}

package com.revature.bank.dao.impl;

import java.sql.CallableStatement;
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
				double balance = rs.getFloat("balance");
				
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
				double balance = rs.getFloat("balance");
				
				
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
				double balance = rs.getFloat("balance");
				
				
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
			ps.setDouble(2, account.getBalance());
			
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
	public boolean updateAccount(int id, double balance, String type) {
		String sql = "update account set balance = ?, type_of = ? where id = ?";
		boolean wasUpdated = false;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setDouble(1, balance);
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

	@Override
	public boolean transfer(int senderId, int receiverId, double amount) {
		String sql = "{call transfer(?,?,?)}";
		
		try(Connection c = ConnectionUtil.getConnection();
				CallableStatement cs = c.prepareCall(sql)){
				
				cs.setInt(1, senderId);
				cs.setInt(2, receiverId);
				cs.setDouble(3, amount);
				
				 if(cs.execute()) {
					 return true;
				 }
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return false;
	}

	@Override
	public List<Account> getAccountsByUserId(int userId) {
		String sql = "select account.id, account.type_of, account.balance from user_info join user_id_account_id\r\n" + 
				"on user_info.id = user_id_account_id.user_id join account on \r\n" + 
				"user_id_account_id.account_id = account.id where user_id = ?";
		List <Account> accounts =  new ArrayList<>();
		Account account = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			
				while(rs.next()) {
				
				int accountId = rs.getInt("id");
				String type = rs.getString("type_of");
				double balance = rs.getFloat("balance");
				
				
				account = new Account(accountId, type, balance);
				accounts.add(account);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return accounts;
		
	}
}

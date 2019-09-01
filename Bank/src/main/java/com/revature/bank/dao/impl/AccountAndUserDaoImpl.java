package com.revature.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.dao.AccountAndUserDao;
import com.revature.bank.model.AccountAndUser;
import com.revature.bank.model.User;
import com.revature.bank.util.ConnectionUtil;

public class AccountAndUserDaoImpl implements AccountAndUserDao{

	@Override
	public List<AccountAndUser> getAllAccountAndUser() {
		String sql = "select * from user_id_account_id";
		
		List <AccountAndUser> accountAndUsers = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);){
			
			while(rs.next()) {
				
				int userId = rs.getInt("user_id");
				int accountId = rs.getInt("account_id");
				
				AccountAndUser aau = new AccountAndUser(userId, accountId);
	
				accountAndUsers.add(aau);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accountAndUsers;
	}

	@Override
	public AccountAndUser getAccountAndUser(int userId, int accountId) {
		String sql = "select * from user_id_account_id where user_id = ? and account_id = ?";
		
		AccountAndUser aau = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1, userId);
			ps.setInt(2, accountId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				 userId = rs.getInt("user_id");
				accountId = rs.getInt("account_id");
				
				 aau = new AccountAndUser(userId, accountId);
	
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aau;	
	}

	@Override
	public boolean createAccountAndUser(AccountAndUser accountAndUser) {
		String sql = "insert into user_id_account_id (user_id, account_id) values (?,?)";
		boolean wasCreated = false;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1, accountAndUser.getUserId());
			ps.setInt(2, accountAndUser.getAccountId());
			
			if(ps.executeUpdate() == 1) {
				wasCreated = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wasCreated;
	}

	@Override
	public boolean updateAccountAndUser(int userId, int accountId) {
		String sql = "update user_id_account_id set userId = ?, account_id = ?";
		boolean wasUpdated = false;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1, userId);
			ps.setInt(2, accountId);
			
			
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
	public boolean deleteAccountAndUser(int userId, int accountId) {
		
		String sql = "delete from user_id_account_id where user_id = ? and account_id = ?";
		boolean wasDeleted = false;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1, userId);
			ps.setInt(2, accountId);
			
			if (ps.executeUpdate() == 1) {
				wasDeleted = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasDeleted;
		
	}
	
}

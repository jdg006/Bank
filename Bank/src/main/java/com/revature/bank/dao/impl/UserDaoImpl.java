package com.revature.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.dao.UserDao;
import com.revature.bank.model.User;
import com.revature.bank.util.ConnectionUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public List <User> getUsers() {
		
		String sql = "select * from user_info";
		
		List <User> users = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);){
			
			while(rs.next()) {
				
				int userId = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				User u = new User(userId, firstName, lastName, username, password);
	
				users.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;

	}

	@Override
	public User getUser(int id) {
		
		String sql = "select * from user_info where id = ?";
		
		User u = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int userId = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				 u = new User(userId, firstName, lastName, username, password);
	
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
	}
	
	public User getUser(String username) {
		
		String sql = "select * from user_info where username = ?";
		
		User u = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int userId = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String password = rs.getString("password");
			    username = rs.getString("username");
				
				 u = new User(userId, firstName, lastName, username, password);
	
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
		
	}
	
	public List <String> getUsernames(){
		
		List <String> usernames = new ArrayList<>();
		
		String sql = "select username from user_info";
		
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);){
			
			while(rs.next()) {
				
				String username = rs.getString("username");
				usernames.add(username);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usernames;
	} 

	@Override
	public boolean createUser(User user) {
		String sql = "insert into user_info (first_name, last_name, username, password) values (?,?,?,?)";
		boolean wasCreated = false;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, user.getFirst_name());
			ps.setString(2, user.getLast_name());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			
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
	public boolean updateUser(int toUpdateId, String firstName, String lastName, String username, String password) {
		String sql = "update user_info set first_name = ?, last_name = ?, username = ?, password = ? where id = ?";
		boolean wasUpdated = false;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setInt(5, toUpdateId);
			
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
	public boolean deleteUser(int id) {
		String sql = "delete from user_info where id = ?";
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

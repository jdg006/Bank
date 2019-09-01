package com.revature.bank.service;

import java.util.List;

import com.revature.bank.dao.UserDao;
import com.revature.bank.dao.impl.UserDaoImpl;
import com.revature.bank.model.User;

public class UserService {
	
	private UserDao ud = new UserDaoImpl();
	
	public UserService () {
		
	}
	
	public List<User> getAllUsers(){
		
		return ud.getUsers();
		
	}
	
	public User getUser(int id) {
		
		return ud.getUser(id);
		
	}
	
	public User getUser(String username) {
		return ud.getUser(username);
	}
	
	public List <String> getUsernames(){
		return ud.getUsernames();
	}
	
	public boolean createUser(User user) {
		
		return ud.createUser(user);
		
	}
	
	public boolean updateUser(User user) {
		
		int toUpdateId = user.getId();
		String firstName = user.getFirst_name();
		String lastName = user.getLast_name();
		String username = user.getUsername();
		String password = user.getPassword();
		
		
		return ud.updateUser(toUpdateId, firstName, lastName, username, password);
		
	}
	
	public boolean deleteUser (int id) {
		
		return ud.deleteUser(id);
		
	}

}

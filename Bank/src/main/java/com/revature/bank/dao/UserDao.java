package com.revature.bank.dao;

import java.util.List;

import com.revature.bank.model.User;

public interface UserDao {

	public List <User> getUsers();

	public User getUser(int id);
	public User getUser(String username);
	public List <String> getUsernames();
	public boolean createUser(User user);
	public boolean updateUser(int toUpdateId, String firstName, String lastName, String username, String password);
	public boolean deleteUser(int id);
	
}

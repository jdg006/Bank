package com.revature.bank.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.bank.model.User;
import com.revature.bank.util.ConnectionUtil;

public class UserDaoImplTest {
	
	@Before
	public void setUp() throws SQLException, FileNotFoundException {
		try(Connection c = ConnectionUtil.getConnection();){
			RunScript.execute(c, new FileReader("setup.sql"));
		}
	}
	
	@After
	public void truncate() {
		String sql = "truncate account,user_id_account_id,user_info";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);)
		{
			ps.executeUpdate();

		}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	UserDaoImpl udi = new UserDaoImpl();
	
	@Test
	public void testCreateUser() {
		
		User user = new User(0, "Bill", "Billson","Billyboy","bbpass");
		
		assertTrue(udi.createUser(user));
		assertEquals(udi.getUsers().size(), 4);
		
	}
	
	@Test
	public void testDeleteUser() {
		User user = new User(0, "Bill", "Billson","Billyboy","bbpass");
		udi.createUser(user);
		
		assertTrue(udi.deleteUser(4));
		assertEquals(udi.getUsers().size(), 3);
		
	}
	
	@Test
	public void testGetUserById() {
		
		assertEquals(udi.getUser(1).getFirst_name(),"joe");
		
	}
	
	@Test
	public void testGetUserByUsername() {
		
		assertEquals(udi.getUser("joeu").getFirst_name(),"joe");
		
	}
	
	@Test
	public void testGetUsers() {
		
		List<User> users = udi.getUsers();
		
		assertEquals(users.get(0).getFirst_name(), "joe");
		assertEquals(users.get(1).getFirst_name(), "jim");
		assertEquals(users.get(2).getFirst_name(), "jil");
		
	}
	
	@Test
	public void testGetUsernames() {
		
		List<String> usernames = udi.getUsernames();
		
		assertEquals(usernames.get(0),"joeu");
		assertEquals(usernames.get(1),"jimu");
		assertEquals(usernames.get(2),"jilu");
		
	}
	
	@Test
	public void testUpdateUser() {
		
		udi.updateUser(1, "jack", "jackson", "jacksusername", "jackpass");
		
		assertEquals(udi.getUser(1).getFirst_name(), "jack");
		
	}

}

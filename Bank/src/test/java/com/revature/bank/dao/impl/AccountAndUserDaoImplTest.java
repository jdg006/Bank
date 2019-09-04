package com.revature.bank.dao.impl;

import static org.junit.Assert.assertEquals;
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

import com.revature.bank.model.AccountAndUser;
import com.revature.bank.util.ConnectionUtil;

public class AccountAndUserDaoImplTest {
	
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
	
	AccountAndUserDaoImpl aaudi = new AccountAndUserDaoImpl();
	
	@Test
	public void testCreateAccountAndUser() {
		
		AccountAndUser aau = new AccountAndUser(2,1);
		
		aaudi.createAccountAndUser(aau);
		assertEquals(aaudi.getAllAccountAndUser().size(), 5);
		
	}
	
	@Test
	public void testDeleteAccountAndUser() {
		
		aaudi.deleteAccountAndUser(2, 3);
		assertEquals(aaudi.getAllAccountAndUser().size(),3);
		
	}
	
	@Test
	public void testGetAccountAndUser() {
		
		AccountAndUser aau = new AccountAndUser(1,1);
		assertEquals(aaudi.getAccountAndUser(1, 1),aau);
		
	}
	
	@Test
	public void testGetAccountAndUserByAccountId() {
		
		AccountAndUser aau = new AccountAndUser(1,1);
		List<AccountAndUser> aaus = aaudi.getAccountAndUserByAccountId(1);
		assertEquals(aaus.get(0), aau);
	}
	
	@Test
	public void testGetAccountAndUserByUserId() {
		AccountAndUser aau1 = new AccountAndUser(1,1);
		AccountAndUser aau2 = new AccountAndUser(1,2);
		List<AccountAndUser> aaus = aaudi.getAccountAndUserByUserId(1);
		
		assertEquals(aaus.get(0),aau1);
		assertEquals(aaus.get(1),aau2);
		
	}
	
	@Test
	public void testGetAllAccountAndUser() {
		List<AccountAndUser> aaus = aaudi.getAllAccountAndUser();
		AccountAndUser aau1 = new AccountAndUser(1,1);
		AccountAndUser aau2 = new AccountAndUser(1,2);
		AccountAndUser aau3 = new AccountAndUser(2,3);
		AccountAndUser aau4 = new AccountAndUser(3,4);
		
		assertEquals(aaus.get(0),aau1);
		assertEquals(aaus.get(1),aau2);
		assertEquals(aaus.get(2),aau3);
		assertEquals(aaus.get(3),aau4);
	
	}

}

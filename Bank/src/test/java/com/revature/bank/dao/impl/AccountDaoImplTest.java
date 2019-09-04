package com.revature.bank.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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

import com.revature.bank.model.Account;
import com.revature.bank.util.ConnectionUtil;

public class AccountDaoImplTest {
	
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
	
	public static AccountDaoImpl adi = new AccountDaoImpl();
	Account a = new Account(0,"checking",2000);
	
	@Test 
	public void testCreateAccountReturnsTrue() {
		assertTrue(adi.createAccount(a));
	}
	
	@Test
	public void testCreateAccountAddsAnAccountToDB() {
		int numOfAccountsBeforeCreate = adi.getAccounts().size();
		adi.createAccount(a);
		int numOfAccountsAfterCreate = adi.getAccounts().size();
		
		assertEquals(numOfAccountsBeforeCreate, numOfAccountsAfterCreate -1);
	}
	
	@Test
	public void testCreateAccountAddsCorrectAccountToDB() {
		Account b = new Account(0,"savings", 1234567);
		adi.createAccount(b);
		Account c = adi.getLastCreatedAccount();
		b.setId(c.getId());
		assertEquals(b,c);
		
	}
	
	@Test 
	public void testDeleteAccountReturnsTrue() {
		adi.createAccount(a);
		assertTrue(adi.deleteAccountById(adi.getLastCreatedAccount().getId()));
	}
	
	@Test
	public void testDeleteAccountRemovesAnAccountFromTheDB() {
		adi.createAccount(a);
		int beforeDelete = adi.getAccounts().size();
		adi.deleteAccountById(adi.getLastCreatedAccount().getId());
		int afterDelete = adi.getAccounts().size();
		assertEquals(beforeDelete, afterDelete + 1);
	}
	
	@Test
	public void testDelectAccountRemovesTheCorrectAccount() {
		Account b = new Account(0,"checking", 987654);
		adi.createAccount(b);
		Account c = adi.getLastCreatedAccount();
		b.setId(c.getId());
		adi.deleteAccountById(b.getId());
		
		assertNull(adi.getAccountById(b.getId()));
	}
	
	@Test
	public void testGetAccountByIdReturnsTrue() {
		assertTrue(adi.createAccount(a));
	}
	
	@Test
	public void testGetAccountByIdReturnsCorrectAccount() {
		Account b = new Account(0,"checking", 2222);
		adi.createAccount(b);
		Account c = adi.getLastCreatedAccount();
		b.setId(c.getId());
		assertEquals(b,c);
	}
	
	
	@Test
	public void testGetAccountsReturnsList() {
		assertTrue(adi.getAccounts() instanceof List);
	}
	
	@Test
	public void testGetAccountsReturnsCorrectNumberOfAccounts(){
		assertEquals(adi.getAccounts().size(), 4);
	}
	
	@Test
	public void testGetAccountsReturnsCorrectAccounts() {
		List<Account> accts = adi.getAccounts();
		Account a = accts.get(0);
		Account b = accts.get(1);
		Account c = accts.get(2);
		Account d = accts.get(3);
		assertEquals(a.getId(),1);
		assertEquals(b.getId(),2);
		assertEquals(c.getId(),3);
		assertEquals(d.getId(),4);
	}
	
	
	@Test
	public void testGetAccountsByUserIdReturnsList() {
		List<Account> accts = adi.getAccountsByUserId(1);
		assertEquals(accts.get(0).getId(), 1);
		assertEquals(accts.get(1).getId(), 2);
	}
	
	@Test
	public void testGetLastCreatedAccountReturnsLastAccount() {
		assertEquals(adi.getLastCreatedAccount().getId(), 4);
	}
	
	@Test
	public void testTransferTransfersMoneyToCorrectAccount() {
		Account receiver = adi.getAccountById(1);
		Account sender = adi.getAccountById(2);
		double receiverBalance = receiver.getBalance();
		double senderBalance = sender.getBalance();
		adi.transfer(sender.getId(), receiver.getId(), 20);
		receiver = adi.getAccountById(1);
		sender = adi.getAccountById(2);
		
		assertEquals(senderBalance - 20, sender.getBalance(), 0.0);
		assertEquals(receiverBalance + 20, receiver.getBalance(),0.0);
		
	}
	
	@Test
	public void testUpdateAccount () {
		double amount = 20;
		Account acct = adi.getAccountById(1);
		adi.updateAccount(acct.getId(), amount, acct.getType());
		
		assertEquals(adi.getAccountById(1).getBalance(), amount, 0.0);
	}
	
}

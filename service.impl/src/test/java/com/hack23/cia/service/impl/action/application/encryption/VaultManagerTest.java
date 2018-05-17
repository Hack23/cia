package com.hack23.cia.service.impl.action.application.encryption;

import java.util.UUID;

import org.junit.Test;

import com.hack23.cia.testfoundation.AbstractTest;

public class VaultManagerTest extends AbstractTest {
	
	@Test
	public void encryptDecryptValueSuccessTest() {		
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl();		
		final String password = UUID.randomUUID().toString();
		final String userId = UUID.randomUUID().toString();
		final String someSecureValue = "SomeSecureValue";
		final String encryptValue = vaultManagerImpl.encryptValue(password, userId, someSecureValue);
		final String decryptValue = vaultManagerImpl.decryptValue(password, userId, encryptValue);
		assertEquals(someSecureValue,decryptValue);
	}

	
	@Test
	public void encryptValueFailureTest() {		
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl();		
		final String userId = UUID.randomUUID().toString();
		final String encryptValue = vaultManagerImpl.encryptValue(null, userId, null);
		assertNull(encryptValue);
	}

	@Test
	public void encryptDecryptWrongPasswordFailureTest() {		
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl();		
		final String password = UUID.randomUUID().toString();
		final String userId = UUID.randomUUID().toString();
		final String someSecureValue = "SomeSecureValue";
		final String encryptValue = vaultManagerImpl.encryptValue(password, userId, someSecureValue);
		final String decryptValue = vaultManagerImpl.decryptValue("wrong-password", userId, encryptValue);
		assertNull(decryptValue);
		assertNotEquals(someSecureValue,decryptValue);
	}

	@Test
	public void encryptDecryptWrongUserIdFailureTest() {		
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl();		
		final String password = UUID.randomUUID().toString();
		final String userId = UUID.randomUUID().toString();
		final String someSecureValue = "SomeSecureValue";
		final String encryptValue = vaultManagerImpl.encryptValue(password, userId, someSecureValue);
		final String decryptValue = vaultManagerImpl.decryptValue(password, UUID.randomUUID().toString(), encryptValue);
		assertNull(decryptValue);
		assertNotEquals(someSecureValue,decryptValue);
	}

}

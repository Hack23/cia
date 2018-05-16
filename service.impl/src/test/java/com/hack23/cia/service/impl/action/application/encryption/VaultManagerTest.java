package com.hack23.cia.service.impl.action.application.encryption;

import java.util.UUID;

import org.junit.Test;

import com.hack23.cia.testfoundation.AbstractTest;

public class VaultManagerTest extends AbstractTest {
	
	@Test
	public void getEncryptedValueSuccessTest() {		
		VaultManagerImpl vaultManagerImpl = new VaultManagerImpl();		
		String password = UUID.randomUUID().toString();
		String userId = UUID.randomUUID().toString();
		String someSecureValue = "SomeSecureValue";
		String encryptValue = vaultManagerImpl.encryptValue(password, userId, someSecureValue);
		String decryptValue = vaultManagerImpl.decryptValue(password, userId, encryptValue);
		assertEquals(someSecureValue,decryptValue);
	}
	
}

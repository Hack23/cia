/*
 * Copyright 2010 James Pether Sörling
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *	$Id$
 *  $HeadURL$
*/
package com.hack23.cia.service.impl.action.application.encryption;

import java.util.UUID;

import org.junit.Test;
import org.mockito.Mockito;

import com.hack23.cia.model.internal.application.secure.impl.EncryptedValue;
import com.hack23.cia.model.internal.application.secure.impl.EncryptedValue_;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.data.api.EncryptedValueDAO;
import com.hack23.cia.testfoundation.AbstractTest;

/**
 * The Class VaultManagerTest.
 */
public class VaultManagerTest extends AbstractTest {
	
	/**
	 * Encrypt decrypt value success test.
	 */
	@Test
	public void encryptDecryptValueSuccessTest() {		
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(Mockito.mock(EncryptedValueDAO.class));		
		final String password = UUID.randomUUID().toString();
		final String userId = UUID.randomUUID().toString();
		final String someSecureValue = "SomeSecureValue";
		final String encryptValue = vaultManagerImpl.encryptValue(password, userId, someSecureValue);
		final String decryptValue = vaultManagerImpl.decryptValue(password, userId, encryptValue);
		assertEquals(someSecureValue,decryptValue);
	}

	
	/**
	 * Encrypt value failure test.
	 */
	@Test
	public void encryptValueFailureTest() {		
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(Mockito.mock(EncryptedValueDAO.class));		
		final String userId = UUID.randomUUID().toString();
		final String encryptValue = vaultManagerImpl.encryptValue(null, userId, null);
		assertNull(encryptValue);
	}

	/**
	 * Encrypt decrypt wrong password failure test.
	 */
	@Test
	public void encryptDecryptWrongPasswordFailureTest() {		
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(Mockito.mock(EncryptedValueDAO.class));		
		final String password = UUID.randomUUID().toString();
		final String userId = UUID.randomUUID().toString();
		final String someSecureValue = "SomeSecureValue";
		final String encryptValue = vaultManagerImpl.encryptValue(password, userId, someSecureValue);
		final String decryptValue = vaultManagerImpl.decryptValue("wrong-password", userId, encryptValue);
		assertNull(decryptValue);
		assertNotEquals(someSecureValue,decryptValue);
	}

	/**
	 * Encrypt decrypt wrong user id failure test.
	 */
	@Test
	public void encryptDecryptWrongUserIdFailureTest() {		
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(Mockito.mock(EncryptedValueDAO.class));		
		final String password = UUID.randomUUID().toString();
		final String userId = UUID.randomUUID().toString();
		final String someSecureValue = "SomeSecureValue";
		final String encryptValue = vaultManagerImpl.encryptValue(password, userId, someSecureValue);
		final String decryptValue = vaultManagerImpl.decryptValue(password, UUID.randomUUID().toString(), encryptValue);
		assertNull(decryptValue);
		assertNotEquals(someSecureValue,decryptValue);
	}

	/**
	 * Decrypt wrong null value failure test.
	 */
	@Test
	public void decryptWrongNullValueFailureTest() {		
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(Mockito.mock(EncryptedValueDAO.class));		
		final String password = UUID.randomUUID().toString();
		final String decryptValue = vaultManagerImpl.decryptValue(password, UUID.randomUUID().toString(), null);
		assertNull(decryptValue);
	}

	/**
	 * Gets the encrypted value null failure test.
	 *
	 * @return the encrypted value null failure test
	 */
	@Test
	public void getEncryptedValueNullFailureTest() {		
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(Mockito.mock(EncryptedValueDAO.class));		
		final String password = UUID.randomUUID().toString();
		final String decryptValue = vaultManagerImpl.getEncryptedValue(password, null);
		assertNull(decryptValue);
	}

	/**
	 * Gets the encrypted value no user failure test.
	 *
	 * @return the encrypted value no user failure test
	 */
	@Test
	public void getEncryptedValueNoUserFailureTest() {		
		EncryptedValueDAO encryptedValueDAO = Mockito.mock(EncryptedValueDAO.class);
		Mockito.when(encryptedValueDAO.findFirstByProperty(EncryptedValue_.userId,"-1")).thenReturn(null);
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(encryptedValueDAO);		
		final String password = UUID.randomUUID().toString();
		final String decryptValue = vaultManagerImpl.getEncryptedValue(password, new UserAccount().withUserId("-1"));
		assertNull(decryptValue);
	}

	
	/**
	 * Gets the encrypted value success test.
	 *
	 * @return the encrypted value success test
	 */
	@Test
	public void getEncryptedValueSuccessTest() {		
		final String userId = UUID.randomUUID().toString();
		final String someSecureValue = "SomeSecureValue";
		final String password = UUID.randomUUID().toString();
		EncryptedValueDAO encryptedValueDAO = Mockito.mock(EncryptedValueDAO.class);
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(encryptedValueDAO);		
		final String encryptValue = vaultManagerImpl.encryptValue(password, userId, someSecureValue);
		
		EncryptedValue encryptedValue = new EncryptedValue();
		encryptedValue.setStorage(encryptValue);
		Mockito.when(encryptedValueDAO.findFirstByProperty(EncryptedValue_.userId,userId)).thenReturn(encryptedValue);
			
		final String decryptValue = vaultManagerImpl.getEncryptedValue(password, new UserAccount().withUserId(userId));
		assertEquals(someSecureValue,decryptValue);
	}

}

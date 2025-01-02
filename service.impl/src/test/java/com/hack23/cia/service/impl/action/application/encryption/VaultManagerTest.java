/*
 * Copyright 2010-2025 James Pether Sörling
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
	 * Encrypt value failure all null test.
	 */
	@Test
	public void encryptValueFailureAllNullTest() {
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(Mockito.mock(EncryptedValueDAO.class));
		final String encryptValue = vaultManagerImpl.encryptValue(null, null, null);
		assertNull(encryptValue);
	}

	/**
	 * Encrypt value failure non null userid test.
	 */
	@Test
	public void encryptValueFailureNonNullUseridTest() {
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(Mockito.mock(EncryptedValueDAO.class));
		final String userId = UUID.randomUUID().toString();
		final String encryptValue = vaultManagerImpl.encryptValue(null, userId, null);
		assertNull(encryptValue);
	}

	/**
	 * Encrypt value failure non null password test.
	 */
	@Test
	public void encryptValueFailureNonNullPasswordTest() {
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(Mockito.mock(EncryptedValueDAO.class));
		final String password = UUID.randomUUID().toString();
		final String encryptValue = vaultManagerImpl.encryptValue(password, null, null);
		assertNull(encryptValue);
	}

	/**
	 * Encrypt value failure non null value test.
	 */
	@Test
	public void encryptValueFailureNonNullValueTest() {
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(Mockito.mock(EncryptedValueDAO.class));
		final String value = UUID.randomUUID().toString();
		final String encryptValue = vaultManagerImpl.encryptValue(null, null, value);
		assertNull(encryptValue);
	}


	/**
	 * Encrypt value failure null user id test.
	 */
	@Test
	public void encryptValueFailureNullUserIdTest() {
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(Mockito.mock(EncryptedValueDAO.class));
		final String password = UUID.randomUUID().toString();
		final String value = UUID.randomUUID().toString();
		final String encryptValue = vaultManagerImpl.encryptValue(password, null, value);
		assertNull(encryptValue);
	}

	/**
	 * Encrypt value failure null password test.
	 */
	@Test
	public void encryptValueFailureNullPasswordTest() {
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(Mockito.mock(EncryptedValueDAO.class));
		final String userId = UUID.randomUUID().toString();
		final String value = UUID.randomUUID().toString();
		final String encryptValue = vaultManagerImpl.encryptValue(null, userId, value);
		assertNull(encryptValue);
	}

	/**
	 * Encrypt value failure null value test.
	 */
	@Test
	public void encryptValueFailureNullValueTest() {
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(Mockito.mock(EncryptedValueDAO.class));
		final String userId = UUID.randomUUID().toString();
		final String password = UUID.randomUUID().toString();
		final String encryptValue = vaultManagerImpl.encryptValue(password, userId, null);
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
	 * Decrypt wrong non null password failure test.
	 */
	@Test
	public void decryptWrongNonNullPasswordFailureTest() {
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(Mockito.mock(EncryptedValueDAO.class));
		final String password = UUID.randomUUID().toString();
		final String decryptValue = vaultManagerImpl.decryptValue(password, null, null);
		assertNull(decryptValue);
	}


	/**
	 * Decrypt wrong all null failure test.
	 */
	@Test
	public void decryptWrongAllNullFailureTest() {
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(Mockito.mock(EncryptedValueDAO.class));
		final String decryptValue = vaultManagerImpl.decryptValue(null, null, null);
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
		final EncryptedValueDAO encryptedValueDAO = Mockito.mock(EncryptedValueDAO.class);
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
		final EncryptedValueDAO encryptedValueDAO = Mockito.mock(EncryptedValueDAO.class);
		final VaultManagerImpl vaultManagerImpl = new VaultManagerImpl(encryptedValueDAO);
		final String encryptValue = vaultManagerImpl.encryptValue(password, userId, someSecureValue);

		final EncryptedValue encryptedValue = new EncryptedValue();
		encryptedValue.setStorage(encryptValue);
		Mockito.when(encryptedValueDAO.findFirstByProperty(EncryptedValue_.userId,userId)).thenReturn(encryptedValue);

		final String decryptValue = vaultManagerImpl.getEncryptedValue(password, new UserAccount().withUserId(userId));
		assertEquals(someSecureValue,decryptValue);
	}

}

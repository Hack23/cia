/*
 * Copyright 2010-2024 James Pether Sörling
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
package com.hack23.cia.service.data.impl.util;

import org.junit.Test;
import org.mockito.Mockito;

import com.amazonaws.secretsmanager.caching.SecretCache;
import com.amazonaws.services.secretsmanager.model.DecryptionFailureException;
import com.hack23.cia.testfoundation.AbstractUnitTest;

import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

/**
 * The Class SecretCredentialsManagerImplTest.
 */
public class SecretCredentialsManagerImplTest extends AbstractUnitTest {


	/**
	 * Gets the username exception test.
	 *
	 * @return the username exception test
	 * @throws Exception the exception
	 */
	@Test(expected=RuntimeException.class)
	public void getUsernameExceptionTest() throws Exception {
		new SecretCredentialsManagerImpl(null, "true", null, null) {

			@Override
			protected SecretCache getSecretCache() {
				throw new DecryptionFailureException(null);
			}
		}.getUsername();
	}

	/**
	 * Gets the password success test.
	 *
	 * @return the password success test
	 * @throws Exception the exception
	 */
	@Test
	public void getPasswordSuccessTest() throws Exception {
		final SecretCredentialsManagerImpl secretCredentialsManagerImpl = new SecretCredentialsManagerImpl(null, "true", null, null) {

			@Override
			protected SecretCache getSecretCache() {
				return new SecretCache(Mockito.mock(SecretsManagerClient.class)) {

					@Override
					public String getSecretString(final String secretId) {
						return "{ \"password\" : \"password\", \"username\" : \"username\" }";
					}
				};
			}
		};
		assertNotNull(secretCredentialsManagerImpl.getUsername());
		assertNotNull(secretCredentialsManagerImpl.getPassword());
	}

	/**
	 * Gets the username success test.
	 *
	 * @return the username success test
	 * @throws Exception the exception
	 */
	@Test
	public void getUsernameSuccessTest() throws Exception {
		assertNotNull(new SecretCredentialsManagerImpl(null, "true", null, null) {

			@Override
			protected SecretCache getSecretCache() {
				return new SecretCache(Mockito.mock(SecretsManagerClient.class)) {

					@Override
					public String getSecretString(final String secretId) {
						return "{ \"username\" : \"username\" }";
					}
				};
			}
		}.getUsername());
	}

	/**
	 * Gets the username failure invalid content test.
	 *
	 * @return the username failure invalid content test
	 * @throws Exception the exception
	 */
	@Test(expected=RuntimeException.class)
	public void getUsernameFailureInvalidContentTest() throws Exception {
		assertNotNull(new SecretCredentialsManagerImpl(null, "true", null, null) {

			@Override
			protected SecretCache getSecretCache() {
				return new SecretCache(Mockito.mock(SecretsManagerClient.class)) {

					@Override
					public String getSecretString(final String secretId) {
						return "{ \"wrongusernamefield\" : \"username\" }";
					}
				};
			}
		}.getUsername());
	}

}

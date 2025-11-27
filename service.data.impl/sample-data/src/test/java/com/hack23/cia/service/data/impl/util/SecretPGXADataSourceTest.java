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
package com.hack23.cia.service.data.impl.util;

import java.sql.SQLException;
import java.util.UUID;

import javax.sql.XAConnection;

import org.junit.Test;
import org.mockito.Mockito;

import com.hack23.cia.service.data.impl.util.SecretPGXADataSource.SecretReference;
import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * The Class SecretPGXADataSourceTest.
 */
public class SecretPGXADataSourceTest extends AbstractUnitTest {

	/**
	 * Creates the reference test.
	 */
	@Test
	public void createReferenceTest() {
		final SecretPGXADataSource secretPGXADataSource = new SecretPGXADataSource(Mockito.mock(SecretCredentialsManager.class));
		final SecretReference ref = (SecretReference) secretPGXADataSource.createReference();
		assertNotNull(ref);
	}


	/**
	 * Gets the XA connection test.
	 *
	 * @return the XA connection test
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void getXAConnectionTest() throws SQLException {
		final String secretuser = UUID.randomUUID().toString();
		final String secretpassword = UUID.randomUUID().toString();


		final SecretCredentialsManager secretCredentialsManager = Mockito.mock(SecretCredentialsManager.class);

		Mockito.when(secretCredentialsManager.getUsername()).thenReturn(secretuser);
		Mockito.when(secretCredentialsManager.getPassword()).thenReturn(secretpassword);

		final SecretPGXADataSource secretPGXADataSource = new SecretPGXADataSource(secretCredentialsManager) {

			@Override
			public XAConnection getXAConnection(final String user, final String password) throws SQLException {
				assertEquals(secretuser,user);
				assertEquals(secretpassword,password);
				return Mockito.mock(XAConnection.class);
			}
		};


		final XAConnection xaConnection = secretPGXADataSource.getXAConnection();
		assertNotNull(xaConnection);
	}

}

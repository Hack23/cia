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

import javax.naming.StringRefAddr;

import org.junit.Test;
import org.mockito.Mockito;

import com.hack23.cia.service.data.impl.util.SecretPGXADataSource.SecretReference;
import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * The Class SecretPGXADataSourceFactoryTest.
 */
public class SecretPGXADataSourceFactoryTest extends AbstractUnitTest {

	/**
	 * Gets the object instance test.
	 *
	 * @return the object instance test
	 * @throws Exception the exception
	 */
	@Test
	public void getObjectInstanceTest() throws Exception {
		final SecretPGXADataSource secretPGXADataSource = new SecretPGXADataSource(Mockito.mock(SecretCredentialsManager.class));
		final SecretReference ref = (SecretReference) secretPGXADataSource.createReference();
		ref.add(new StringRefAddr("serverName","192.168.1.1"));

		assertNotNull(new SecretPGXADataSourceFactory().getObjectInstance(ref, null,null, null));
	}

}

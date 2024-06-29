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
package com.hack23.cia.service.data.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.data.api.PersonDataDAO;

/**
 * The Class PersonDataDAOITest.
 */
public final class PersonDataDAOITest extends AbstractServiceDataFunctionalIntegrationTest {

	/** The person data dao. */
	@Autowired
	private PersonDataDAO personDataDAO;

	/**
	 * Test get size.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetSize() throws Exception {
		assertTrue("Expect some persons in database",personDataDAO.getSize() >= 0);
	}

}

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
package com.hack23.cia.testfoundation;

import java.sql.Connection;

import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.Test;

/**
 * The Class FunctionalIntegrationTest.
 */
public class FunctionalIntegrationTest extends AbstractFunctionalIntegrationTest {

	/** (non-Javadoc)
	 * @see com.hack23.cia.testfoundation.AbstractFunctionalIntegrationTest#getDatabaseConnection()
	 */
	@Override
	protected Connection getDatabaseConnection() throws Exception {
		final JdbcConnectionPool cp = JdbcConnectionPool.
			    create("jdbc:h2:mem:test", "sa", "sa");
			return cp.getConnection();

	}

	/**
	 * Gets the database connection test.
	 *
	 * @return the database connection test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getDatabaseConnectionTest() throws Exception {
		assertNotNull(getDatabaseConnection());
		assertNotNull(getDataSet());
		assertNotNull(getQueryDataset());
	}

}

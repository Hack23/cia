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
 * The Class DataSetConnectionFactoryIntegrationTest.
 */
public final class DataSetConnectionFactoryIntegrationTest extends AbstractFunctionalIntegrationTest {

	/**
	 * Gets the database connection.
	 *
	 * @return the database connection
	 * @throws Exception
	 *             the exception
	 */
	private static Connection getDatabaseConnection() throws Exception {
		final JdbcConnectionPool cp = JdbcConnectionPool.
			    create("jdbc:h2:mem:test", "sa", "sa");
			return cp.getConnection();
	}

	/**
	 * Gets the query dataset test.
	 *
	 * @return the query dataset test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getQueryDatasetTest() throws Exception {
		assertNotNull(getDatabaseConnection());
		assertNotNull(new DataSetConnectionFactory().getQueryDataset(getDatabaseConnection()));
	}

	/**
	 * Gets the data set test.
	 *
	 * @return the data set test
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getDataSetTest() throws Exception {
		assertNotNull(getDatabaseConnection());
		assertNotNull(new DataSetConnectionFactory().getQueryDataset(getDatabaseConnection()));
	}

}

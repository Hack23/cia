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

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.ITableFilter;

/**
 * The Class AbstractFunctionalIntegrationTest.
 */
public abstract class AbstractFunctionalIntegrationTest extends AbstractTest {

	/**
	 * Instantiates a new abstract functional integration test.
	 */
	protected AbstractFunctionalIntegrationTest() {
		super();

	}


	/**
	 * Gets the database connection.
	 *
	 * @return the database connection
	 * @throws Exception
	 *             the exception
	 */
	protected abstract Connection getDatabaseConnection() throws Exception;

	/**
	 * Gets the data set.
	 *
	 * @return the data set
	 * @throws Exception
	 *             the exception
	 */
	protected final IDataSet getDataSet() throws Exception {
		final DatabaseConnection databaseConnection = new DatabaseConnection(
				getDatabaseConnection());
		final ITableFilter filter = new DatabaseSequenceFilter(
				databaseConnection);
		return new FilteredDataSet(filter, databaseConnection.createDataSet());
	}

	/**
	 * Gets the query dataset.
	 *
	 * @return the query dataset
	 * @throws Exception
	 *             the exception
	 */
	protected final QueryDataSet getQueryDataset() throws Exception {
		return new QueryDataSet(new DatabaseConnection(getDatabaseConnection()));
	}

}

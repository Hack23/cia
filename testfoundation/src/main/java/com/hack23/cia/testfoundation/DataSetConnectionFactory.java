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
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.ITableFilter;

/**
 * A factory for creating DataSetConnection objects.
 */
public final class DataSetConnectionFactory {

	/**
	 * Instantiates a new data set connection factory.
	 */
	public DataSetConnectionFactory() {
		super();
	}

	/**
	 * Gets the data set.
	 *
	 * @param connection
	 *            the connection
	 * @return the data set
	 * @throws DatasetFactoryException
	 *             the dataset factory exception
	 */
	public IDataSet getDataSet(final Connection connection) throws DatasetFactoryException {
		DatabaseConnection databaseConnection;
		try {
			databaseConnection = new DatabaseConnection(connection);
			final ITableFilter filter = new DatabaseSequenceFilter(databaseConnection);
			return new FilteredDataSet(filter, databaseConnection.createDataSet());
		} catch (DatabaseUnitException | SQLException e) {
			throw new DatasetFactoryException(e);
		}
	}

	
	/**
	 * Gets the query dataset.
	 *
	 * @param connection
	 *            the connection
	 * @return the query dataset
	 * @throws DatasetFactoryException
	 *             the dataset factory exception
	 */
	public QueryDataSet getQueryDataset(final Connection connection) throws DatasetFactoryException {
		try {
			return new QueryDataSet(new DatabaseConnection(connection));
		} catch (DatabaseUnitException e) {
			throw new DatasetFactoryException(e);
		}
	}

}

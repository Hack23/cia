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
 *	$Id: AbstractFunctionalIntegrationTest.java 6091 2015-06-06 07:08:05Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/testfoundation/src/main/java/com/hack23/cia/testfoundation/AbstractFunctionalIntegrationTest.java $
 */

package com.hack23.cia.testfoundation;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.w3c.dom.Document;

/**
 * The Class AbstractFunctionalIntegrationTest.
 */
public abstract class AbstractFunctionalIntegrationTest extends AbstractTest {

	/** The Constant FUNCTIONALINTEGRATIONTEST_DATABASE_SCENARIODATA. */
	protected static final String FUNCTIONALINTEGRATIONTEST_DATABASE_SCENARIODATA = "src/test/resources/functionalintegrationtest/database/scenariodata/"; //$NON-NLS-1$

	/** The Constant IGNORE_ATTRIBUTES. */
	private static final String[] IGNORE_ATTRIBUTES = { "id" }; //$NON-NLS-1$

	/** The document builder factory. */
	protected final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
			.newInstance();

	/** The ignore attributes. */
	protected List<String> ignoreAttributes = Arrays.asList(IGNORE_ATTRIBUTES);

	/** The test scenario name. */
	protected String testScenarioName;

	/**
	 * Instantiates a new abstract functional integration test.
	 */
	protected AbstractFunctionalIntegrationTest() {
		super();
		
	}

	/**
	 * Assert xml ignore time attributes.
	 *
	 * @param expectedDataset
	 *            the expected dataset
	 * @param actualDataset
	 *            the actual dataset
	 */
	@SuppressWarnings("unchecked")
	protected final void assertXmlIgnoreTimeAttributes(
			final Document expectedDataset, final Document actualDataset) {
		final DetailedDiff detailedDiff = new DetailedDiff(new Diff(
				expectedDataset, actualDataset));
		final List<Difference> differences = detailedDiff.getAllDifferences();
		for (final Difference difference : differences) {
			if (!ignoreAttributes.contains(difference.getControlNodeDetail()
					.getNode().getNodeName())) {
				fail(difference.toString());
			}
		}
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

	/**
	 * Gets the xml document.
	 *
	 * @param dataset
	 *            the dataset
	 * @return the xml document
	 * @throws Exception
	 *             the exception
	 */
	protected final Document getXmlDocument(final IDataSet dataset)
			throws Exception {
		final File tempfile = File.createTempFile("getXmlDocument", "xml");
		FlatXmlDataSet.write(dataset, new FileOutputStream(tempfile));
		return loadXml(tempfile);
	}

	/**
	 * Load xml.
	 *
	 * @param file
	 *            the file
	 * @return the document
	 * @throws Exception
	 *             the exception
	 */
	private Document loadXml(final File file) throws Exception {		
		documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		final DocumentBuilder builder = documentBuilderFactory
				.newDocumentBuilder();	
		
		return builder.parse(file);
	}

	/**
	 * Load xml.
	 *
	 * @param fileName
	 *            the file name
	 * @return the document
	 * @throws Exception
	 *             the exception
	 */
	protected final Document loadXml(final String fileName) throws Exception {
		final File file = new File(
				FUNCTIONALINTEGRATIONTEST_DATABASE_SCENARIODATA + fileName);
		return loadXml(file);
	}

	/**
	 * Load xml dataset.
	 *
	 * @param file
	 *            the file
	 * @return the i data set
	 * @throws Exception
	 *             the exception
	 */
	protected final IDataSet loadXmlDataset(final File file) throws Exception {
		return new FlatXmlDataSetBuilder().build(file);
	}

	/**
	 * Sets the up database pre condition.
	 *
	 * @param file
	 *            the new up database pre condition
	 * @throws Exception
	 *             the exception
	 */
	protected final void setupDatabasePreCondition(final File file)
			throws Exception {
		final DatabaseConnection connection = new DatabaseConnection(
				getDatabaseConnection());

		try {
			final Statement turnOffConstraints = connection.getConnection()
					.createStatement();
			try {
				turnOffConstraints.execute("SET CONSTRAINTS ALL DEFERRED"); //$NON-NLS-1$
			} finally {
				turnOffConstraints.close();
			}
			
			DatabaseOperation.DELETE_ALL.execute(connection,
					loadXmlDataset(new File(
							FUNCTIONALINTEGRATIONTEST_DATABASE_SCENARIODATA
							+ "empty-content.xml"))); //$NON-NLS-1$
						
			final IDataSet dataset = loadXmlDataset(file);
			DatabaseOperation.INSERT.execute(connection, dataset);

		} finally {
			connection.close();
		}
	}

	/**
	 * Verify database post condition.
	 *
	 * @param dataSet
	 *            the data set
	 * @throws Exception
	 *             the exception
	 */
	protected final void verifyDatabasePostCondition(final IDataSet dataSet)
			throws Exception {
		final Document actualDataset = getXmlDocument(dataSet);
		final Document expectedDataset = loadXml(testScenarioName
				+ "-expected-results.xml"); //$NON-NLS-1$
		assertXmlIgnoreTimeAttributes(expectedDataset, actualDataset);
	}

	/**
	 * Write import xml.
	 *
	 * @param dataset
	 *            the dataset
	 * @throws Exception
	 *             the exception
	 */
	protected final void writeImportXml(final IDataSet dataset) throws Exception {
		final File file = new File("src/main/data/import/import-data.xml"); //$NON-NLS-1$
		XmlDataSet.write(dataset, new FileOutputStream(file));
	}

	/**
	 * Write xml.
	 *
	 * @param dataset
	 *            the dataset
	 * @throws Exception
	 *             the exception
	 */
	protected final void writeXml(final IDataSet dataset) throws Exception {
		final File file = new File(
				FUNCTIONALINTEGRATIONTEST_DATABASE_SCENARIODATA
				+ testScenarioName + "-expected-results.xml"); //$NON-NLS-1$
		FlatXmlDataSet.write(dataset, new FileOutputStream(file));
	}
}

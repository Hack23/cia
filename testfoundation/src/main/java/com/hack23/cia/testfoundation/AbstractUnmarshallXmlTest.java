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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;

import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.Unmarshaller;

/**
 * The Class AbstractUnmarshallXmlTest.
 *
 * @param <T>
 *            the generic type
 */
public abstract class AbstractUnmarshallXmlTest<T> extends AbstractFunctionalIntegrationTest {

	/**
	 * Instantiates a new abstract unmarshall xml test.
	 */
	public AbstractUnmarshallXmlTest() {
		super();
	}

	@Override
	protected Connection getDatabaseConnection() throws Exception {
		return null;
	}

	/**
	 * Unmarshall xml.
	 *
	 * @param unmarshaller
	 *            the unmarshaller
	 * @param filename
	 *            the filename
	 * @return the t
	 * @throws Exception
	 *             the exception
	 */
	protected final T unmarshallXml(final Unmarshaller unmarshaller,final String filename) throws Exception {
		final BufferedReader inputStream= new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename))));

		return (T) unmarshaller
				.unmarshal(new StreamSource(inputStream));
	}
}

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
package com.hack23.cia.model.common.impl.xml;

import java.util.Date;

import org.junit.Test;

import com.hack23.cia.testfoundation.AbstractTest;

/**
 * The Class XmlDateTimeTypeAdapterTest.
 */
public final class XmlDateTimeTypeAdapterTest extends AbstractTest {


	/**
	 * Parses the date time test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void parseDateTimeTest() throws Exception {
		final String printTime = "2015-05-21T00:33:27.560+02:00";
		final XmlDateTimeTypeAdapter xmlDateTimeTypeAdapter = new XmlDateTimeTypeAdapter();
		final Date simpleSwedishDateForm = xmlDateTimeTypeAdapter.unmarshal(printTime);
		assertEquals("Expect specified time",printTime,xmlDateTimeTypeAdapter.marshal(simpleSwedishDateForm));

		assertNull("Expect null",xmlDateTimeTypeAdapter.marshal(null));
		assertNull("Expect null",xmlDateTimeTypeAdapter.unmarshal(null));

	}

}

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
package com.hack23.cia.model.common.impl.xml;

import java.util.Date;

import org.junit.Test;

import com.hack23.cia.testfoundation.AbstractTest;

/**
 * The Class XmlDateTypeAdapterTest.
 */
public final class XmlDateTypeAdapterTest extends AbstractTest {

	/**
	 * Parses the date test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void parseDateTest() throws Exception {

		final XmlDateTypeAdapter xmlDateTypeAdapter = new XmlDateTypeAdapter();
		final Date simpleSwedishDateForm = xmlDateTypeAdapter.unmarshal("2015-05-20");
		assertNotNull("Expect a result",simpleSwedishDateForm);
		assertEquals("Expect specified date","2015-05-20+02:00",xmlDateTypeAdapter.marshal(simpleSwedishDateForm));

		final Date simpleSwedishDateTimeFormat = xmlDateTypeAdapter.unmarshal("2001-01-01 00:00:00");
		assertNotNull("Expect a result",simpleSwedishDateTimeFormat);
		assertEquals("Expect specified date","2001-01-01+01:00",xmlDateTypeAdapter.marshal(simpleSwedishDateTimeFormat));


		final Date validDateForm = xmlDateTypeAdapter.unmarshal("2001-01-01+01:00");
		assertNotNull("Expect specified date",validDateForm);
		assertEquals("2001-01-01+01:00",xmlDateTypeAdapter.marshal(validDateForm));

		assertNull("Expect null",xmlDateTypeAdapter.marshal(null));
		assertNull("Expect null",xmlDateTypeAdapter.unmarshal(null));

		assertNull("Expect null",xmlDateTypeAdapter.unmarshal("wron-gd-at"));

		assertNull("Expect null",xmlDateTypeAdapter.unmarshal("wrong"));


		assertNull("Expect null",xmlDateTypeAdapter.unmarshal("wron-gd-at+e1:00"));

	}


}

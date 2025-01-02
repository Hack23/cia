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
 * The Class XmlTimeTypeAdapterTest.
 */
public final class XmlTimeTypeAdapterTest extends AbstractTest {


	/**
	 * Parses the time test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void parseTimeTest() {
		final String printTime = "00:27:55.916+01:00";
		final XmlTimeTypeAdapter xmlTimeTypeAdapter = new XmlTimeTypeAdapter();
		final Date simpleSwedishDateForm = xmlTimeTypeAdapter.unmarshal(printTime);
		assertEquals("Expect specified time",printTime,xmlTimeTypeAdapter.marshal(simpleSwedishDateForm));

		assertNull("Expect null",xmlTimeTypeAdapter.unmarshal(null));
		assertNull("Expect null",xmlTimeTypeAdapter.marshal(null));

	}

}

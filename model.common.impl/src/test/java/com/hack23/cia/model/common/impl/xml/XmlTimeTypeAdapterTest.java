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
package com.hack23.cia.model.common.impl.xml;

import java.util.Date;

import org.junit.Test;

import com.hack23.cia.model.common.impl.xml.XmlTimeTypeAdapter;
import com.hack23.cia.testfoundation.AbstractTest;

/**
 * The Class XmlTimeTypeAdapterTest.
 */
public final class XmlTimeTypeAdapterTest extends AbstractTest {

//
//	/**
//	 * Parses the date test.
//	 *
//	 * @throws Exception
//	 *             the exception
//	 */
//	@Test
//	public void parseDateTest() throws Exception {
//
//		Date simpleSwedishDateForm = XmlTimeTypeAdapter.parseDate("2015-05-20");
//		assertNotNull("Expect a result",simpleSwedishDateForm);
//		assertEquals("Expect specified date","2015-05-20+02:00",XmlTimeTypeAdapter.printDate(simpleSwedishDateForm));
//
//		Date simpleSwedishDateTimeFormat = XmlTimeTypeAdapter.parseDate("2001-01-01 00:00:00");
//		assertNotNull("Expect a result",simpleSwedishDateTimeFormat);
//		assertEquals("Expect specified date","2001-01-01+01:00",XmlTimeTypeAdapter.printDate(simpleSwedishDateTimeFormat));
//
//
//		Date validDateForm = XmlTimeTypeAdapter.parseDate("2001-01-01+01:00");
//		assertNotNull("Expect specified date",validDateForm);
//		assertEquals("2001-01-01+01:00",XmlTimeTypeAdapter.printDate(validDateForm));
//
//		assertNull("Expect null",XmlTimeTypeAdapter.printDate(null));
//		assertNull("Expect null",XmlTimeTypeAdapter.parseDate(null));
//
//	}
//
//
//	/**
//	 * Parses the time test.
//	 *
//	 * @throws Exception
//	 *             the exception
//	 */
//	@Test
//	public void parseTimeTest() throws Exception {
//
//		String printTime = "00:27:55.916+01:00";
//		Date simpleSwedishDateForm = XmlTimeTypeAdapter.parseTime(printTime);
//		assertEquals("Expect specified time",printTime,XmlTimeTypeAdapter.printTime(simpleSwedishDateForm));
//
//		assertNull("Expect null",XmlTimeTypeAdapter.printTime(null));
//		assertNull("Expect null",XmlTimeTypeAdapter.parseTime(null));
//
//	}
//
//	/**
//	 * Parses the date time test.
//	 *
//	 * @throws Exception
//	 *             the exception
//	 */
//	@Test
//	public void parseDateTimeTest() throws Exception {
//		String printTime = "2015-05-21T00:33:27.560+02:00";
//		Date simpleSwedishDateForm = XmlTimeTypeAdapter.parseDateTime(printTime);
//		assertEquals("Expect specified time",printTime,XmlTimeTypeAdapter.printDateTime(simpleSwedishDateForm));
//
//		assertNull("Expect null",XmlTimeTypeAdapter.printDateTime(null));
//		assertNull("Expect null",XmlTimeTypeAdapter.parseDateTime(null));
//
//	}
//
}

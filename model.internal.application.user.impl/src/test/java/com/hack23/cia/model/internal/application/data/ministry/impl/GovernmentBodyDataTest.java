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
package com.hack23.cia.model.internal.application.data.ministry.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * The Class GovernmentBodyDataTest.
 */
public class GovernmentBodyDataTest {

	/**
	 * Test constructor with all parameters.
	 */
	@Test
	public void testConstructorWithAllParameters() {
		final GovernmentBodyData data = new GovernmentBodyData(
			2023, 
			"Test Ministry", 
			1, 
			"GOV001", 
			"M1", 
			"Ministry of Testing",
			"555123-4567", 
			100, 
			95, 
			"VAT123", 
			"Test comment"
		);

		assertEquals(2023, data.getYear());
		assertEquals("Test Ministry", data.getName());
		assertEquals(1, data.getConsecutiveNumber());
		assertEquals("GOV001", data.getGovernmentBodyId());
		assertEquals("M1", data.getmCode());
		assertEquals("Ministry of Testing", data.getMinistry());
		assertEquals("555123-4567", data.getOrgNumber());
		assertEquals(100, data.getHeadCount());
		assertEquals(95, data.getAnnualWorkHeadCount());
		assertEquals("VAT123", data.getVat());
		assertEquals("Test comment", data.getComment());
	}

	/**
	 * Test default constructor.
	 */
	@Test
	public void testDefaultConstructor() {
		final GovernmentBodyData data = new GovernmentBodyData();
		assertNotNull(data);
		assertNull(data.getId());
		assertNull(data.getYear());
		assertNull(data.getName());
	}

	/**
	 * Test setters and getters.
	 */
	@Test
	public void testSettersAndGetters() {
		final GovernmentBodyData data = new GovernmentBodyData();
		
		data.setId(1L);
		data.setYear(2024);
		data.setName("Test Body");
		data.setConsecutiveNumber(2);
		data.setGovernmentBodyId("GOV002");
		data.setmCode("M2");
		data.setMinistry("Test Ministry");
		data.setOrgNumber("555987-6543");
		data.setHeadCount(200);
		data.setAnnualWorkHeadCount(190);
		data.setVat("VAT456");
		data.setComment("Another test");

		assertEquals(1L, data.getId());
		assertEquals(2024, data.getYear());
		assertEquals("Test Body", data.getName());
		assertEquals(2, data.getConsecutiveNumber());
		assertEquals("GOV002", data.getGovernmentBodyId());
		assertEquals("M2", data.getmCode());
		assertEquals("Test Ministry", data.getMinistry());
		assertEquals("555987-6543", data.getOrgNumber());
		assertEquals(200, data.getHeadCount());
		assertEquals(190, data.getAnnualWorkHeadCount());
		assertEquals("VAT456", data.getVat());
		assertEquals("Another test", data.getComment());
	}

	/**
	 * Test equals and hash code.
	 */
	@Test
	public void testEqualsAndHashCode() {
		final GovernmentBodyData data1 = new GovernmentBodyData(
			2023, "Test Body", 1, "GOV001", "M1", "Ministry", 
			"555123-4567", 100, 95, "VAT123", "Comment"
		);
		data1.setId(1L);

		final GovernmentBodyData data2 = new GovernmentBodyData(
			2023, "Test Body", 1, "GOV001", "M1", "Ministry", 
			"555123-4567", 100, 95, "VAT123", "Comment"
		);
		data2.setId(1L);

		final GovernmentBodyData data3 = new GovernmentBodyData(
			2024, "Different Body", 2, "GOV002", "M2", "Other Ministry", 
			"555987-6543", 200, 190, "VAT456", "Other comment"
		);
		data3.setId(2L);

		assertEquals(data1, data2);
		assertEquals(data1.hashCode(), data2.hashCode());
		assertNotEquals(data1, data3);
		assertNotEquals(data1.hashCode(), data3.hashCode());
	}

	/**
	 * Test to string.
	 */
	@Test
	public void testToString() {
		final GovernmentBodyData data = new GovernmentBodyData(
			2023, "Test Body", 1, "GOV001", "M1", "Ministry", 
			"555123-4567", 100, 95, "VAT123", "Comment"
		);
		data.setId(1L);

		final String toString = data.toString();
		assertNotNull(toString);
		// Apache Commons Lang reflectionToString format
		assertEquals(true, toString.contains("id=1"));
		assertEquals(true, toString.contains("year=2023"));
		assertEquals(true, toString.contains("name=Test Body"));
	}
}

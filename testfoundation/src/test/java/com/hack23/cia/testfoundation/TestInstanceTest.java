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
package com.hack23.cia.testfoundation;

import org.junit.Test;

/**
 * The Class TestInstanceTest.
 */
public final class TestInstanceTest extends AbstractUnitTest {

	/** The order. */
	int order;

	/**
	 * Test instance example test.
	 */
	@Test
	public void testInstanceExampleTest() {
		new AbstractTestInstance() {

			@Override
			protected void setupExpectations() {
				order++;
				assertEquals(1,order);
			}

			@Override
			protected void run() {
				order++;
				assertEquals(2,order);
			}

			@Override
			protected void assertPostcondition() {
				order++;
				assertEquals(3,order);
			}
		}.executeTestPhases();
		assertEquals(3,order);
	}
	
	@Test
	public void checkAllClassesInPackageTest() throws Exception {
		assertTrue(checkAllClassesInPackage("com.hack23.cia.testfoundation.pojo"));
		assertTrue(checkAllDtoClassesInPackage("com.hack23.cia.testfoundation.pojo"));
	}


}

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
package com.hack23.cia.testfoundation;

import java.util.Arrays;
import java.util.Collection;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized.Parameters;

/**
 * The Class SystemIntegrationTest.
 */
@RunWith(Parallelized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public final class SystemIntegrationTest extends AbstractSystemIntegrationTest {

	/** The browser. */
	private final String browser;

	/**
	 * Instantiates a new system integration test.
	 *
	 * @param browser
	 *            the browser
	 */
	public SystemIntegrationTest(final String browser) {
		super();
		this.browser = browser;
	}

	/**
	 * Browsers strings.
	 *
	 * @return the collection
	 */
	@Parameters(name = "SiteTest{index}: browser({0})")
	public static Collection<String[]> browsersStrings() {
		return Arrays.asList(new String[][] { { "firefox" } });	}

	/**
	 * Example test.
	 */
	@Test
	public void exampleTest() {
		assertEquals("firefox", browser);
	}


}

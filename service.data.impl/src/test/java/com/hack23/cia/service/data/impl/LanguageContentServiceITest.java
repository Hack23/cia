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
package com.hack23.cia.service.data.impl;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.service.data.api.LanguageContentService;

/**
 * The Class LanguageContentServiceITest.
 */
public final class LanguageContentServiceITest extends AbstractServiceDataFunctionalIntegrationTest {

	/** The application configuration service. */
	@Autowired
	private LanguageContentService languageContentService;

	/**
	 * Check value or load default property already exist same value test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getLanguageResourceAlreadyExistGetConfiguredValueTest() throws Exception {

		final String defaultEnglishValue = "defaultValue";
		final String key = UUID.randomUUID().toString();
		final String languageResource = languageContentService.getLanguageResource(key, "test","en",
				defaultEnglishValue);

		assertEquals("Expect to get default english value",defaultEnglishValue, languageResource);

		final String languageResourceAfterOtherValueCreated = languageContentService.getLanguageResource(key, "test","en",
				"Some strange value");

		assertEquals("Expect to get default english value",defaultEnglishValue, languageResourceAfterOtherValueCreated);
	}

	/**
	 * Check value or load default no property exist test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void getLanguageResourceNoValueExistTest() throws Exception {

		final String defaultEnglishValue = "defaultValue";
		final String key = UUID.randomUUID().toString();
		final String languageResource = languageContentService.getLanguageResource(key, "test","en",
				defaultEnglishValue);

		assertEquals("Expect to get default english value",defaultEnglishValue, languageResource);

	}

}

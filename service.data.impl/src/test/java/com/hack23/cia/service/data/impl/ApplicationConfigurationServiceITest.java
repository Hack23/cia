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

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.system.impl.ApplicationConfiguration;
import com.hack23.cia.model.internal.application.system.impl.ConfigurationGroup;
import com.hack23.cia.service.data.api.ApplicationConfigurationService;

/**
 * The Class ApplicationConfigurationServiceITest.
 */
public final class ApplicationConfigurationServiceITest extends AbstractServiceDataFunctionalIntegrationTest {

	/** The application configuration service. */
	@Autowired
	private ApplicationConfigurationService applicationConfigurationService;

	/**
	 * Check value or load default no property exist test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void checkValueOrLoadDefaultNoPropertyExistTest() throws Exception {

		final ApplicationConfiguration checkValueOrLoadDefault = applicationConfigurationService.checkValueOrLoadDefault(
				"checkValueOrLoadDefaultNoPropertyExistTest property",
				"checkValueOrLoadDefaultNoPropertyExistTest should be set to true/false",
				ConfigurationGroup.AUTHORIZATION, ApplicationConfigurationServiceITest.class.getSimpleName(),
				"ApplicationConfigurationService ITest", "FunctionalIntegrationTest",
				"checkValueOrLoadDefaultNoPropertyExistTest", "true");

		assertEquals("Expect the config to be set to","true", checkValueOrLoadDefault.getPropertyValue());

	}

	/**
	 * Check value or load default property already exist same value test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void checkValueOrLoadDefaultPropertyAlreadyExistSameValueTest() throws Exception {

		final ApplicationConfiguration checkValueOrLoadDefault = applicationConfigurationService.checkValueOrLoadDefault(
				"checkValueOrLoadDefaultPropertyAlreadyExistSameValueTest property",
				"checkValueOrLoadDefaultPropertyAlreadyExistSameValueTest should be set to true/false",
				ConfigurationGroup.AUTHORIZATION, ApplicationConfigurationServiceITest.class.getSimpleName(),
				"ApplicationConfigurationService ITest", "FunctionalIntegrationTest",
				"checkValueOrLoadDefaultPropertyAlreadyExistSameValueTest", "true");

		final ApplicationConfiguration checkValueOrLoadDefault2 = applicationConfigurationService.checkValueOrLoadDefault(
				"checkValueOrLoadDefaultPropertyAlreadyExistSameValueTest property",
				"checkValueOrLoadDefaultPropertyAlreadyExistSameValueTest should be set to true/false",
				ConfigurationGroup.AUTHORIZATION, ApplicationConfigurationServiceITest.class.getSimpleName(),
				"ApplicationConfigurationService ITest", "FunctionalIntegrationTest",
				"checkValueOrLoadDefaultPropertyAlreadyExistSameValueTest", "true");

		assertEquals("Expect the config to use the first set value",checkValueOrLoadDefault, checkValueOrLoadDefault2);
	}

}

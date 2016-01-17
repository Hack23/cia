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
package com.hack23.cia.service.impl;

import java.sql.Connection;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hack23.cia.testfoundation.AbstractFunctionalIntegrationTest;

/**
 * The Class AbstractServiceFunctionalIntegrationTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/application-context-service.xml",
		"classpath:/META-INF/cia-service-component-agent.xml",
		"classpath:META-INF/cia-jms-broker.xml",
		"classpath:/META-INF/application-context-service-data.xml",
		"classpath:META-INF/cia-service-external-common.xml",
		"classpath:META-INF/cia-service-external-riksdagen.xml",
		"classpath:META-INF/cia-service-external-worldbank.xml",
		"classpath:META-INF/cia-service-external-vdem.xml",
		"classpath:META-INF/cia-service-external-val.xml",
"classpath:/META-INF/cia-test-context.xml" })
public abstract class AbstractServiceFunctionalIntegrationTest extends AbstractFunctionalIntegrationTest {

	/**
	 * Instantiates a new abstract service functional integration test.
	 */
	public AbstractServiceFunctionalIntegrationTest() {
		super();
		System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","com.hack23.cia,java.util,java.lang,java.math,org.apache");
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.testfoundation.AbstractFunctionalIntegrationTest#getDatabaseConnection()
	 */
	@Override
	protected Connection getDatabaseConnection() throws Exception {
		return null;
	}
}

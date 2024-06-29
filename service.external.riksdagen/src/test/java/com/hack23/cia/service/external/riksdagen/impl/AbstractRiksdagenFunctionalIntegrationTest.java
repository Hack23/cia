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
package com.hack23.cia.service.external.riksdagen.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hack23.cia.service.external.common.api.XmlAgent;
import com.hack23.cia.service.external.common.api.XmlAgentException;
import com.hack23.cia.testfoundation.AbstractFunctionalIntegrationTest;

/**
 * The Class AbstractRiksdagenFunctionalIntegrationTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/cia-service-external-riksdagen.xml",
		"classpath:META-INF/cia-service-external-common.xml", "classpath:/META-INF/cia-test-context.xml" })
public abstract class AbstractRiksdagenFunctionalIntegrationTest extends AbstractFunctionalIntegrationTest {

	/**
	 * Instantiates a new abstract riksdagen functional integration test.
	 */
	public AbstractRiksdagenFunctionalIntegrationTest() {
		super();
	}

	/**
	 * Creates the mock xml agent throws exception.
	 *
	 * @return the xml agent
	 * @throws XmlAgentException
	 *             the xml agent exception
	 */
	protected final XmlAgent createMockXmlAgentThrowsException() throws XmlAgentException {
		final XmlAgent xmlAgent = mock(XmlAgent.class);
		Mockito.when(xmlAgent.unmarshallXml(isNull(),any(String.class),any(String.class),isNull(),isNull())).thenThrow(new XmlAgentException(new RuntimeException()));
		Mockito.when(xmlAgent.retriveContent(any(String.class))).thenThrow(new XmlAgentException(new RuntimeException()));
		return xmlAgent;
	}


}

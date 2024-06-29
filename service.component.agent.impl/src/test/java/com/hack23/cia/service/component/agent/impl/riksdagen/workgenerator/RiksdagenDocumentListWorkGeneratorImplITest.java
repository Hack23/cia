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
package com.hack23.cia.service.component.agent.impl.riksdagen.workgenerator;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.Serializable;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.transaction.Transactional;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.util.ReflectionTestUtils;

import com.hack23.cia.service.component.agent.impl.AbstractServiceComponentAgentFunctionalIntegrationTest;
import com.hack23.cia.service.component.agent.impl.common.jms.JmsSender;

/**
 * The Class RiksdagenDocumentListWorkGeneratorImplITest.
 */
@Transactional
public class RiksdagenDocumentListWorkGeneratorImplITest extends AbstractServiceComponentAgentFunctionalIntegrationTest {

	/** The riksdagen data sources work generator. */
	@Autowired
	@Qualifier("RiksdagenDocumentListWorkGeneratorImpl")
	private RiksdagenDataSourcesWorkGenerator riksdagenDataSourcesWorkGenerator;

	/**
	 * Generate work orders success test.
	 *
	 * @throws JMSException
	 *             the JMS exception
	 */
	@Test
	public void generateWorkOrdersSuccessTest() throws JMSException {
		riksdagenDataSourcesWorkGenerator.generateWorkOrders();
		final JmsSender jmsSenderMock = mock(JmsSender.class);
        ReflectionTestUtils.setField(riksdagenDataSourcesWorkGenerator, "jmsSender", jmsSenderMock);

        riksdagenDataSourcesWorkGenerator.generateWorkOrders();

		final ArgumentCaptor<Destination> destCaptor = ArgumentCaptor.forClass(Destination.class);

		final ArgumentCaptor<Serializable> stringCaptor = ArgumentCaptor.forClass(Serializable.class);

		verify(jmsSenderMock, atLeastOnce()).send(destCaptor.capture(),stringCaptor.capture());

		final List<Serializable> capturedStrings = stringCaptor.getAllValues();
		final List<Destination> capturedDestinations = destCaptor.getAllValues();

		assertNotNull(capturedStrings);
		assertNotNull(capturedDestinations);
	}

}

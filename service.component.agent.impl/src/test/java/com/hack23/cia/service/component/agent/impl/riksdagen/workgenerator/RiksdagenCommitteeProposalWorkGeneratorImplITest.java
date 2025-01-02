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
package com.hack23.cia.service.component.agent.impl.riksdagen.workgenerator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
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
import com.hack23.cia.service.component.agent.impl.riksdagen.workgenerator.data.RiksdagenImportService;

/**
 * The Class RiksdagenCommitteeProposalWorkGeneratorImplITest.
 */
@Transactional
public class RiksdagenCommitteeProposalWorkGeneratorImplITest
		extends AbstractServiceComponentAgentFunctionalIntegrationTest {

	/** The riksdagen data sources work generator. */
	@Autowired
	@Qualifier("RiksdagenCommitteeProposalWorkGeneratorImpl")
	private RiksdagenDataSourcesWorkGenerator riksdagenDataSourcesWorkGenerator;

	/**
	 * Generate work orders when already exist success test.
	 *
	 * @throws JMSException
	 *             the JMS exception
	 */
	@Test
	public void generateWorkOrdersWhenAlreadyExistSuccessTest() throws JMSException {
		riksdagenDataSourcesWorkGenerator.generateWorkOrders();
		final JmsSender jmsSenderMock = mock(JmsSender.class);
		ReflectionTestUtils.setField(riksdagenDataSourcesWorkGenerator, "jmsSender", jmsSenderMock);

		final RiksdagenImportService riksdagenImportService = mock(RiksdagenImportService.class);

		ReflectionTestUtils.setField(riksdagenDataSourcesWorkGenerator, "importService", riksdagenImportService);

		final String committeId = "committeId";
		when(riksdagenImportService.getAvaibleCommitteeProposal()).thenReturn(Arrays.asList(committeId));
		when(riksdagenImportService.getCommitteeProposalComponentDataMap()).thenReturn(new HashMap<String, String>() {{
		    put(committeId,committeId);
		}});

		riksdagenDataSourcesWorkGenerator.generateWorkOrders();

		final ArgumentCaptor<Destination> destCaptor = ArgumentCaptor.forClass(Destination.class);

		final ArgumentCaptor<Serializable> stringCaptor = ArgumentCaptor.forClass(Serializable.class);

		verify(jmsSenderMock, never()).send(destCaptor.capture(), stringCaptor.capture());

		final List<Serializable> capturedStrings = stringCaptor.getAllValues();
		final List<Destination> capturedDestinations = destCaptor.getAllValues();

		assertTrue(capturedStrings.isEmpty());
		assertTrue(capturedDestinations.isEmpty());
	}

	/**
	 * Generate work orders when not exist success test.
	 *
	 * @throws JMSException the JMS exception
	 */
	@Test
	public void generateWorkOrdersWhenNotExistSuccessTest() throws JMSException {
		riksdagenDataSourcesWorkGenerator.generateWorkOrders();
		final JmsSender jmsSenderMock = mock(JmsSender.class);
		ReflectionTestUtils.setField(riksdagenDataSourcesWorkGenerator, "jmsSender", jmsSenderMock);

		final RiksdagenImportService riksdagenImportService = mock(RiksdagenImportService.class);

		ReflectionTestUtils.setField(riksdagenDataSourcesWorkGenerator, "importService", riksdagenImportService);

		final String committeId = "committeId";
		when(riksdagenImportService.getAvaibleCommitteeProposal()).thenReturn(Arrays.asList(committeId));
		when(riksdagenImportService.getCommitteeProposalComponentDataMap()).thenReturn(new HashMap<>());

		riksdagenDataSourcesWorkGenerator.generateWorkOrders();

		final ArgumentCaptor<Destination> destCaptor = ArgumentCaptor.forClass(Destination.class);

		final ArgumentCaptor<Serializable> stringCaptor = ArgumentCaptor.forClass(Serializable.class);

		verify(jmsSenderMock, times(1)).send(destCaptor.capture(), stringCaptor.capture());

		final List<Serializable> capturedStrings = stringCaptor.getAllValues();
		final List<Destination> capturedDestinations = destCaptor.getAllValues();

		assertFalse(capturedStrings.isEmpty());
		assertFalse(capturedDestinations.isEmpty());
	}

}

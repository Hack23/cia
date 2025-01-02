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
package com.hack23.cia.service.component.agent.impl.worldbank.workgenerator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.jms.Destination;
import javax.jms.JMSException;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.data.impl.WorldBankDataSources;
import com.hack23.cia.service.component.agent.impl.AbstractServiceComponentAgentFunctionalIntegrationTest;
import com.hack23.cia.service.component.agent.impl.common.jms.JmsSender;
import com.hack23.cia.service.external.worldbank.api.DataFailureException;
import com.hack23.cia.service.external.worldbank.api.WorldBankIndicatorApi;

/**
 * The Class WorldBankDataWorkGeneratorImplITest.
 */
@Transactional(timeout = 300)
public class WorldBankDataWorkGeneratorImplITest extends AbstractServiceComponentAgentFunctionalIntegrationTest {

	/** The world bank data sources work generator. */
	@Autowired
	@Qualifier("WorldBankDataWorkGeneratorImpl")
	private WorldBankDataSourcesWorkGenerator worldBankDataSourcesWorkGenerator;

	/**
	 * Generate work orders success test.
	 *
	 * @throws JMSException
	 *             the JMS exception
	 * @throws DataFailureException
	 */
	@Test
	public void generateWorkOrdersSuccessTest() throws JMSException, DataFailureException {
		final JmsSender jmsSenderMock = mock(JmsSender.class);
		final WorldBankIndicatorApi worldbankIndicatorApi = mock(WorldBankIndicatorApi.class);
        ReflectionTestUtils.setField(worldBankDataSourcesWorkGenerator, "jmsSender", jmsSenderMock);
		ReflectionTestUtils.setField(worldBankDataSourcesWorkGenerator, "worldbankIndicatorApi", worldbankIndicatorApi);

		final ArrayList<String> indicators = new ArrayList<>();
		indicators.add(UUID.randomUUID().toString());

		when(worldbankIndicatorApi.getIndicatorsWithSwedishData()).thenReturn(indicators);

		assertTrue(worldBankDataSourcesWorkGenerator.matches(WorldBankDataSources.DATA));
        assertFalse(worldBankDataSourcesWorkGenerator.matches(WorldBankDataSources.COUNTRIES));
        assertFalse(worldBankDataSourcesWorkGenerator.matches(WorldBankDataSources.INDICATORS));
        assertFalse(worldBankDataSourcesWorkGenerator.matches(WorldBankDataSources.TOPIC));

		worldBankDataSourcesWorkGenerator.generateWorkOrders();

		final ArgumentCaptor<Destination> destCaptor = ArgumentCaptor.forClass(Destination.class);
		final ArgumentCaptor<Serializable> stringCaptor = ArgumentCaptor.forClass(Serializable.class);

		verify(jmsSenderMock, atLeastOnce()).send(destCaptor.capture(),stringCaptor.capture());

		final List<Serializable> capturedStrings = stringCaptor.getAllValues();
		final List<Destination> capturedDestinations = destCaptor.getAllValues();

		assertNotNull(capturedStrings);
		assertNotNull(capturedDestinations);
	}

	@Test
	public void generateWorkOrdersIndicatorsSuccessTest() throws JMSException, DataFailureException {
		final JmsSender jmsSenderMock = mock(JmsSender.class);
		final WorldBankIndicatorApi worldbankIndicatorApi = mock(WorldBankIndicatorApi.class);
        ReflectionTestUtils.setField(worldBankDataSourcesWorkGenerator, "jmsSender", jmsSenderMock);
		ReflectionTestUtils.setField(worldBankDataSourcesWorkGenerator, "worldbankIndicatorApi", worldbankIndicatorApi);

		final ArrayList<String> indicators = new ArrayList<>();

		when(worldbankIndicatorApi.getIndicatorsWithSwedishData()).thenReturn(indicators);

		worldBankDataSourcesWorkGenerator.generateWorkOrders();
		verify(jmsSenderMock, never()).send(any(Destination.class),any(Serializable.class));
	}


	@Test
	public void generateWorkOrdersFailureTest() throws JMSException, DataFailureException {
		final JmsSender jmsSenderMock = mock(JmsSender.class);
		final WorldBankIndicatorApi worldbankIndicatorApi = mock(WorldBankIndicatorApi.class);
        ReflectionTestUtils.setField(worldBankDataSourcesWorkGenerator, "jmsSender", jmsSenderMock);
		ReflectionTestUtils.setField(worldBankDataSourcesWorkGenerator, "worldbankIndicatorApi", worldbankIndicatorApi);

		final ArrayList<String> indicators = new ArrayList<>();
		indicators.add(UUID.randomUUID().toString());

		when(worldbankIndicatorApi.getIndicatorsWithSwedishData()).thenThrow(new DataFailureException(new RuntimeException()));

		worldBankDataSourcesWorkGenerator.generateWorkOrders();
		verify(jmsSenderMock, never()).send(any(Destination.class),any(Serializable.class));
	}

}

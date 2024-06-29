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
import javax.transaction.Transactional;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.util.ReflectionTestUtils;

import com.hack23.cia.model.external.worldbank.indicators.impl.IndicatorElement;
import com.hack23.cia.model.internal.application.data.impl.WorldBankDataSources;
import com.hack23.cia.service.component.agent.impl.AbstractServiceComponentAgentFunctionalIntegrationTest;
import com.hack23.cia.service.component.agent.impl.common.jms.JmsSender;
import com.hack23.cia.service.external.worldbank.api.DataFailureException;
import com.hack23.cia.service.external.worldbank.api.WorldBankIndicatorApi;

/**
 * The Class WorldBankIndicatorWorkGeneratorImplITest.
 */
@Transactional
public class WorldBankIndicatorWorkGeneratorImplITest extends AbstractServiceComponentAgentFunctionalIntegrationTest {

	/** The world bank data sources work generator. */
	@Autowired
	@Qualifier("WorldBankIndicatorWorkGeneratorImpl")
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

		final ArrayList<IndicatorElement> indicators = new ArrayList<>();
		indicators.add(new IndicatorElement().withId(UUID.randomUUID().toString()));

		when(worldbankIndicatorApi.getIndicators()).thenReturn(indicators);

		assertTrue(worldBankDataSourcesWorkGenerator.matches(WorldBankDataSources.INDICATORS));
		assertFalse(worldBankDataSourcesWorkGenerator.matches(WorldBankDataSources.DATA));
        assertFalse(worldBankDataSourcesWorkGenerator.matches(WorldBankDataSources.COUNTRIES));
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

	/**
	 * Generate work orders success no indicators test.
	 *
	 * @throws JMSException
	 *             the JMS exception
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	@Test
	public void generateWorkOrdersSuccessNoIndicatorsTest() throws JMSException, DataFailureException {
		final JmsSender jmsSenderMock = mock(JmsSender.class);

		final WorldBankIndicatorApi worldbankIndicatorApi = mock(WorldBankIndicatorApi.class);

		ReflectionTestUtils.setField(worldBankDataSourcesWorkGenerator, "jmsSender", jmsSenderMock);
		ReflectionTestUtils.setField(worldBankDataSourcesWorkGenerator, "worldbankIndicatorApi", worldbankIndicatorApi);

		final ArrayList<IndicatorElement> indicators = new ArrayList<>();
		when(worldbankIndicatorApi.getIndicators()).thenReturn(indicators);

		worldBankDataSourcesWorkGenerator.generateWorkOrders();
		verify(jmsSenderMock, never()).send(any(Destination.class),any(Serializable.class));
	}

	/**
	 * Generate work orders failure test.
	 *
	 * @throws JMSException
	 *             the JMS exception
	 * @throws DataFailureException
	 *             the data failure exception
	 */
	@Test
	public void generateWorkOrdersFailureTest() throws JMSException, DataFailureException {
		final JmsSender jmsSenderMock = mock(JmsSender.class);

		final WorldBankIndicatorApi worldbankIndicatorApi = mock(WorldBankIndicatorApi.class);

		ReflectionTestUtils.setField(worldBankDataSourcesWorkGenerator, "jmsSender", jmsSenderMock);
		ReflectionTestUtils.setField(worldBankDataSourcesWorkGenerator, "worldbankIndicatorApi", worldbankIndicatorApi);

		when(worldbankIndicatorApi.getIndicators()).thenThrow(new DataFailureException(new RuntimeException()));

		worldBankDataSourcesWorkGenerator.generateWorkOrders();
		verify(jmsSenderMock, never()).send(any(Destination.class),any(Serializable.class));
	}

}
